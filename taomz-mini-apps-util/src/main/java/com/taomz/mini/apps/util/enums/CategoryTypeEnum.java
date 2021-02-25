package com.taomz.mini.apps.util.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollectionUtil;

/**
 * package ：com.ishop.eum
 * describe ：用户类别
 * Date ： 2018/7/3 18:42
 *
 * @author liaobing
 */
public enum CategoryTypeEnum {
    商会会员("MEMBER", "SUB_MEMBER_PRIVILEGE", "M"),
    红人("SENSATION", "", "S"),
    品牌方("BRAND_PARTY", "SUB_BRAND_PRIVILEGE", "B"),
    优享会员("EXCELLENT_USER", "", "E"),
    其他合作机构("OTHER_MECHANISM", "", ""),
    无身份("NO_IDENTITY", "", ""),
    未知参数("UNKNOWN_PARAMETER", "", "");

    private String stringValue;

    private String subAccountType;

    /**
     * 身份字母
     */
    private String letter;

    CategoryTypeEnum(String stringValue, String subAccountType, String letter) {
        this.stringValue = stringValue;
        this.subAccountType = subAccountType;
        this.letter = letter;
    }

    public static CategoryTypeEnum getEnum(String stringValue) {
        for (CategoryTypeEnum em : values()) {
            if (em.getStringValue().equals(stringValue)) {
                return em;
            }
        }
        return 未知参数;
    }

    public static String getCategoryName(List<String> stringValueList) {
        if (CollectionUtil.isEmpty(stringValueList)) {
            return CategoryTypeEnum.无身份.name();
        }
        StringBuffer strBuff = new StringBuffer();
        for (String stringValue : stringValueList) {
            strBuff.append(getEnum(stringValue).name()).append(",");
        }

        return strBuff.toString();
    }

    /**
     * 根据名称匹配参数
     *
     * @param name
     * @return
     */
    public static String getByName(String name) {
        switch (name) {
            case "商会会员":
                return 商会会员.stringValue;
            case "红人":
                return 红人.stringValue;
            case "品牌方":
                return 品牌方.stringValue;
            case "其他合作机构":
                return 其他合作机构.stringValue;
            default:
                return 未知参数.stringValue;
        }
    }

    /**
     * 枚举类型遍历成 Map 集合
     *
     * @return
     */
    public static Map<String, String> getCategoryTypeMap() {
        Map<String, String> map = new HashMap<>();
        for (CategoryTypeEnum em : values()) {
            map.put(em.stringValue, em.name());
        }
        return map;
    }

    public String getStringValue() {
        return stringValue;
    }

    public String getSubAccountType() {
        return subAccountType;
    }

    public String getLetter() {
        return letter;
    }
}
