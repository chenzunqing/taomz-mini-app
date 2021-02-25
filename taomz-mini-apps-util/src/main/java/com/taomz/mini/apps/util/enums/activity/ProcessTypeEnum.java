package com.taomz.mini.apps.util.enums.activity;

import lombok.Getter;

/**
 * @Description: 推送类型枚举
 * @Author: liaoxiaoli
 * @CreateDate: 2018/7/4 15:29
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/7/4 15:29
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Getter
public enum ProcessTypeEnum {
    RICH_TEXT(1, "富文本编辑"),
    TIME_LINE(2, "时间轴编辑框"),
    ERROR(-1, "错误");

    private String stringValue;
    private int intValue;

    //构造函数必须为private的,防止意外调用
    ProcessTypeEnum(int intValue, String stringValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
}
