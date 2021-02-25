package com.taomz.mini.apps.service.campaign;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.campaign.CampaignManagementConfig;
import com.taomz.mini.apps.model.campaign.CampaignManagement;

import java.util.List;

/**
 * <p>
 * 营销活动管理 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
public interface CampaignManagementService extends IService<CampaignManagement> {

    /**
     * 获取营销活动信息
     * @param cmId
     * @param type
     * @return
     */
    CampaignManagementConfig getConfig(Integer cmId, String type);

    /**
     * 判断用户主体是否有权限
     * @param userId
     * @param subjectGradeId
     * @return
     */
    Boolean userPermission(Integer userId, List<Integer> subjectGradeId);
}
