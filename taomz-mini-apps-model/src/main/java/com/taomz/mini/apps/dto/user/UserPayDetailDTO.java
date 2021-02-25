package com.taomz.mini.apps.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * package ：com.ishop.dto
 * describe ：
 * Date ： 2018/9/14 15:07
 *
 * @author liaobing
 */
@Getter
@Setter
@ToString
public class UserPayDetailDTO implements Serializable {
    private static final long serialVersionUID = -5289623712443952717L;

    /**
     * 主键
     */
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
     * 类型（3-会员，4-品牌）
     */
    private String orderType;

    /**
     * 支付类型(0 -支付宝，1 -微信，2 -银联，3 -线下支付)
     */
    private String payType;

    /**
     * 订单名称
     */
    private String orderName;

    /**
     * 支付状态(0 - 待支付，1 - 已支付，2 - 已取消)
     */
    private String status;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 开票金额
     */
    private BigDecimal invoiceAmount;

    /**
     * 支付名称
     */
    private String payUserName;

    /**
     * 是否允许开票(0：不允许,1：允许申请,2：已申请开票,3：允许重开发票)
     */
    private Short allowInvoice = 0;

    /**
     * 开票状态
     */
    private Short invoiceStatus;

    /**
     * 备注
     */
    private String remark;

    private Short type;

    private Short orderStatus;

    private Date paymentTime;
}
