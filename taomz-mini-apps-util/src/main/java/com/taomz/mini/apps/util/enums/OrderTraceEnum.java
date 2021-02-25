package com.taomz.mini.apps.util.enums;

import lombok.Getter;

/**
 * 操作轨迹
 *
 * @author Wei.Guang
 * @create 2018-06-24 15:53
 **/
@Getter
public enum OrderTraceEnum {
    CREATE((short) 0, "创建订单"),
    PAIDING((short) 1, "订单支付"),
    PAID((short) 2, "支付完成"),
    DELIVERED((short) 3, "订单发货"),
    SUCCESS((short) 4, "交易完成"),
    CANCEL((short) 5, "取消订单"),
    REFUND((short) 6, "退款"),
    RETURNGOODS((short) 7, "待退货"),
    CLOSE((short) 8, "交易关闭"),
    WAITREFUND((short) 9, "待退款/已退货");

    private Short code;
    private String message;

    OrderTraceEnum(Short code, String message) {
        this.code = code;
        this.message = message;
    }
}
