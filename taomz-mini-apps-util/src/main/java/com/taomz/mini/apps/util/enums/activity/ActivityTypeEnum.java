package com.taomz.mini.apps.util.enums.activity;

import lombok.Getter;

/**
 * @Description: 活动类型枚举
 * @Author: liaoxiaoli
 * @CreateDate: 2018/8/12 15:47
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/8/12 15:47
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Getter
public enum ActivityTypeEnum {
    BRAND_ACTIVITY("品牌活动"),
    MEMBER_ACTIVITY("会员活动"),
    COMPLEX_ACTIVITY("综合活动"),
    PROFESSIONAL_AUDIENCE_ACTIVITY("专业观众活动"),
    ERROR("错误");
    private String stringValue;

    //构造函数必须为private的,防止意外调用
    private ActivityTypeEnum(String stringValue) {
        this.stringValue = stringValue;
    }
    public static ActivityTypeEnum getEnum(String name) {
        for (ActivityTypeEnum em : values()) {
            if (em.name().equals(name)) {
                return em;
            }
        }
        return ERROR;
    }
}
