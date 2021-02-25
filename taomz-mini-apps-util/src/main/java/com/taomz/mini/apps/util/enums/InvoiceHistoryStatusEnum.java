package com.taomz.mini.apps.util.enums;

/**
 * package ：com.ishop.eum
 * describe ：
 * Date ： 2018/8/15 10:58
 *
 * @author liaobing
 */
public enum InvoiceHistoryStatusEnum {
    拒绝((short) 0,  "开票中"),
    资质审核中((short) 1,  "开票中"),
    开票中((short) 2,  "开票中"),
    已开票((short) 3, "已开票"),
    资质不通过((short) 9, "开票中"),
    未知参数((short) -1, "未知");

    public Short flag;
    public String name;

    InvoiceHistoryStatusEnum(Short flag, String name) {
        this.flag = flag;
        this.name = name;
    }

    public static InvoiceHistoryStatusEnum getEnum(Short flag) {
        for (InvoiceHistoryStatusEnum em : values()) {
            if (em.flag.equals(flag)) {
                return em;
            }
        }
        return null;
    }
}
