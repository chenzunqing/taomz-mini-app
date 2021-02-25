package com.taomz.mini.apps.dto.activity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityBrandDTO {
    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 英文名称
     */
    private String brandNameEn;

    /**
     * 品牌Logo
     */
    private String brandLogo;
}
