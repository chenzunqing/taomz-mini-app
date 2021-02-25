package com.taomz.mini.apps.util.pay;

import com.alipay.api.AlipayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.taomz.mini.apps.util.TransactionType;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : AliPayEnum
 * @Package : com.taomz.mini.apps.util.pay
 * @Description: 支付宝支付enum
 * @date 2020/12/4 10:41
 **/
public enum AliPayEnum implements TransactionType {

    /**
     * 网页支付
     */
    PAGE("alipay.trade.page.pay") {

        @Override
        public AlipayRequest getRequest(String bizContent) {
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setBizContent(bizContent);
            return request;
        }
    },
    /**
     * APP支付
     */
    APP("alipay.trade.app.pay"),
    /**
     * 手机网站支付
     */
    WAP("alipay.trade.wap.pay") {

        @Override
        public AlipayRequest getRequest(String bizContent) {
            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            request.setBizContent(bizContent);
            return request;
        }
    },
    /**
     *  统一收单交易结算接口
     */
    SETTLE("alipay.trade.order.settle"),
    /**
     * 交易订单查询
     */
    QUERY("alipay.trade.query"),
    /**
     * 交易订单关闭
     */
    CLOSE("alipay.trade.close"),
    /**
     * 交易订单撤销
     */
    CANCEL("alipay.trade.cancel"),
    /**
     * 退款
     */
    REFUND("alipay.trade.refund") ,
    /**
     * 退款查询
     */
    REFUNDQUERY("alipay.trade.fastpay.refund.query") ,
    /**
     * 下载对账单
     */
    DOWNLOADBILL("alipay.data.dataservice.bill.downloadurl.query"),
    /**
     * 转账到支付宝
     */
    TRANS("alipay.fund.trans.toaccount.transfer"),
    /**
     * 转账查询
     */
    TRANS_QUERY("alipay.fund.trans.order.query");

    private String method;

    AliPayEnum(String method) {
        this.method = method;
    }

    public AlipayRequest getRequest(String bizContent){
        return null;
    }

    @Override
    public String getType() {
        return this.name();
    }

    /**
     * 获取接口名称
     * @return 接口名称
     */
    @Override
    public String getMethod() {
        return this.method;
    }
}
