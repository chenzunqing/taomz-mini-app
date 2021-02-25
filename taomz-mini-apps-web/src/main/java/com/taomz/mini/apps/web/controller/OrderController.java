package com.taomz.mini.apps.web.controller;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.param.OrderItemParam;
import com.taomz.mini.apps.service.order.MemberOrderService;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.pay.PayOrder;
import com.taomz.mini.apps.web.controller.base.BaseController;
import com.taomz.mini.apps.web.util.PropertyFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : OrderController
 * @Package : com.taomz.mini.apps.web.controller
 * @Description: 订单
 * @date 2020/11/23 19:46
 **/
@RestController
public class OrderController extends BaseController {

    @Value("${shop_spu_id}")
    private Long shopSpuId;

    @Value("${shop_sku_id}")
    private Long shopSkuId;

    @Autowired
    private MemberOrderService memberOrderService;

    /**
     * 创建订单
     * @return
     * @throws ParseException
     * @throws ExceptionWrapper
     */
    @PostMapping(value = "/wx/user/order/renew/member/order_create")
    public String createMemberOrder() throws ParseException, ExceptionWrapper {
        return JSON.toJSONString(
                memberOrderService.createMemberOrder(
                        this.getCurrentUserId(),
                        new OrderItemParam().setSkuId(shopSkuId).setSpuId(shopSpuId)), DEFAULT_FEATURES);

    }

    /**
     * 支付
     * @param payOrder
     * @return
     * @throws ExceptionWrapper
     */
    @PostMapping(value = "/wx/user/order/renew/member/to_pay")
    public String toPay(@Valid @RequestBody PayOrder payOrder) throws ExceptionWrapper {
        String[] ignoreProps = new String[] {"orderCode"};
        return JSON.toJSONString(
                memberOrderService.toPay(payOrder, this.getCurrentUserId()),
                PropertyFilterFactory.create(ignoreProps),
                DEFAULT_FEATURES);

    }

    /**
     * 会员支付前续费页面
     * @return
     * @throws ExceptionWrapper
     */
    @PostMapping(value = "/wx/user/pay/member/detail")
    public String payMemberDetail() throws ExceptionWrapper {
        String[] ignoreProps = new String[] {"accountNo"};
        return JSON.toJSONString(
                memberOrderService.payMemberDetail(shopSkuId, shopSkuId, getCurrentUserId()),
                PropertyFilterFactory.create(ignoreProps),
                DEFAULT_FEATURES);

    }
}
