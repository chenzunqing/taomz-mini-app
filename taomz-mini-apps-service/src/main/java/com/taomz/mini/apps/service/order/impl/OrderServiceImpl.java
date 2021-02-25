package com.taomz.mini.apps.service.order.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.taomz.mini.apps.dao.mapper.InvoiceHistoryMapper;
import com.taomz.mini.apps.model.InvoiceHistory;
import com.taomz.mini.apps.service.order.OrderService;
import com.taomz.mini.apps.util.enums.InvoiceHistoryStatusEnum;
import com.taomz.mini.apps.util.enums.OrderStatusEnum;
import jodd.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : OrderServiceImpl
 * @Package : com.taomz.mini.apps.service.order.impl
 * @Description: 订单服务
 * @date 2020/11/25 11:13
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private InvoiceHistoryMapper invoiceHistoryMapper;

    @Override
    public JSONObject switchFlag(String orderCode, Short orderStatus, Date paymentTime, BigDecimal invoiceAmount) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isBlank(orderCode) ||  Objects.isNull(orderStatus) || Objects.isNull(paymentTime)) {
            return null;
        }
        List<InvoiceHistory> invoiceHistories = invoiceHistoryMapper.selectList(
                Wrappers.lambdaQuery(InvoiceHistory.class)
                        .eq(InvoiceHistory::getOrderId, orderCode)
                        .eq(InvoiceHistory::getKptype, Short.valueOf("1"))
                        .eq(InvoiceHistory::getFlag, Short.valueOf("0")));
        if (CollectionUtils.isNotEmpty(invoiceHistories)) {
            if (OrderStatusEnum.REFUND.getCode().equals(orderStatus)) {
                jsonObject.put("allowInvoice", (short) 0);
            } else if (InvoiceHistoryStatusEnum.资质不通过.flag.equals(invoiceHistories.get(0).getStatus())) {
                jsonObject.put("allowInvoice", (short) 0);
                jsonObject.put("invoiceStatus", invoiceHistories.get(0).getStatus());
                jsonObject.put("invoiceId", invoiceHistories.get(0).getInvoiceId());
            } else {
                jsonObject.put("allowInvoice", (short) 2);
                jsonObject.put("invoiceStatus", invoiceHistories.get(0).getStatus());
                jsonObject.put("invoiceId", invoiceHistories.get(0).getInvoiceId());
            }
        } else {
            if (BigDecimal.valueOf(0.01).compareTo(invoiceAmount) >= 0
                    || DateUtil.compare(paymentTime, DateUtil.parse("2019-08-13 23:59:59", DatePattern.NORM_DATETIME_PATTERN)) <= 0) {
                jsonObject.put("allowInvoice", (short) 0);
            } else if (OrderStatusEnum.paySuccessJudge(orderStatus)) {
                jsonObject.put("allowInvoice", (short) 1);
            } else {
                jsonObject.put("allowInvoice", (short) 0);
            }
        }
        return jsonObject;
    }
}
