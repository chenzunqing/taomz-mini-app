package com.taomz.mini.apps.util.enums;

/**
 * 错误枚举示范类
 */
public enum ErrorMessageEnum {
    已预约(100001, "已预约"),
    活动不存在(100002, "活动不存在"),
    活动已下架(100003, "活动已下架"),


    ERROR(000000, "系统错误"),
    SUCCESS(200, "请求成功"),
    FAILURE(500, "请求失败"),
    EXCEPTION(1001, "系统异常"),
    OPENID_IS_NULL(1002,"微信账号已校验"),
    OPERATE_FREQUENTLY(1003, "操作太频繁，请稍候尝试"),

    WX_PAY_ERROR(20001,"微信支付异常"),
    WX_PAY_SIGN_ERROR(20002,"微信支付签名异常"),
    WX_PAY_SIGN_NULL(20003,"微信支付返回签名为空"),;

    private String message;

    private int code;

    /**
     * 构造函数必须为private的,防止意外调用
     *
     * @param code
     * @param message
     */
    ErrorMessageEnum(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public static ErrorMessageEnum getEnum(int code) {
        for (ErrorMessageEnum em : values()) {
            if (em.getCode() == code) {
                return em;
            }
        }
        return ERROR;
    }

    public String getResult() {
        return "结果码：" + this.code + "，结果描述：" + this.message;
    }
}
