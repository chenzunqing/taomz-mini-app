package com.taomz.mini.apps.model.activity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 活动品牌招商申请流水表
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_activity_apply_investment_brand")
public class ActivityApplyInvestmentBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动品牌招商申请表主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 展位号ID
     */
    private Integer boothNumberId;

    /**
     * 活动报名Id
     */
    private Integer applyId;

    /**
     * 展位号
     */
    private String boothNumber;

    /**
     * 排展草稿展位号
     */
    private String draftBoothNumber;

    /**
     * 报名品牌ID   来源已入驻品牌
     */
    private Integer brandId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 审核状态 0初始化 1通过 2不通过 4已支付 5取消 6退款中 7退款成功 10拒绝退款 11线下转账待审核 12线下转账不通过
     */
    private Integer auditState;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 审核人ID
     */
    private String auditAdmin;

    /**
     * 审核备注
     */
    private String auditRemark;


}
