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
 * 付费用户支付明细表
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_pay_detail")
public class UserPayDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 到期时间
     */
    private Date endTime;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 业务ID
     */
    private Integer businessId;

    /**
     * 类型（3-品牌，4-会员）
     */
    private Short orderType;

    /**
     * 支付类型(0 -支付宝，1 -微信，2 -银联，3 -线下支付)
     */
    private Short payType;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 支付状态(0 - 待支付，1 - 已支付，2 - 已取消)
     */
    private Short status;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * （1-新协议，0-老协议）
     */
    private Short isNewAgreement;

    /**
     * 备注
     */
    private String remark;

    /**
     * 流水ID
     */
    private Integer flowId;


}
