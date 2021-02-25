package com.taomz.mini.apps.util.enums;

import lombok.Getter;

/**
 * Created by liaoxiaoli on 2020/12/03.
 */
@Getter
public enum MarketingActivityTypeEnum {
    MARKETING_ACTIVITY_BRAND_CALL("品牌打榜"),
    MARKETING_ACTIVITY_SHARE_FISSION("分享裂变");

    private String stringValue;

    //构造函数必须为private的,防止意外调用
    MarketingActivityTypeEnum(String stringValue) {
        this.stringValue = stringValue;
    }
    public static MarketingActivityTypeEnum getEnum(String stringValue) {
        for (MarketingActivityTypeEnum em : values()) {
            if (em.getStringValue().equals(stringValue)) {
                return em;
            }
        }
        return null;
    }
}
