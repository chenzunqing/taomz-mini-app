package com.taomz.mini.apps.util.enums;

import cn.hutool.core.collection.ListUtil;
import lombok.Getter;

import java.util.List;

/**
 * 订单状态
 *
 * @author Wei.Guang
 * @create 2018-06-24 13:58
 **/
@Getter
public enum OrderStatusEnum {

    REFUNDALL((short) -2, "退款列表"),
    ALL((short) -1, "全部"),
    NEW((short) 0, "待付款"),
    UNDELIVERED((short) 1, "待发货"),
    DELIVERED((short) 2, "待收货"),
    SUCCESS((short) 3, "付款完成"),
    CANCEL((short) 4, "订单取消"),
    OVERTIME((short) 5, "付款超时"),
    RETURNGOODS((short) 6, "待退货"),
    CLOSE((short) 7, "交易完成"),
    WAITREFUND((short) 8, "退款中"),
    REFUND((short) 9, "退款成功"),
    REFUSEREFUND((short) 10, "退款拒绝"),
    ASSESS((short) 11, "待评价"),
    OFFLINECONFIRM((short) 98, "待审核"),
    OFFLINECONFUSE((short) 99, "审核拒绝");

    private Short code;
    private String message;

    private final static List<Short> paySuccessEnums = ListUtil.toList(new Short[]{
            UNDELIVERED.code,
            DELIVERED.code,
            SUCCESS.code,
            RETURNGOODS.code,
            CLOSE.code,
            REFUSEREFUND.code,
            OrderStatusEnum.ASSESS.code

    });

    OrderStatusEnum(Short code, String message) {
        this.code = code;
        this.message = message;
    }

    public static OrderStatusEnum getEnum(short intValue) {
        for (OrderStatusEnum em : values()) {
            if (em.getCode() == intValue) {
                return em;
            }
        }
        return null;
    }

    public static boolean containEnum(short intValue) {
        for (OrderStatusEnum em : values()) {
            if (em.getCode() == intValue) {
                return true;
            }
        }
        return false;
    }

    public static boolean paySuccessJudge(Short status) {
        return paySuccessEnums.contains(status);
    }
}
