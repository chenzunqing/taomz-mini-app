package com.taomz.mini.apps.util.pay;

import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.sha.util.response.BaseResponseModel;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : PayService
 * @Package : com.taomz.mini.apps.service.pay
 * @Description: 基础支付服务接口
 * @date 2020/12/4 9:43
 **/

public interface BasePayService {

    /**
     * 支付
     * @return {@link String}
     */
    JSONObject toPay(PayOrder order) throws ExceptionWrapper;

    /**
     * 查询支付订单信息
     * @param order
     * @return
     * @throws ExceptionWrapper
     */
    BaseResponseModel orderQuery(PayOrder order) throws ExceptionWrapper;

    /**
     * 关闭支付订单
     * @param order
     * @return
     * @throws ExceptionWrapper
     */
    JSONObject closeOrder(PayOrder order) throws ExceptionWrapper;

    /**
     * 退款订单
     * @param order
     * @return
     * @throws ExceptionWrapper
     */
    JSONObject refundOrder(RefundOrder order) throws ExceptionWrapper;
}
