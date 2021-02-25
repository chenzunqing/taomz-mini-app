package com.taomz.mini.apps.util.enums;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : UserPayDetailEnum
 * @Package : com.taomz.mini.apps.util.enums
 * @Description: 付费用户支付明细状态
 * @date 2020/11/23 16:51
 **/
public enum  UserPayDetailEnum {

    NEW((short) 0, "待支付"),
    UNDELIVERED((short) 1, "已支付"),
    CANCEL((short) 2, "已取消");

    private Short code;
    private String message;

    UserPayDetailEnum(Short code, String message) {
        this.code = code;
        this.message = message;
    }

    public static UserPayDetailEnum getEnum(short intValue) {
        for (UserPayDetailEnum em : values()) {
            if (em.getCode() == intValue) {
                return em;
            }
        }
        return null;
    }

    public static boolean containEnum(short intValue) {
        for (UserPayDetailEnum em : values()) {
            if (em.getCode() == intValue) {
                return true;
            }
        }
        return false;
    }

    public Short getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
