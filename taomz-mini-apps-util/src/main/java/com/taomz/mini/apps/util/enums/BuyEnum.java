package com.taomz.mini.apps.util.enums;

import lombok.Getter;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : BuyEnum
 * @Package : com.taomz.mini.apps.util.enums
 * @Description:
 * @date 2020/11/23 17:53
 **/
@Getter
public enum  BuyEnum {

    CAN_BUY(1, "可购买"),
    CAN_NOT_BUY(0, "不可购买"),
    ERROR(-1, "错误");

    private String stringValue;
    private int intValue;

    //构造函数必须为private的,防止意外调用
    BuyEnum(int intValue, String stringValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    public static BuyEnum getEnum(int intValue) {
        for (BuyEnum em : values()) {
            if (em.getIntValue()== intValue) {
                return em;
            }
        }
        return ERROR;
    }
}
