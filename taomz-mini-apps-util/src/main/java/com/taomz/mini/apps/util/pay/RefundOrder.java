package com.taomz.mini.apps.util.pay;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : RefundOrder
 * @Package : com.taomz.mini.apps.util.pay
 * @Description: 退款订单
 * @date 2020/12/4 16:09
 **/
@Setter
@Getter
public class RefundOrder {

    /**
     * 平台交易号
     */
    private String tradeNo;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     */
    private String outRequestNo;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退款的原因说明
     */
    private String refundReason;

    /**
     * 商户的操作员编号
     */
    private String operatorId;
}
