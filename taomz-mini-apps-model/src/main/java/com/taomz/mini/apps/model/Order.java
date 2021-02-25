package com.taomz.mini.apps.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 商品总价
     */
    private BigDecimal totalFee;

    /**
     * 实付金额
     */
    private BigDecimal payment;

    /**
     * 支付类型(0支付宝/1微信/2银联/3线下支付)
     */
    private Short payType;

    /**
     * 付款状态(0未付款/1正在付款/2已付款/3退款/4支付超时)
     */
    private Short payStatus;

    /**
     * 订单类型(0普通/1活动/2集采/3品牌/4会员)
     */
    private Short orderType;

    /**
     * 订单状态(0待付款/1待发货/2已发货/3付款成功/4订单取消/5超时/6退货/7交易完成/8退款中/9退款成功/10退款拒绝)
     */
    private Short orderStatus;

    /**
     * 付款时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date consignTime;

    /**
     * 订单完成时间
     */
    private Date endTime;

    /**
     * 订单超时时间
     */
    private Date overTime;

    /**
     * 订单关闭时间
     */
    private Date closeTime;

    /**
     * 买家用户ID
     */
    private Integer userId;

    /**
     * 卖家用户Id
     */
    private Integer sellerUserId;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 收货地址
     */
    private Integer address;

    /**
     * 活动ID
     */
    private Integer promotionsId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 物流公司编号
     */
    private String logisticsNum;

    /**
     * 快递单号
     */
    private String courierNum;

    /**
     * 子账号ID
     */
    private Integer subUserId;

    /**
     * 质押金额
     */
    private BigDecimal cashPledge;

    /**
     * 发票金额
     */
    private BigDecimal invoiceAmount;

    /**
     * 押金退款状态(1退款申请中 2已退款)
     */
    private Integer cashPledgeRefundStatus;

    /**
     * 押金退款金额
     */
    private BigDecimal cashPledgeRefundFee;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 退款金额
     */
    private BigDecimal refundFee;

    /**
     * 退款金额
     */
    private String payCode;
}
