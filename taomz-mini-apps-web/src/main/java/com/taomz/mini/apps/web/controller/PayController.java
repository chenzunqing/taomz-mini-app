package com.taomz.mini.apps.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taomz.mini.apps.service.PayService;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.pay.PayOrder;
import com.taomz.mini.apps.web.controller.base.BaseController;
import com.taomz.sha.util.response.BaseResponseModel;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : WxPayController
 * @Package : com.taomz.mini.apps.web.controller
 * @Description: 微信支付
 * @date 2020/11/23 14:47
 **/
@RestController
public class PayController extends BaseController {

    @Autowired
    private PayService payService;

    /**
     * 微信支付
     * @param order {@link PayOrder}
     * @return {@link String}
     * @throws ExceptionWrapper
     */
    @PostMapping("/yf/pay")
    public BaseResponseModel wxPay(@Valid @RequestBody PayOrder order) throws ExceptionWrapper {

        return new BaseResponseModel().warpSuccess().setContent(payService.toPay(order));
    }

    /**
     * 微信支付查询
     * @param order {@link PayOrder}
     * @return {@link String}
     * @throws ExceptionWrapper
     */
    @PostMapping("/yf/pay/query")
    public BaseResponseModel wxPayQuery(@Valid @RequestBody PayOrder order) throws ExceptionWrapper {
        return payService.orderQuery(order);
    }

    /**
     * 微信支付关闭订单
     * @param order {@link PayOrder}
     * @return {@link String}
     * @throws ExceptionWrapper
     */
    @PostMapping("/yf/pay/close")
    public BaseResponseModel wxPayClose(@Valid @RequestBody PayOrder order) throws ExceptionWrapper {
        return new BaseResponseModel().warpSuccess().setContent(payService.closeOrder(order));
    }
}
