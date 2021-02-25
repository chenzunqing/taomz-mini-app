package com.taomz.mini.apps.service.pay.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.taomz.mini.apps.service.pay.AliPayService;
import com.taomz.mini.apps.util.TransactionType;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.pay.AliPayEnum;
import com.taomz.mini.apps.util.pay.PayOrder;
import com.taomz.mini.apps.util.pay.RefundOrder;
import com.taomz.mini.apps.util.pay.WxPayUtil;
import com.taomz.sha.util.response.BaseResponseModel;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : AliPayServiceImpl
 * @Package : com.taomz.mini.apps.service.pay.impl
 * @Description: 支付宝支付服务
 * @date 2020/12/4 9:59
 **/
@Slf4j
public abstract class AliPayServiceImpl implements AliPayService {

    /**
     * 正式测试环境支付网关
     */
    private static final String HTTPS_REQ_URL = "https://openapi.alipay.com/gateway.do";

    /**
     * 沙箱测试环境支付网关
     */
    private static final String DEV_REQ_URL = "https://openapi.alipaydev.com/gateway.do";

    private static final String SIGN = "sign";

    private static final String FORMAT = "JSON";

    private static final String CHARSET = "utf-8";

    private static final String SIGN_TYPE_RSA2 = "RSA2";

    @Override
    public JSONObject toPay(PayOrder order) throws ExceptionWrapper {
        JSONObject resJson = new JSONObject();
        switch ((AliPayEnum) order.getType()) {
            case APP:
                try {
                    Map<String, Object> orderInfo = getAppPayParameter(order.getType());
                    orderInfo.put("biz_content", getBizContent(order));
                    orderInfo.put("notify_url", order.getNotifyUrl());
                    String sign = AlipaySignature.rsaSign(WxPayUtil.parameterText(orderInfo, "&", SIGN), getPrivateKey(), getCharSet(), getSignType());
                    orderInfo.put(SIGN, sign);
                    resJson.put("urlParam", WxPayUtil.getMapToParameters(orderInfo));
                } catch (AlipayApiException e) {
                    log.error("支付宝APP支付操作失败：{}，参数：{}", ExceptionUtil.getMessage(e), JSON.toJSONString(order));
                    throw new ExceptionWrapper(ExceptionUtil.getMessage(e));
                }
                break;
            case PAGE:
                try {
                    AlipayTradePagePayRequest request = (AlipayTradePagePayRequest) ((AliPayEnum) order.getType()).getRequest(getBizContent(order));
                    request.setNotifyUrl(order.getNotifyUrl());
                    request.setReturnUrl(order.getReturnUrl());
                    AlipayTradePagePayResponse response = getPublicParameters().pageExecute(request);
                    if (response.isSuccess()) {
                        resJson.put("body", response.getBody());
                        break;
                    }
                    throw new ExceptionWrapper(response.getSubCode(), response.getSubMsg());
                } catch (AlipayApiException e) {
                    log.error("支付宝操作失败：{}, 方式：{}，参数：{}",
                            ExceptionUtil.getMessage(e),
                            order.getType().getMethod(),
                            JSON.toJSONString(order));
                    throw new ExceptionWrapper(ExceptionUtil.getMessage(e));
                }
            case WAP:
                try {
                    AlipayTradeWapPayRequest request = (AlipayTradeWapPayRequest) ((AliPayEnum) order.getType()).getRequest(getBizContent(order));
                    request.setNotifyUrl(order.getNotifyUrl());
                    request.setReturnUrl(order.getReturnUrl());
                    AlipayTradeWapPayResponse response = getPublicParameters().pageExecute(request);
                    if (response.isSuccess()) {
                        resJson.put("body", response.getBody());
                        break;
                    }
                    throw new ExceptionWrapper(response.getSubCode(), response.getSubMsg());
                } catch (AlipayApiException e) {
                    log.error("支付宝操作失败：{}, 方式：{}，参数：{}",
                            ExceptionUtil.getMessage(e),
                            order.getType().getMethod(),
                            JSON.toJSONString(order));
                    throw new ExceptionWrapper(ExceptionUtil.getMessage(e));
                }
        }
        resJson.put("outTradeNo", order.getOutTradeNo());
        return resJson;
    }

    @Override
    public BaseResponseModel orderQuery(PayOrder order) throws ExceptionWrapper {
        try {
            AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();
            AlipayTradeQueryModel model = new AlipayTradeQueryModel();
            model.setOutTradeNo(order.getOutTradeNo());
            queryRequest.setBizModel(model);
            AlipayResponse alipayResponse = getPublicParameters().execute(queryRequest);
            if (alipayResponse.isSuccess()) {
                return new BaseResponseModel().warpSuccess().setContent(alipayResponse.getBody());
            }
            throw new ExceptionWrapper(alipayResponse.getSubCode(), alipayResponse.getSubMsg());
        } catch (AlipayApiException e) {
            log.error("支付宝操作失败：{}, 方式：{}，参数：{}",
                    ExceptionUtil.getMessage(e),
                    order.getType().getMethod(),
                    JSON.toJSONString(order));
            throw new ExceptionWrapper(ExceptionUtil.getMessage(e));
        }
    }

