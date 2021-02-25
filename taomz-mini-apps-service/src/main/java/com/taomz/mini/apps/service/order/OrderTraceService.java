package com.taomz.mini.apps.service.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.OrderTrace;

/**
 * <p>
 * 订单操作轨迹表 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
public interface OrderTraceService extends IService<OrderTrace> {

    /**
     * 保存订单操作轨迹
     * @param userId
     * @param orderCode
     * @param operate
     * @param afterOperate
     */
    void saveTrace(String userId, String orderCode, Short operate, Short afterOperate);
}
