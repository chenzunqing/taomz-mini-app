package com.taomz.mini.apps.dto.campaign;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : CampaignManagementSubjectDTO
 * @Package : com.ishop.dto.Campaign
 * @Description: 营销活动主体
 * @date 2020/12/2 10:44
 **/
@Data
@Accessors(chain = true)
public class CampaignManagementSubjectDTO {

    private Integer id;

    /**
     * 营销活动管理ID
     */
    private Integer campaignId;

    /**
     * 等级ID
     */
    private Integer gradeId;
}
