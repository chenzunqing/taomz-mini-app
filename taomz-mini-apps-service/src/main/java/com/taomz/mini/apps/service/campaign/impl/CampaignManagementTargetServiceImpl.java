package com.taomz.mini.apps.service.campaign.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.campaign.CampaignManagementTargetMapper;
import com.taomz.mini.apps.dto.campaign.CampaignManagementTargetDTO;
import com.taomz.mini.apps.model.campaign.CampaignManagementTarget;
import com.taomz.mini.apps.service.campaign.CampaignManagementTargetService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 营销活动管理对象 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Service
public class CampaignManagementTargetServiceImpl extends ServiceImpl<CampaignManagementTargetMapper, CampaignManagementTarget> implements CampaignManagementTargetService {

    @Override
    public List<CampaignManagementTargetDTO> selectByCmId(Integer campaignId) {
        List<CampaignManagementTarget> targetList = getBaseMapper().selectList(
                Wrappers.lambdaQuery(CampaignManagementTarget.class)
                        .eq(CampaignManagementTarget::getCampaignId, campaignId)
                        .eq(CampaignManagementTarget::getDeleteFlag, 1));
        if (CollectionUtil.isNotEmpty(targetList)) {
            return JSON.parseArray(JSON.toJSONString(targetList), CampaignManagementTargetDTO.class);
        }
        return null;
    }
}
