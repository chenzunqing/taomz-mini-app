package com.taomz.mini.apps.util.enums.login;

import com.taomz.mini.apps.util.enums.ErrorMessageEnum;

/**
 * @author : wangling
 * @version V1.0
 * @title : LoginErrorMsgEnum
 * @Package : com.taomz.mini.apps.util.enums.login
 * @Description: 登录错误信息枚举
 * @date 2020/11/18 13:21
 **/
public enum LoginErrorMsgEnum {
    WXLOGIN_ERRCODE_1("-1", "系统繁忙，此时请开发者稍候再试"),
    WXLOGIN_ERRCODE_SUCCESS("0", "请求成功"),
    WXLOGIN_ERRCODE_40029("40029", "code 无效"),
    WXLOGIN_ERRCODE_45011("45011", "操作频繁，请稍后再试"),
    WX_LOGIN_FAILED("10002", "微信认证失败"),
    WXLOGIN_ERRCODE_40163("40163", "code已被使用"),
    USER_PHONE_REGISTERED("10001", "该手机号已注册"),
    OPENID_NULL("10004", "openId获取失败"),
    OPENID_HAVEND("10005", "openId已校验，不能重复新增"),
    PARAMETER_NULL("10006", "参数为空"),
    USERBASEIN_ALIPAY_FAILED("10011", "获取用户信息失败");


    private String desc;
    private String code;

    LoginErrorMsgEnum(String code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }

}
