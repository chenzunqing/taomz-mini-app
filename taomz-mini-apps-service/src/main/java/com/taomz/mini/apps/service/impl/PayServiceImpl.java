package com.taomz.mini.apps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.service.PayService;
import com.taomz.mini.apps.service.pay.ali.YFAliPayService;
import com.taomz.mini.apps.service.pay.wx.YFWxPayService;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.pay.PayOrder;
import com.taomz.sha.util.response.BaseResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : PayServiceImpl
 * @Package : com.taomz.mini.apps.service.impl
 * @Description: 支付服务
 * @date 2020/12/7 16:09
 **/
@Service
public class PayServiceImpl implements PayService {

    private static final String YF_PLATFORM_ID = "EB9D1087050FF31D3D45909C3A48C260";

    @Autowired
    @Qualifier(value = "yfWxPayService")
    private YFWxPayService yfWxPayService;

    @Autowired
    @Qualifier(value = "yfAliPayService")
    private YFAliPayService yfAliPayService;


    @Override
    public JSONObject toPay(PayOrder order) throws ExceptionWrapper {
        if (StrUtil.isBlank(order.getTransactionType()) || Objects.isNull(order.getPayType())) {
            throw new ExceptionWrapper("支付类型/交易方式不能为空");
        }
        if (StrUtil.isBlank(order.getPlatformId())) {
            throw new ExceptionWrapper("平台ID不能为空");
        }
        if (StrUtil.isBlank(order.getOutTradeNo()) || Objects.isNull(order.getPrice())) {
            throw new ExceptionWrapper("参数不能为空");
        }
        // 获取交易类型
        order.setType(order.getPayType().getTransactionType(order.getTransactionType()));
        if (YF_PLATFORM_ID.equals(order.getPlatformId())) {
            switch (order.getPayType()) {
                case aliPay:
                    return yfAliPayService.toPay(order);
                case wxPay:
                    return yfWxPayService.toPay(order);
                default:
                    throw new ExceptionWrapper("支付类型错误！");
            }
        }
        return null;
    }

    @Override
    public BaseResponseModel orderQuery(PayOrder order) throws ExceptionWrapper {
        if (StrUtil.isBlank(order.getTransactionType()) || Objects.isNull(order.getPayType())) {
            throw new ExceptionWrapper("支付类型/交易方式不能为空");
        }
        if (StrUtil.isBlank(order.getPlatformId())) {
            throw new ExceptionWrapper("平台ID不能为空");
        }
        if (StrUtil.isBlank(order.getOutTradeNo())) {
            throw new ExceptionWrapper("参数不能为空");
        }
        // 获取交易类型
        order.setType(order.getPayType().getTransactionType(order.getTransactionType()));
        if (YF_PLATFORM_ID.equals(order.getPlatformId())) {
            switch (order.getPayType()) {
                case aliPay:
                    return yfAliPayService.orderQuery(order);
                case wxPay:
                    return  yfWxPayService.orderQuery(order);
                default:
                    throw new ExceptionWrapper("支付类型错误！");
            }
        }
        return null;
    }

    @Override
    public JSONObject closeOrder(PayOrder order) throws ExceptionWrapper {
        if (StrUtil.isBlank(order.getTransactionType()) || Objects.isNull(order.getPayType())) {
            throw new ExceptionWrapper("支付类型/交易方式不能为空");
        }
        if (StrUtil.isBlank(order.getPlatformId())) {
            throw new ExceptionWrapper("平台ID不能为空");
        }
        if (StrUtil.isBlank(order.getOutTradeNo())) {
            throw new ExceptionWrapper("参数不能为空");
        }
        // 获取交易类型
        order.setType(order.getPayType().getTransactionType(order.getTransactionType()));
        if (YF_PLATFORM_ID.equals(order.getPlatformId())) {
            switch (order.getPayType()) {
                case aliPay:
                    return yfAliPayService.closeOrder(order);
                case wxPay:
                    return  yfWxPayService.closeOrder(order);
                default:
                    throw new ExceptionWrapper("支付类型错误！");
            }
        }
        return null;
    }
}
