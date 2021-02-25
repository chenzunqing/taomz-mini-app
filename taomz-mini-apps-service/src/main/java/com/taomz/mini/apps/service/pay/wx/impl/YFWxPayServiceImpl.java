package com.taomz.mini.apps.service.pay.wx.impl;

import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.service.pay.impl.WxPayServiceImpl;
import com.taomz.mini.apps.service.pay.wx.YFWxPayService;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.pay.PayOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : YFWxPayServiceImpl
 * @Package : com.taomz.mini.apps.service.pay.wx.impl
 * @Description: 仪菲微信支付
 * @date 2020/12/3 20:11
 **/
@Service(value = "yfWxPayService")
public class YFWxPayServiceImpl extends WxPayServiceImpl implements YFWxPayService {

    private static final String KEY_STORE = "95855A1229924DDCB222048977E192B5";

    @Value("${wx.yf.mini.appId}")
    private String miniAppId;

    @Value("${wx.yf.mchId}")
    private String mchId;

    @Override
    public JSONObject toPay(PayOrder order) throws ExceptionWrapper {
        return super.toPay(order);
    }

    @Override
    public String getPublicAppId() {
        return null;
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
    public String getMchId() {
        return mchId;
    }

    @Override
    public String getKeyStore() {
        return KEY_STORE;
    }
}
