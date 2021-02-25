package com.taomz.mini.apps.service.order;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : OrderService
 * @Package : com.taomz.mini.apps.service.order
 * @Description: 订单接口
 * @date 2020/11/25 11:12
 **/
public interface OrderService {

    /**
     * 发票开票标志
     * @param orderCode
     * @param orderStatus
     * @param paymentTime
     * @param invoiceAmount
     * @return
     */
    JSONObject switchFlag(String orderCode, Short orderStatus, Date paymentTime, BigDecimal invoiceAmount);
}
