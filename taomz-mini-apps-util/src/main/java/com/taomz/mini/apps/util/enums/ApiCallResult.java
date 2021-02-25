package com.taomz.mini.apps.util.enums;

/**
 * @Title: ApiCallResult.java
 * @Package com.taomz.live.portal.util.enums
 * @Description: TODO(返回的错误码定义类型)
 * @author wuque.hua
 * @date 2020年5月11日 下午5:32:38
 * @version V1.0
 */
public enum ApiCallResult {
    SUCCESS("000", "操作成功"),
    EMPTY("001", "参数缺失"), 
    DBERROR("002", "持久化异常"), 
    UNSUPPORTED("003", "格式不支持"), 
    SIGNERROR("004","签名错误"),
    OUTRANGE("005", "文件过大"),
    OPTIONFAILED("991","请求失败"),
    NORIGHT("995", "没有权限"),
    PHANTOM("996", "数据幻读"),
    DEFECT("997","缺失票据"), 
    OVERDUE("998", "票据过期"), 
    FAILURE("999", "操作失败"), 
    ILLEGAL_ARGUMENT("600","参数异常"),
    EXCEPTION("1001", "系统异常"),
    REQUEST_TIMEOUT("1002", "请求超时，请稍候尝试"),
    OPERATE_FREQUENTLY("1003", "操作太频繁，请稍候尝试"),
    RESTCLIENTERROR("1100", "通讯异常，请稍后尝试");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ApiCallResult(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
