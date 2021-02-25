package com.taomz.mini.apps.service.campaign;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.campaign.CampaignManagementTargetDTO;
import com.taomz.mini.apps.model.campaign.CampaignManagementTarget;

import java.util.List;

/**
 * <p>
 * 营销活动管理对象 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
public interface CampaignManagementTargetService extends IService<CampaignManagementTarget> {

    List<CampaignManagementTargetDTO> selectByCmId(Integer campaignId);
}
