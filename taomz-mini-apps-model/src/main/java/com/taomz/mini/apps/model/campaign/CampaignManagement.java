package com.taomz.mini.apps.model.campaign;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 营销活动管理
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("campaign_management")
public class CampaignManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 业务绑定ID
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
     * 状态 0-有效/1-无效
     */
    private Integer status;

    /**
     * 打榜初始化票数
     */
    private Integer contributionInitialNum;

    /**
     * 打榜登录新增票数
     */
    private Integer contributionLoginAddNum;

    /**
     * 打榜分享新增票数
     */
    private Integer contributionShareAddNum;

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
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;


}
