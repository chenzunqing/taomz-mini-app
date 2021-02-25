package com.taomz.mini.apps.util.reslut;

import com.taomz.mini.apps.util.enums.ErrorMessageEnum;

/**
 * 统一参数返回类
 *
 * @author : liaobing
 * @date : 2018/05/08
 */
public class AjaxResult {

    public static final int CODE_SUCCESS = ErrorMessageEnum.SUCCESS.getCode();

    public static final int CODE_FAILED = ErrorMessageEnum.FAILURE.getCode();

    /**
     * 返回 code
     */
    private int code;

    /**
     * 返回 消息提示
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    private AjaxResult(int code, Object data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public boolean isSuccess() {
        return CODE_SUCCESS == code;
    }

    public static final AjaxResult success() {
        return new AjaxResult(CODE_SUCCESS, null, ErrorMessageEnum.SUCCESS.getMessage());
    }

    public static final AjaxResult success(Object data) {
        return new AjaxResult(CODE_SUCCESS, data, ErrorMessageEnum.SUCCESS.getMessage());
    }

    public static final AjaxResult success(Object data, String message) {
        return new AjaxResult(CODE_SUCCESS, data, message);
    }

    public static final AjaxResult success(ErrorMessageEnum r, Object data) {
        return new AjaxResult(r.getCode(), data, r.getMessage());
    }

    public static final AjaxResult failed() {
        return new AjaxResult(CODE_FAILED, null, ErrorMessageEnum.FAILURE.getMessage());
    }

    public static final AjaxResult failed(String message) {
        return new AjaxResult(CODE_FAILED, null, message);
    }

    public static final AjaxResult failed(Object data, String message) {
        return new AjaxResult(CODE_FAILED, data, message);
    }

    public static final AjaxResult failed(Object data) {
        return new AjaxResult(CODE_FAILED, data, ErrorMessageEnum.FAILURE.getMessage());
    }

    public static final AjaxResult failed(ErrorMessageEnum err) {
        return new AjaxResult(err.getCode(), null, err.getMessage());
    }

    public static final AjaxResult failed(int code, Object data, String message) {
        return new AjaxResult(code, data, message);
    }

    public int getCode() {
        return code;
    }
}
