package com.taomz.mini.apps.util.enums.activity;

import lombok.Getter;

/**
 * @Description: 活动状态枚举
 * @Author: liaoxiaoli
 * @CreateDate: 2018/8/2 16:38
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/8/2 16:38
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Getter
public enum StateEnum {
    STARTING(1, "进行中"),
    NOT_START(2, "未开始"),
    OVER(3, "已结束"),
    APPLYING(4, "报名中"),
    ERROR(-1, "错误");

    private String stringValue;
    private int intValue;

    //构造函数必须为private的,防止意外调用
    StateEnum(int intValue, String stringValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }

    public static StateEnum getEnum(int intValue) {
        for (StateEnum em : values()) {
            if (em.getIntValue() == intValue) {
                return em;
            }
        }
        return ERROR;
    }
}
