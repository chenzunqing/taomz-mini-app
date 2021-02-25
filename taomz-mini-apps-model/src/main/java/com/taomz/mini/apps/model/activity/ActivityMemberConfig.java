package com.taomz.mini.apps.model.activity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 活动会员设置表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_activity_member_config")
public class ActivityMemberConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动会员设置表主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 耦合字段 活动产品ID
     */
    private Integer actProdId;

    /**
     * 活动ID
     */
    private Integer activityId;

    /**
     * 会员开始报名时间
     */
    private Date memberStartTime;

    /**
     * 会员报名结束时间
     */
    private Date memberEndTime;

    /**
     * 是否有参会  1有 0 没有
     */
    private Integer enterFlag;

    /**
     * 会议开始时间
     */
    private Date enterStartTime;

    /**
     * 会议结束时间
     */
    private Date enterEndTime;

    /**
     * 参会总人数限制
     */
    private Integer enterTotalLimit;

    /**
     * 会员最大参会人数限制 null无限制
     */
    private Integer enterLimit;

    /**
     * 参会费用单价
     */
    private BigDecimal enterPrice;

    /**
     * 是否免费参会人员  1有 0 没有
     */
    private Integer enterFeeFlag;

    /**
     * 免费参会人数限制
     */
    private Integer enterFeeNum;

    /**
     * 是否有逛展  1有 0 没有
     */
    private Integer visitExhibitionFlag;

    /**
     * 会员逛展开始时间
     */
    private Date visitExhibitionStartTime;

    /**
     * 会员逛展结束时间
     */
    private Date visitExhibitionEndTime;

    /**
     * 逛展总人数限制
     */
    private Integer visitExhibitionTotalLimit;

    /**
     * 会员最大逛展报名数 null无限制
     */
    private Integer visitExhibitionLimit;

    /**
     * 逛展费用
     */
    private BigDecimal visitExhibitionPrice;

    /**
     * 是否有免费逛展人  1是 0 否
     */
    private Integer visitExhibitionFeeFlag;

    /**
     * 免费会员逛展人数
     */
    private Integer freeVisitExhibitionLimit;

    /**
     * 是否有住宿  1有 0 没有
     */
    private Integer lodgingFlag;

    /**
     * 住宿开始时间
     */
    private Date lodgingStartTime;

    /**
     * 住宿结束时间
     */
    private Date lodgingEndTime;

    /**
     * 是否允许拼房 1允许 0不允许
     */
    private Integer buildRoomFlag;

    /**
     * 是否有免费住宿人员 1是 0否
     */
    private Integer feeLodgingFlag;

    /**
     * 免费住宿人数
     */
    private Integer feeLodgingLimit;

    /**
     * 住宿押金金额
     */
    private BigDecimal lodgingDeposit;

    /**
     * 住宿单价
     */
    private BigDecimal lodgingPrice;

    /**
     * 单个会员住宿人数上限
     */
    private Integer lodgingLimit;

    /**
     * 是否有晚宴  1有 0 没有
     */
    private Integer banquetFlag;

    /**
     * 晚宴开始报名时间
     */
    private Date banquetStartTime;

    /**
     * 晚宴报名结束时间
     */
    private Date banquetEndTime;

    /**
     * 晚宴总人数上限
     */
    private Integer banquetTotalLimit;

    /**
     * 会员晚宴单店铺限制人数
     */
    private Integer banquetLimit;

    /**
     * 是否有免费晚宴 1开 0关
     */
    private Integer feeBanquetFlag;

    /**
     * 免费晚宴限制
     */
    private Integer feeBanquetLimit;

    /**
     * 晚宴费用
     */
    private BigDecimal banquetPrice;

    /**
     * 参会已申请人数
     */
    private Integer applyEnterNum;

    /**
     * 虚拟参会已申请人数
     */
    private Integer virtualApplyEnterNum;

    /**
     * 逛展已申请人数
     */
    private Integer applyVisitExhibitionNum;

    /**
     * 晚宴已申请人数
     */
    private Integer applyBanquetNum;

    /**
     * 是否有附加会议 1是 0否
     */
    private Integer additionalFlag;

    /**
     * 协议ID
     */
    private Integer agreementId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 报名设置是否有效 1有效 0无效
     */
    private Integer deleteFlag;

    /**
     * 报名是否需要审核 1需要 0不需要
     */
    private Integer auditFlag;

    /**
     * 超时报名开关 1开 0 关
     */
    private Integer timeoutSignUpFlag;

    /**
     * 超时报名开始时间
     */
    private Date timeoutSignUpStartTime;

    /**
     * 超时报名结束时间
     */
    private Date timeoutSignUpEndTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否允许退款  0否 1是
     */
    private Integer refundFlag;

    /**
     * 退款开始时间
     */
    private Date refundStartTime;

    /**
     * 退款结束时间
     */
    private Date refundEndTime;

    /**
     * 退押金任务是否开启 0否 1是
     */
    private Integer depositRefundTaskFlag;

    /**
     * 押金发票任务是否开启 0否 1是
     */
    private Integer depositInvoiceTaskFlag;

    /**
     * 是否有套餐价标识  0无 1有
     */
    private Integer packageFlag;

    /**
     * 限制第几人后享受套餐价
     */
    private Integer packageFew;

    /**
     * 套餐价
     */
    private BigDecimal packagePrice;

    /**
     * 套餐限制提醒标识  0 无 1有
     */
    private Integer packageRemindFlag;

    /**
     * 套餐限制提醒
     */
    private String packageRemind;

    /**
     * 是否实名开关（0-否，1-是）
     */
    private Integer isOpenReal;

    /**
     * 报名前提示文案
     */
    private String promptCopy;

    /**
     * 进场须知
     */
    private String entryNotice;

    /**
     * 0/不限制，1/限制
     */
    private Integer isLimited;

    /**
     * 临时会员是否可报名  1可以 0不可以
     */
    private Integer temporaryMemberSignUpFlag;

    /**
     * 是否显示报名人数  1显示 0不显示
     */
    private Integer viewEnterNumFlag;


}
