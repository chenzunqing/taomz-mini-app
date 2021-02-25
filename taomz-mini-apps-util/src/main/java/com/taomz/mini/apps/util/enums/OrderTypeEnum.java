package com.taomz.mini.apps.util.enums;

import lombok.Getter;

/**
 * 订单类型枚举
 *
 * @author Wei.Guang
 * @create 2018-06-25 14:59
 **/
@Getter
public enum OrderTypeEnum {

    ERROR((short) -2, "未知参数", ""),
    ALL((short) -1, "全部", ""),
    GENERAL((short) 0, "普通", ""),
    ACTIVITY((short) 1, "活动", "信息技术服务费"),
    COLLECTION((short) 2, "商品", ""),
    BRAND((short) 3, "品牌", "信息技术服务费"),
    MEMBER((short) 4, "会员", "信息技术服务费"),
    GROUP_SING_UP((short) 5, "团购报名", ""),
    GROU_ORDER((short) 6, "团购订单", ""),
    EXCELLENT_ORDER((short) 7, "优享会员", "信息技术服务费"),
    REFUND((short) 9, "退款", ""),
    Cash_Back((short) 10, "返现", "");

    private Short code;
    private String message;
    private String InvoiceGoodName;

    OrderTypeEnum(Short code, String message, String InvoiceGoodName) {
        this.code = code;
        this.message = message;
        this.InvoiceGoodName = InvoiceGoodName;
    }

    public static OrderTypeEnum getEnum(Short code) {
        for (OrderTypeEnum em : values()) {
            if (em.getCode().equals(code)) {
                return em;
            }
        }
        return ERROR;
    }
}
