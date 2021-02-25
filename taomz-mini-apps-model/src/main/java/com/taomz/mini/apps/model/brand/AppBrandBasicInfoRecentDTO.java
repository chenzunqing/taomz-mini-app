package com.taomz.mini.apps.model.brand;

import lombok.Data;

@Data
public class AppBrandBasicInfoRecentDTO {
    /**
     * 品牌id
     */
    private Integer id;
    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌logo
     */
    private String brandLogo;
}
