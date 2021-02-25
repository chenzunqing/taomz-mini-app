package com.taomz.mini.apps.util.enums.activity;

import lombok.Getter;

@Getter
public enum JudgeFlagEnum {
    YES(1, "是"),  //可以
    NO(0, "否"),   //不可以
    ERROR(-1, "错误");

    private String stringValue;
    private int intValue;

    //构造函数必须为private的,防止意外调用
    JudgeFlagEnum(int intValue, String stringValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    public static JudgeFlagEnum getEnum(int intValue) {
        for (JudgeFlagEnum em : values()) {
            if (em.getIntValue()== intValue) {
                return em;
            }
        }
        return null;
    }
}
