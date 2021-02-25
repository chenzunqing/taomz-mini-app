package com.taomz.mini.apps.dto.brand;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BrandRecommendDTO {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 类型
     */
    private String type;
}
