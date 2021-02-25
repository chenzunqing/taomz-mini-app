package com.taomz.mini.apps.service.campaign;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.campaign.CampaignManagementSubjectDTO;
import com.taomz.mini.apps.model.campaign.CampaignManagementSubject;

import java.util.List;

/**
 * <p>
 * 营销活动管理主体 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
public interface CampaignManagementSubjectService extends IService<CampaignManagementSubject> {

    List<CampaignManagementSubjectDTO> selectByCmId(Integer campaignId);
}
