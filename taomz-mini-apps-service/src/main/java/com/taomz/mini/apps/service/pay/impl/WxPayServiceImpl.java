package com.taomz.mini.apps.service.pay.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.service.pay.WxPayService;
import com.taomz.mini.apps.util.IpUtil;
import com.taomz.mini.apps.util.TransactionType;
import com.taomz.mini.apps.util.enums.ErrorMessageEnum;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.pay.PayOrder;
import com.taomz.mini.apps.util.pay.RefundOrder;
import com.taomz.mini.apps.util.pay.WxPayEnum;
import com.taomz.mini.apps.util.pay.WxPayRequestUtil;
import com.taomz.mini.apps.util.pay.WxPayUtil;
import com.taomz.sha.util.response.BaseResponseModel;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : WxPayServiceImpl
 * @Package : com.taomz.mini.apps.service.pay.impl
 * @Description: 微信支付服务
 * @date 2020/11/23 10:52
 **/
public abstract class WxPayServiceImpl implements WxPayService {

    /**
     * 微信请求地址
     */
    private static final String URI = "https://api.mch.weixin.qq.com/";

    @Override
    public JSONObject toPay(PayOrder order) throws ExceptionWrapper {
        order.setAppId(getAppId((WxPayEnum) order.getType()));
        Map<String, Object> param = getPublicParameters((WxPayEnum) order.getType());
        // 设备号
        param.put("device_info", "WEB");
        // 总金额
        param.put("total_fee", order.getPrice().multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString());
        // 终端IP
        param.put("spbill_create_ip", IpUtil.LOCAL_IP);
        // 通知地址
        param.put("notify_url", order.getNotifyUrl());
        // 商户订单号
        param.put("out_trade_no", order.getOutTradeNo());
        // 附加数据
        param.put("attach", order.getAddition());
        // 订单有效时间
        param.put("time_expire", DateUtil.format(order.getExpirationTime(), DatePattern.PURE_DATETIME_PATTERN));
        ((WxPayEnum) order.getType()).setAttribute(param, order);
        // 签名
        param.put("sign", WxPayUtil.createSign(param, getKeyStore()));
        JSONObject response = WxPayRequestUtil.wechatRequest(
                getReqUrl((WxPayEnum) order.getType()),
                WxPayUtil.getMap2Xml(param));
        if (WxPayUtil.checkWXSign(response, getKeyStore())) {
            return ((WxPayEnum) order.getType()).getResult(response, order.getOutTradeNo(), getKeyStore(), order.getAppId());
        }
        throw new ExceptionWrapper(
                response.getString(WxPayUtil.RESULT_CODE),
                response.getString(WxPayUtil.ERR_CODE_DES)
        );
    }

    @Override
    public BaseResponseModel orderQuery(PayOrder order) throws ExceptionWrapper {
        Map<String, Object> param = getPublicParameters((WxPayEnum) order.getType());
        // 商户订单号
        param.put("out_trade_no", order.getOutTradeNo());
        // 签名
        param.put("sign", WxPayUtil.createSign(param, getKeyStore()));
        JSONObject response = WxPayRequestUtil.wechatRequest(
                getReqUrl(WxPayEnum.QUERY),
                WxPayUtil.getMap2Xml(param));
        if (WxPayUtil.checkWXSign(response, getKeyStore())) {
            return new BaseResponseModel().warpSuccess().setContent(response);
        }
        throw new ExceptionWrapper(ErrorMessageEnum.WX_PAY_SIGN_ERROR.getMessage());
    }

    @Override
    public JSONObject closeOrder(PayOrder order) throws ExceptionWrapper {
        Map<String, Object> param = getPublicParameters((WxPayEnum) order.getType());
        // 商户订单号
        param.put("out_trade_no", order.getOutTradeNo());
        // 签名
        param.put("sign", WxPayUtil.createSign(param, getKeyStore()));
        JSONObject response = WxPayRequestUtil.wechatRequest(
                getReqUrl(WxPayEnum.QUERY),
                WxPayUtil.getMap2Xml(param));
        if (WxPayUtil.checkWXSign(response, getKeyStore())) {
            return response;
        }
        throw new ExceptionWrapper(ErrorMessageEnum.WX_PAY_SIGN_ERROR.getMessage());
    }

    @Override
    public JSONObject refundOrder(RefundOrder order) throws ExceptionWrapper {
        return null;
    }

    /**
     * 获取公众平台appId
     * @return {@link String}
     */
    public abstract String getPublicAppId();

    /**
     * 获取小程序appId
     * @return {@link String}
     */
    public abstract String getMiniAppId();

    /**
     * 获取开放平台appId
     * @return {@link String}
     */
    public abstract String getOpenAppId();

    /**
     * 获取商户号
     * @return {@link String}
     */
    public abstract String getMchId();

    /**
     * 获取公众平台appId
     * @return {@link String}
     */
    public abstract String getKeyStore();

    /**
     * 获取公共参数
     * @return {@link Map}
     */
    public Map<String, Object> getPublicParameters(WxPayEnum wxPayEnum) {
        Map<String, Object> parameters = MapUtil.newTreeMap(null);
        // appId
        parameters.put("appid", getAppId(wxPayEnum));
        // 商户号
        parameters.put("mch_id", getMchId());
        parameters.put("nonce_str", WxPayUtil.getRandomString(16));
        return parameters;
    }

    private String getReqUrl(TransactionType transactionType) {
        return URI + transactionType.getMethod();
    }

    /**
     * 获取appId
     * @return {@link String}
     */
    private String getAppId(WxPayEnum wxPayEnum) {
        switch (wxPayEnum) {
            case MINI:
                return getMiniAppId();
            case APP:
                return getPublicAppId();
            default:
                return getOpenAppId();
        }
    }
}
