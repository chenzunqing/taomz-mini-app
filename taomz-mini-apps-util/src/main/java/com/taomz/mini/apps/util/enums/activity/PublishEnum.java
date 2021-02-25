package com.taomz.mini.apps.util.enums.activity;

import lombok.Getter;

/**
 * Created by liaoxiaoli on 2018/5/30.
 */
@Getter
public enum PublishEnum {
    UP(1, "上架"),
    DOWN(0, "下架"),
    BRANDS_EXPIRED_DOWN(2, "品牌过期下架"),
    ERROR(-1, "错误");

    private String stringValue;
    private int intVlue;

    //构造函数必须为private的,防止意外调用
    private PublishEnum(int intVlue, String stringValue) {

        this.stringValue = stringValue;
        this.intVlue = intVlue;
    }


    public static PublishEnum getEnum(int intVlue) {
        for (PublishEnum em : values()) {
            if (em.getIntVlue() == intVlue) {
                return em;
            }
        }
        return ERROR;
    }
}
