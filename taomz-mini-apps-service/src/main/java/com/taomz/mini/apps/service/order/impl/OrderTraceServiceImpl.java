package com.taomz.mini.apps.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.OrderTraceMapper;
import com.taomz.mini.apps.model.OrderTrace;
import com.taomz.mini.apps.service.order.OrderTraceService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 订单操作轨迹表 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
@Service
public class OrderTraceServiceImpl extends ServiceImpl<OrderTraceMapper, OrderTrace> implements OrderTraceService {

    @Override
    public void saveTrace(String userId, String orderCode, Short operate, Short afterOperate) {
        OrderTrace orderTrace = new OrderTrace();
        Date date = new Date();
        orderTrace.setOrderCode(orderCode);
        orderTrace.setOperate(operate == null ? "" : Short.toString(operate));
        orderTrace.setOpeTrace(afterOperate == null ? "" : Short.toString(afterOperate));
        orderTrace.setUserId(userId);
        orderTrace.setCreateTime(date);
        save(orderTrace);
    }
}
