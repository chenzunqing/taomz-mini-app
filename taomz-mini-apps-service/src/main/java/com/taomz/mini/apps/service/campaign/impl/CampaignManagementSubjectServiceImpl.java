package com.taomz.mini.apps.service.campaign.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.campaign.CampaignManagementSubjectMapper;
import com.taomz.mini.apps.dto.campaign.CampaignManagementSubjectDTO;
import com.taomz.mini.apps.model.campaign.CampaignManagementSubject;
import com.taomz.mini.apps.service.campaign.CampaignManagementSubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 营销活动管理主体 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Service
public class CampaignManagementSubjectServiceImpl extends ServiceImpl<CampaignManagementSubjectMapper, CampaignManagementSubject> implements CampaignManagementSubjectService {

    @Override
    public List<CampaignManagementSubjectDTO> selectByCmId(Integer campaignId) {
        List<CampaignManagementSubject> subjectList = getBaseMapper().selectList(
                Wrappers.lambdaQuery(CampaignManagementSubject.class).
                        eq(CampaignManagementSubject::getCampaignId, campaignId)
                        .eq(CampaignManagementSubject::getDeleteFlag, 1));
        if (CollectionUtil.isNotEmpty(subjectList)) {
            return JSON.parseArray(JSON.toJSONString(subjectList), CampaignManagementSubjectDTO.class);
        }
        return null;
    }
}
