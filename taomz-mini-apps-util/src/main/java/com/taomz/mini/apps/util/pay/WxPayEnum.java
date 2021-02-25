package com.taomz.mini.apps.util.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.util.TransactionType;

import java.util.Map;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : WxPayEnum
 * @Package : com.taomz.mini.apps.util.pay
 * @Description: 微信支付类型
 * @date 2020/11/23 10:40
 **/
public enum WxPayEnum implements TransactionType {

    MINI("pay/unifiedorder") {

        @Override
        public void setAttribute(Map<String, Object> parameters, PayOrder order) {
            // 小程序的APPID
            parameters.put("appid", order.getAppId());
            parameters.put("openid", order.getOpenid());
            // 交易类型
            parameters.put("trade_type", WxPayUtil.JSAPI_TRADE_TYPE);
            // 商品描述
            parameters.put("body", order.getSubject());
        }

        @Override
        public JSONObject getResult(Map<String, Object> parameters, String outTradeNo, String signKey, String appId) {
            JSONObject json = new JSONObject();
            json.put("appId", appId);
            json.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
            json.put("nonceStr", WxPayUtil.getRandomString(16));
            json.put("package", "prepay_id=" + parameters.get("prepay_id"));
            json.put("signType", WxPayUtil.PAYSIGN);
            json.put("paySign", WxPayUtil.createSign(json, signKey));
            json.put("outTradeNo", outTradeNo);
            return json;
        }
    }, MWEB("pay/unifiedorder") {

        @Override
        public void setAttribute(Map<String, Object> parameters, PayOrder order) {
            H5ScencInfo sceneInfo = new H5ScencInfo();
            sceneInfo.setH5_info("Wap", order.getWapUrl(), order.getWapName());
            // 公众平台的APPID
            parameters.put("appid", order.getAppId());
            // 场景信息
            parameters.put("scene_info", JSON.toJSONString(sceneInfo));
            // 交易类型
            parameters.put("trade_type", WxPayUtil.H5_TRADE_TYPE);
            // 商品描述
            parameters.put("body", order.getSubject());
        }

        @Override
        public JSONObject getResult(Map<String, Object> parameters, String outTradeNo, String signKey, String appId) {
            JSONObject json = new JSONObject();
            json.put("outTradeNo", outTradeNo);
            json.put("prepayId", parameters.get("prepay_id"));
            return json;
        }
    },
    APP("pay/unifiedorder"),
    QUERY("pay/orderquery"),
    CLOSE("pay/closeorder"),
    REVERSE("secapi/pay/reverse"),
    REFUND("secapi/pay/refund"),
    REFUNDQUERY("pay/refundquery"),
    DOWNLOADBILL("pay/downloadbill"),
    GETSIGNKEY("pay/getsignkey");

    /**
     * 设置参数
     * @param parameters
     * @param order
     */
    public void setAttribute(Map<String, Object> parameters, PayOrder order){

    }

    /**
     * 返回支付结果
     * @param parameters
     * @param outTradeNo
     * @param signKey
     * @param appId
     * @return
     */
    public JSONObject getResult(Map<String, Object> parameters, String outTradeNo, String signKey, String appId){
        return null;
    }

    private String method;
    WxPayEnum(String method) {
        this.method = method;
    }

    @Override
    public String getType() {
        return this.name();
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    public static WxPayEnum getEnum(String method) {
        for (WxPayEnum em : values()) {
            if (em.getMethod() == method) {
                return em;
            }
        }
        return null;
    }
}
