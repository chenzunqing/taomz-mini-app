package com.taomz.mini.apps.ext;

import lombok.Data;

/**
 * @author : wangling
 * @version V1.0
 * @title : BrandBasicIfoExt
 * @Package : com.taomz.mini.apps.ext
 * @Description: 热搜品牌返回model
 * @date 2020/11/23 14:56
 **/
@Data
public class BrandBasicIfoExt {

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌logo
     */
    private String brandLogo;

    /**
     * 品牌介绍
     */
    private String brandIdea;

    /**
     * 品牌指数
     */
    private Double expent;

    /**
     * 地区
     */
    private String countryName;

    /**
     * 分类
     */
    private String mainCategory;
}
