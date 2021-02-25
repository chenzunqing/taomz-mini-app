package com.taomz.mini.apps.service.campaign.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.campaign.CampaignManagementMapper;
import com.taomz.mini.apps.dao.mapper.user.UserCategoryMapper;
import com.taomz.mini.apps.dto.campaign.CampaignManagementConfig;
import com.taomz.mini.apps.model.campaign.CampaignManagement;
import com.taomz.mini.apps.model.user.UserCategory;
import com.taomz.mini.apps.service.campaign.CampaignManagementService;
import com.taomz.mini.apps.service.campaign.CampaignManagementSubjectService;
import com.taomz.mini.apps.service.campaign.CampaignManagementTargetService;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.mini.apps.util.enums.UserCategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * 营销活动管理 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Service
public class CampaignManagementServiceImpl extends ServiceImpl<CampaignManagementMapper, CampaignManagement> implements CampaignManagementService {

    @Autowired
    private UserCategoryMapper userCategoryMapper;

    @Autowired
    private CampaignManagementSubjectService campaignManagementSubjectService;

    @Autowired
    private CampaignManagementTargetService campaignManagementTargetService;

    @Autowired
    private RedisService redisService;

    @Override
    public CampaignManagementConfig getConfig(Integer cmId, String type) {
        String configKey = redisService.generateCacheKey("commerce:user:campaign:management:config", cmId);
        CampaignManagementConfig config = Optional.ofNullable(redisService.get(configKey)).map(object -> (JSON.parseObject(JSON.toJSONString(object), CampaignManagementConfig.class))).orElseGet(() -> {
            CampaignManagementConfig campaignManagementConfig = new CampaignManagementConfig();
            CampaignManagement campaignManagement = getBaseMapper().selectById(cmId);
            if (Objects.isNull(campaignManagement)) {
                return null;
            }
            BeanUtil.copyProperties(campaignManagement, campaignManagementConfig, CopyOptions.create().ignoreError());
            campaignManagementConfig.setActTarget(campaignManagementTargetService.selectByCmId(cmId));
            campaignManagementConfig.setActSubject(campaignManagementSubjectService.selectByCmId(cmId));
            return campaignManagementConfig;
        });
        if (Objects.nonNull(config) && type.equals(config.getType())) {
            return config;
        }
        return null;
    }

    @Override
    public Boolean userPermission(Integer userId, List<Integer> subjectGradeId) {
        Boolean flag = Boolean.FALSE;
        List<UserCategory> categoryList = userCategoryMapper.selectList(
                Wrappers.lambdaQuery(UserCategory.class)
                        .eq(UserCategory::getUserId, userId)
                        .in(UserCategory::getStatus, Arrays.asList(
                                UserCategoryEnum.审核通过已付费.getFlag(),
                                UserCategoryEnum.线下续费待确认.getFlag(),
                                UserCategoryEnum.线下续费已拒绝.getFlag())));
        if (CollectionUtil.isNotEmpty(categoryList)) {
            for (UserCategory userCategory : categoryList) {
                if (Objects.nonNull(userCategory.getGradeId()) && subjectGradeId.contains(userCategory.getGradeId())) {
                    flag = Boolean.TRUE;
                    break;
                }
            }
        } else {
            if (subjectGradeId.contains(8)) {
                flag = Boolean.TRUE;
            }
        }
        return flag;
    }
}