    @Override
    public JSONObject closeOrder(PayOrder order) throws ExceptionWrapper {
        try {
            AlipayTradeCloseRequest closeRequest = new AlipayTradeCloseRequest();
            AlipayTradeCloseModel model = new AlipayTradeCloseModel();
            model.setOutTradeNo(order.getOutTradeNo());
            closeRequest.setBizModel(model);
            AlipayResponse alipayResponse = getPublicParameters().execute(closeRequest);
            if (alipayResponse.isSuccess()) {
                JSONObject resJson = new JSONObject();
                resJson.put("body", alipayResponse.getBody());
                return resJson;
            }
            throw new ExceptionWrapper(alipayResponse.getSubCode(), alipayResponse.getSubMsg());
        } catch (AlipayApiException e) {
            log.error("支付宝操作失败：{}, 方式：{}，参数：{}",
                    ExceptionUtil.getMessage(e),
                    order.getType().getMethod(),
                    JSON.toJSONString(order));
            throw new ExceptionWrapper(ExceptionUtil.getMessage(e));
        }
    }

    @Override
    public JSONObject refundOrder(RefundOrder order) throws ExceptionWrapper {
        try {
            AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            model.setTradeNo(order.getTradeNo());
            model.setOutTradeNo(order.getOutTradeNo());
            model.setOutRequestNo(order.getOutRequestNo());
            model.setRefundAmount(order.getRefundAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            model.setOperatorId(order.getOperatorId());
            model.setRefundReason(order.getRefundReason());
            refundRequest.setBizModel(model);
            AlipayResponse alipayResponse = getPublicParameters().execute(refundRequest);
            if (alipayResponse.isSuccess()) {
                JSONObject resJson = new JSONObject();
                resJson.put("body", alipayResponse.getBody());
                return resJson;
            }
            throw new ExceptionWrapper(alipayResponse.getSubCode(), alipayResponse.getSubMsg());
        } catch (AlipayApiException e) {
            log.error("支付宝操作失败：{}, 方式：{}，参数：{}",
                    ExceptionUtil.getMessage(e),
                    AliPayEnum.REFUND.getMethod(),
                    JSON.toJSONString(order));
            throw new ExceptionWrapper(ExceptionUtil.getMessage(e));
        }
    }

    /**
     * 蚂蚁金服AppId
     * @return {@link String}
     */
    public abstract String getAppId();

    /**
     * 编码方式
     * @return {@link String}
     */
    public String getCharSet() {
        return CHARSET;
    }

    /**
     * 应用私钥
     * @return {@link String}
     */
    public abstract String getPrivateKey();

    /**
     * 支付宝公钥
     * @return {@link String}
     */
    public abstract String getPublicKey();

    /**
     * 返回格式
     */
    public String getFormat() {
        return FORMAT;
    }

    /**
     * 签名方式
     */
    public String getSignType() {
        return SIGN_TYPE_RSA2;
    }

    /**
     * 环境判断
     */
    public abstract Boolean isTest();

    public String getReqUrl() {
        return isTest() ? DEV_REQ_URL : HTTPS_REQ_URL;
    }

    /**
     * 获取公共请求参数
     *
     * @return 放回公共请求参数
     */
    private AlipayClient getPublicParameters() {
        return new DefaultAlipayClient(getReqUrl(),
                getAppId(),
                getPrivateKey(),
                getFormat(),
                getCharSet(),
                getPublicKey(),
                getSignType());
    }

    /**
     * 支付宝创建订单信息bizContent
     * create the order info
     *
     * @param order 支付订单
     * @return 返回支付宝预下单信息
     * @see PayOrder 支付订单信息
     */
    private String getBizContent(PayOrder order) {
        Map<String, Object> bizContent = new TreeMap<>();
        bizContent.put("body", order.getBody());
        bizContent.put("subject", order.getSubject());
        bizContent.put("out_trade_no", order.getOutTradeNo());
        bizContent.put("total_amount", order.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        bizContent.put("time_expire", DateUtil.format(order.getExpirationTime(), DatePattern.NORM_DATETIME_MINUTE_PATTERN));
        bizContent.put("passback_params", order.getAddition());
        switch ((AliPayEnum) order.getType()) {
            case APP:
                bizContent.put("product_code", "QUICK_MSECURITY_PAY");
                break;
            case PAGE:
                bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
                bizContent.put("qr_pay_mode", order.getQrPayMode());
                bizContent.put("qrcode_width", order.getQrcodeWidth());
                break;
            case WAP:
                bizContent.put("product_code", "QUICK_WAP_WAY");
                bizContent.put("quit_url", order.getQuitUrl());
                break;
        }
        return JSON.toJSONString(bizContent);
    }

    /**
     * 获取公共请求参数
     *
     * @param transactionType 交易类型
     * @return 放回公共请求参数
     */
    private Map<String, Object> getAppPayParameter(TransactionType transactionType) {
        Map<String, Object> orderInfo = new TreeMap<>();
        orderInfo.put("app_id", getAppId());
        orderInfo.put("charset", getCharSet());
        orderInfo.put("format", getFormat());
        orderInfo.put("method", transactionType.getMethod());
        orderInfo.put("sign_type", getSignType());
        orderInfo.put("timestamp", DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        orderInfo.put("version", "1.0");
        return orderInfo;
    }
}
