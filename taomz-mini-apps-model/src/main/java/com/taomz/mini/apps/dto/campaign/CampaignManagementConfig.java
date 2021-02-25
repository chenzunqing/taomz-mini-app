package com.taomz.mini.apps.dto.campaign;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : CampaignManagementConfig
 * @Package : com.ishop.dto.Campaign
 * @Description: 营销活动管理配置信息
 * @date 2020/12/2 10:37
 **/
@Data
@Accessors(chain = true)
public class CampaignManagementConfig {

    /**
     * 主键
     */
    private  Integer id;

    /**
     * 业务ID
     */
    private Integer bussinessId;

    /**
     * 活动名称
     */
    private String title;

    /**
     * 类型（数据字典获取）
     */
    private String type;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 打榜初始化票数
     */
    private Long contributionInitialNum;

    /**
     * 打榜登录新增票数
     */
    private Long contributionLoginAddNum;

    /**
     * 打榜分享新增票数
     */
    private Long contributionShareAddNum;

    /**
     * 裂变分享图片
     */
    private String fissionShareImage;

    /**
     * 裂变分享标题
     */
    private String fissionShareTitle;

    /**
     * 裂变分享描述
     */
    private String fissionShareDesc;

    /**
     * 活动主体
     */
    List<CampaignManagementSubjectDTO> actSubject;

    /**
     * 活动对象
     */
    List<CampaignManagementTargetDTO> actTarget;
}
