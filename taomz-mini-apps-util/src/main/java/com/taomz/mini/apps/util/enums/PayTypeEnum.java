package com.taomz.mini.apps.util.enums;

import lombok.Getter;

/**
 * 付款方式
 *
 * @author Wei.Guang
 * @create 2018-06-25 16:45
 **/
@Getter
public enum PayTypeEnum {

    ALI((short) 0, "支付宝"),
    WX((short) 1, "微信"),
    UNION((short) 2, "银行卡支付"),
    OFFLINE((short) 3, "线下付款");

    private Short code;
    private String message;

    PayTypeEnum(Short code, String message) {
        this.code = code;
        this.message = message;
    }

    public static PayTypeEnum getEnum(Short code) {
        for (PayTypeEnum em : values()) {
            if (em.getCode().equals(code)) {
                return em;
            }
        }
        return null;
    }
}
