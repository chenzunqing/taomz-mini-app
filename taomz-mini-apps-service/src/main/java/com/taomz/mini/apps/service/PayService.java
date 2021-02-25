package com.taomz.mini.apps.service;

import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.pay.PayOrder;
import com.taomz.sha.util.response.BaseResponseModel;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : PayService
 * @Package : com.taomz.mini.apps.service
 * @Description: 支付服务
 * @date 2020/12/7 16:08
 **/
public interface PayService {

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
}
