package com.taomz.mini.apps.dto.campaign;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : CampaignManagementTargetDTO
 * @Package : com.ishop.dto.Campaign
 * @Description: 营销活动对象
 * @date 2020/12/2 10:43
 **/
@Data
@Accessors(chain = true)
public class CampaignManagementTargetDTO {

    private Integer id;

    /**
     * 营销活动管理ID
     */
    private Integer campaignId;

    /**
     * 等级ID
     */
    private Integer gradeId;

    /**
     * 生效次数
     */
    private Integer validNum;

    /**
     * 生效条件
     */
    private String validCondition;
}
