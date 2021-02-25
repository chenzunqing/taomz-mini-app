package com.taomz.mini.apps.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 收货地址
 *
 * @author Wei.Guang
 * @create 2018-07-08 9:33
 **/
@Setter
@Getter
public class OrderRegionDTO implements Serializable {

    private static final long serialVersionUID = 6435850351928369355L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 省市区ID
     */
    private Integer provinceId;

    /**
     * 省市区名称
     */
    private String name;

    /**
     * 所属地区id
     */
    private Integer parentId;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 行政编码
     */
    private String cityCode;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 详细名称
     */
    private String mergerName;

    /**
     * 拼音
     */
    private String pinyin;
}
