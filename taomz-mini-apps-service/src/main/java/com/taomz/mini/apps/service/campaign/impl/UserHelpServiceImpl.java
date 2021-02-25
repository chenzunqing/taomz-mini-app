package com.taomz.mini.apps.service.campaign.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.campaign.UserHelpMapper;
import com.taomz.mini.apps.dao.mapper.campaign.UserHelpRecordMapper;
import com.taomz.mini.apps.dto.campaign.CampaignManagementConfig;
import com.taomz.mini.apps.dto.campaign.CampaignManagementSubjectDTO;
import com.taomz.mini.apps.model.campaign.UserHelp;
import com.taomz.mini.apps.model.campaign.UserHelpRecord;
import com.taomz.mini.apps.service.campaign.CampaignManagementService;
import com.taomz.mini.apps.service.campaign.UserHelpRecordService;
import com.taomz.mini.apps.service.campaign.UserHelpService;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.mini.apps.util.enums.MarketingActivityTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户助力值表 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Service
public class UserHelpServiceImpl extends ServiceImpl<UserHelpMapper, UserHelp> implements UserHelpService {

    @Autowired
    private UserHelpRecordMapper userHelpRecordMapper;

    @Autowired
    private CampaignManagementService campaignManagementService;

    @Autowired
    private UserHelpRecordService userHelpRecordService;

    @Autowired
    private RedisService redisService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long userSurplusHelp(Integer activityId, Integer userId, Integer shareUserId, Integer isNewUser) {
        synchronized (UserHelpServiceImpl.class) {
            if (Objects.isNull(activityId)) {
                return 0L;
            }
            CampaignManagementConfig campaignManagementConfig = campaignManagementService.getConfig(activityId, MarketingActivityTypeEnum.MARKETING_ACTIVITY_BRAND_CALL.name());
            if (Objects.isNull(campaignManagementConfig)
                    || Objects.isNull(campaignManagementConfig.getId())
                    || DateUtil.compare(campaignManagementConfig.getEndTime(), DateUtil.date()) <= 0) {
                return 0L;
            }
            if (CollectionUtil.isEmpty(campaignManagementConfig.getActSubject())) {
                return 0L;
            }
            // 判断用户是否有权限
            List<Integer> subjectGradeId = campaignManagementConfig.getActSubject().stream()
                    .map(CampaignManagementSubjectDTO::getGradeId)
                    .collect(Collectors.toList());
            if (!campaignManagementService.userPermission(userId, subjectGradeId)) {
                return 0L;
            }
            UserHelp userHelp = getBaseMapper().selectOne(
                    Wrappers.lambdaQuery(UserHelp.class)
                            .eq(UserHelp::getActProdId, activityId)
                            .eq(UserHelp::getUserId, userId));
            String redisKey = redisService.generateCacheKey("commerce:user:help:login", activityId, userId);
            if (Objects.isNull(userHelp)) {
                userHelp = new UserHelp()
                        .setActProdId(activityId)
                        .setHelpNum(campaignManagementConfig.getContributionLoginAddNum() + campaignManagementConfig.getContributionInitialNum())
                        .setUseredHelpNum(0L)
                        .setUserId(userId)
                        .setIsNewUser(isNewUser)
                        .setPUserId(shareUserId)
                        .setCreateTime(DateUtil.date())
                        .setLoginTime(DateUtil.date());
                // 插入记录
                getBaseMapper().insert(userHelp);
                // 更新用户助力记录值
                UserHelpRecord userHelpRecord = new UserHelpRecord()
                        .setHelpId(userHelp.getId())
                        .setHelpNum(userHelp.getHelpNum())
                        .setInstruction("登录")
                        .setType(0)
                        .setOperateUserId(userId)
                        .setCreateTime(DateUtil.date());
                userHelpRecordMapper.insert(userHelpRecord);
                redisService.set(redisKey, userHelp.getHelpNum(), DateUtil.between(DateUtil.date(), DateUtil.endOfDay(DateUtil.date()), DateUnit.SECOND));
                return userHelp.getHelpNum() - userHelp.getUseredHelpNum();
            } else {
                Object object = redisService.get(redisKey);
                if (Objects.isNull(object) && userHelpRecordMapper.currentDayLoginCount(activityId, userId, DateUtil.date()) <= 0) {
                    long surplusHelpNum = userHelpRecordService.save(
                            activityId,
                            userId,
                            shareUserId,
                            campaignManagementConfig.getContributionLoginAddNum(),
                            "登录",
                            0);
                    redisService.set(redisKey, surplusHelpNum, DateUtil.between(DateUtil.date(), DateUtil.endOfDay(DateUtil.date()), DateUnit.SECOND));
                    return surplusHelpNum;
                }
                return userHelp.getHelpNum() - userHelp.getUseredHelpNum();
            }
        }
    }
}
