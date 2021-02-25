package com.taomz.mini.apps.service.campaign.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.campaign.CampaignManagementMapper;
import com.taomz.mini.apps.dao.mapper.campaign.TShareFissionRecordMapper;
import com.taomz.mini.apps.dto.campaign.CampaignManagementSubjectDTO;
import com.taomz.mini.apps.dto.campaign.CampaignManagementTargetDTO;
import com.taomz.mini.apps.dto.campaign.ShareFissionRecordCountDTO;
import com.taomz.mini.apps.model.campaign.CampaignManagement;
import com.taomz.mini.apps.model.campaign.TShareFissionRecord;
import com.taomz.mini.apps.service.campaign.CampaignManagementService;
import com.taomz.mini.apps.service.campaign.CampaignManagementSubjectService;
import com.taomz.mini.apps.service.campaign.CampaignManagementTargetService;
import com.taomz.mini.apps.service.campaign.TShareFissionRecordService;
import com.taomz.mini.apps.service.campaign.TShareFissionService;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.mini.apps.util.enums.MarketingActivityTypeEnum;
import com.taomz.mini.apps.util.enums.ShareFissionSuccEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 分享裂变记录 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Service
public class TShareFissionRecordServiceImpl extends ServiceImpl<TShareFissionRecordMapper, TShareFissionRecord> implements TShareFissionRecordService {

    @Autowired
    private CampaignManagementMapper campaignManagementMapper;

    @Autowired
    private TShareFissionService shareFissionService;

    @Autowired
    private CampaignManagementTargetService campaignManagementTargetService;

    @Autowired
    private CampaignManagementSubjectService campaignManagementSubjectService;

    @Autowired
    private CampaignManagementService campaignManagementService;

    @Autowired
    private RedisService redisService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Integer cmId, Integer pUserId, ShareFissionSuccEnum succEnum, Integer userId, Integer gradeId, Integer isNewUser) {
        synchronized (TShareFissionRecordServiceImpl.class) {
            // 获取营销活动配置
            List<CampaignManagement> campaignManagementList = campaignManagementMapper.selectList(
                    Wrappers.lambdaQuery(CampaignManagement.class)
                            .eq(CampaignManagement::getBussinessId, cmId)
                            .eq(CampaignManagement::getStatus, 0)
                            .eq(CampaignManagement::getType, MarketingActivityTypeEnum.MARKETING_ACTIVITY_SHARE_FISSION.name())
                            .orderByDesc(CampaignManagement::getCreateTime));
            if (CollectionUtil.isEmpty(campaignManagementList)) {
                return;
            }
            cmId = campaignManagementList.get(0).getId();
            List<CampaignManagementTargetDTO> actTarget = campaignManagementTargetService.selectByCmId(cmId);
            List<CampaignManagementSubjectDTO> actSubject = campaignManagementSubjectService.selectByCmId(cmId);
            // 判断对象是否有权限
            Optional<CampaignManagementTargetDTO> optional = actTarget.stream().filter(
                    targetDTO ->
                            targetDTO.getValidCondition().equals(succEnum.name())
                                    && targetDTO.getGradeId().equals(gradeId)
            ).findAny();
            if (!optional.isPresent()) {
                return;
            }

            // 判断用户是否有主体权限
            List<Integer> subjectGradeId = actSubject.stream()
                    .map(CampaignManagementSubjectDTO::getGradeId)
                    .collect(Collectors.toList());
            if (!campaignManagementService.userPermission(pUserId, subjectGradeId)) {
                return;
            }

            // 判断对象当天是否已经点击过
            String targetKey = redisService.generateCacheKey("commerce:user:campaign:share:fission", cmId, "target", succEnum, userId);
            if (Objects.nonNull(redisService.get(targetKey))) {
                return;
            }
            String subjectKey = redisService.generateCacheKey("commerce:user:campaign:share:fission", cmId, "subject", succEnum, pUserId);
            TShareFissionRecord shareFissionRecord = new TShareFissionRecord()
                    .setCmId(cmId)
                    .setPUserId(pUserId)
                    .setUserId(userId)
                    .setGradeId(gradeId)
                    .setEnterChannel(succEnum.name())
                    .setIsNewUser(isNewUser)
                    .setCreateTime(DateUtil.date());
            getBaseMapper().insert(shareFissionRecord);

            // 对象当天已经点击过
            redisService.set(targetKey, 1, DateUtil.between(DateUtil.date(), DateUtil.endOfDay(DateUtil.date()), DateUnit.SECOND));
            // 获取用户分享已经被点击过几次
            Long count = redisService.increaseValue(subjectKey, 1);
            if (optional.get().getValidNum() <= count) {
                if (Objects.isNull(shareFissionService.queryShareSucc(cmId, pUserId, null))) {
                    // 查询分享的用户是否在本次活动中是新注册的用户
                    getBaseMapper().selectCount(
                            Wrappers.lambdaQuery(TShareFissionRecord.class)
                                    .eq(TShareFissionRecord::getCmId, cmId)
                                    .eq(TShareFissionRecord::getUserId, userId)
                                    .eq(TShareFissionRecord::getIsNewUser, 1));
                    shareFissionService.save(cmId, pUserId, isNewUser);
                }
            }
        }
    }

    @Override
    public List<ShareFissionRecordCountDTO> queryShareCount(Integer cmId, Integer pUserId, ShareFissionSuccEnum succEnum) {
        List<CampaignManagement> campaignManagementList = campaignManagementMapper.selectList(
                Wrappers.lambdaQuery(CampaignManagement.class)
                        .eq(CampaignManagement::getBussinessId, cmId).eq(CampaignManagement::getStatus, 0));
        if (CollectionUtil.isEmpty(campaignManagementList)) {
            return null;
        }
        List<CampaignManagementTargetDTO> actTarget = campaignManagementTargetService.selectByCmId(campaignManagementList.get(0).getId());
        if (CollectionUtil.isNotEmpty(actTarget)) {
            List<ShareFissionRecordCountDTO> shareFissionRecordCountDTOList =  actTarget
                    .stream().parallel().map(targetDTO -> {
                        ShareFissionRecordCountDTO shareFissionRecordCountDTO = new ShareFissionRecordCountDTO();
                        BeanUtil.copyProperties(targetDTO, shareFissionRecordCountDTO);
                        return shareFissionRecordCountDTO;
                    }).distinct().map(recordCountDTO -> {
                        int count = getBaseMapper().selectCount(
                                Wrappers.lambdaQuery(TShareFissionRecord.class)
                                        .eq(TShareFissionRecord::getCmId, cmId)
                                        .eq(TShareFissionRecord::getPUserId, pUserId)
                                        .eq(TShareFissionRecord::getEnterChannel, recordCountDTO.getValidCondition()));
                        recordCountDTO.setSurplusCount(count > 0 ? count : 0);
                        return recordCountDTO;
                    }).collect(Collectors.toList());
            if (Objects.nonNull(succEnum)) {
                return shareFissionRecordCountDTOList.stream().filter(shareFissionRecordCountDTO -> {
                    if (succEnum.name().equals(shareFissionRecordCountDTO.getValidCondition())) {
                        return Boolean.TRUE;
                    }
                    return Boolean.FALSE;
                }).collect(Collectors.toList());
            }
            return shareFissionRecordCountDTOList;
        }
        return null;
    }
}
