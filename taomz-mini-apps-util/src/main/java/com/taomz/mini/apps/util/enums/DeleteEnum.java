package com.taomz.mini.apps.util.enums;

import lombok.Getter;

/**
 * Created by liaoxiaoli on 2018/5/22.
 */
@Getter
public enum DeleteEnum {
    INVLID(0, "无效"),
    EFFECTIVE(1, "有效"),
    ERROR(-1, "错误");

    private String stringValue;
    private int intValue;

    //构造函数必须为private的,防止意外调用
    DeleteEnum(int intValue, String stringValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    public static DeleteEnum getEnum(int intValue) {
        for (DeleteEnum em : values()) {
            if (em.getIntValue()== intValue) {
                return em;
            }
        }
        return ERROR;
    }
}
