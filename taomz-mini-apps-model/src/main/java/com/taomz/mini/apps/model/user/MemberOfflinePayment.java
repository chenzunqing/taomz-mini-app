package com.taomz.mini.apps.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 会员线下支付审核表
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_member_offline_payment")
public class MemberOfflinePayment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 应支付金额
     */
    private BigDecimal totalFee;

    /**
     * 付款人户名
     */
    private String payAccountName;

    /**
     * 转账凭证号
     */
    private String certificateNum;

    /**
     * 汇款凭证图片
     */
    private String certificateImg;

    /**
     * 审核状态(0待审核/1业务审核通过/2财务审核通过/3审核拒绝/4已退款/9已取消)
     */
    private Integer confirmStatus;

    /**
     * 审核不通过说明
     */
    private String refusalReason;

    /**
     * 是否是续费(0续费/1缴费)
     */
    private Integer isRenewal;

    /**
     * 支付流水ID
     */
    private Integer payRecordId;

    /**
     * 子账号ID
     */
    private Integer subUserId;

    /**
     * 创建时间
     */
    private Date createTime;
}
