package com.taomz.mini.apps.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 取消今天以前未支付的会员续费订单
     *
     * @param currentUserId 用户ID
     * @param type          订单类型
     * @param startTime     开始时间
     * @param beforeStatus  之前订单状态
     * @param afterStatus   之后订单状态
     * @return
     */
    int modifyOrderStatus(@Param("userId") int currentUserId, @Param("type") Short type, @Param("startTime") Date startTime,
                          @Param("beforeStatus") Short beforeStatus, @Param("afterStatus") Short afterStatus);

    /**
     * 查询指定用户时间内、订单的类型和订单的状态是否存订单
     *
     * @param currentUserId 用户ID
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param type          订单类型
     * @param status        订单状态
     * @return
     */
    List<String> queryUserNoPaidOrderCode(@Param("userId") int currentUserId, @Param("startTime") Date startTime,
                                          @Param("endTime") Date endTime, @Param("type") Short type, @Param("status") Short status);
}
