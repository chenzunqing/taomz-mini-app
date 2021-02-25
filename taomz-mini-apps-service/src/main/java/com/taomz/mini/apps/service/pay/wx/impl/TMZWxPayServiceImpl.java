package com.taomz.mini.apps.service.pay.wx.impl;

import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.service.pay.impl.WxPayServiceImpl;
import com.taomz.mini.apps.service.pay.wx.TMZWxPayService;
import com.taomz.mini.apps.util.StringUtil;
import com.taomz.mini.apps.util.enums.OrderTypeEnum;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.pay.PayOrder;
import com.taomz.mini.apps.util.pay.WxPayUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : TMZWxPayServiceImpl
 * @Package : com.taomz.mini.apps.service.pay.wx.impl
 * @Description: 淘美妆微信支付
 * @date 2020/12/3 19:05
 **/
@Service(value = "tmzWxPayService")
public class TMZWxPayServiceImpl extends WxPayServiceImpl implements TMZWxPayService {

    private static final String KEY_STORE = "16EA9E4366662B401EBC0EB9B38C1F35";

    @Value("${wx.appId}")
    private String miniAppId;

    @Value("${wx.mchId}")
    private String mchId;

    @Override
    public JSONObject toPay(PayOrder order) throws ExceptionWrapper {
        try {
            return super.toPay(order);
        } catch (ExceptionWrapper e) {
            if (WxPayUtil.FAIL_201.equals(e.getCustomErrMsg())) {
                closeOrder(order);
                // 生成订单号 (1:生成订单)
                String orderCode = StringUtil.generateOrderNo(String.valueOf(OrderTypeEnum.MEMBER.getCode()));
                order.setOutTradeNo(orderCode);
                return super.toPay(order);
            }
            throw new ExceptionWrapper(e.getCode(), e.getCustomErrMsg());
        }
    }

    @Override
    public String getMiniAppId() {
        return miniAppId;
    }

    @Override
    public String getOpenAppId() {
        return null;
    }

    @Override
    public String getPublicAppId() {
        return null;
    }

    @Override
    public String getMchId() {
        return mchId;
    }

    @Override
    public String getKeyStore() {
        return KEY_STORE;
    }
}
