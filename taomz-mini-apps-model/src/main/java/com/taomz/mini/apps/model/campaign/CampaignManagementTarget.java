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
 * 营销活动管理对象
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("campaign_management_target")
public class CampaignManagementTarget implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 裂变对象
     */
    private String userType;

    /**
     * 是否有效  1有效 0无效
     */
    private Integer deleteFlag;

    /**
     * 修改时间
     */
    private Date updateTime;


}
