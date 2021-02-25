package com.taomz.mini.apps.service.dto.brand;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : wangling
 * @version V1.0
 * @title : BrandSeachNumAddDTO
 * @Package : com.taomz.mini.apps.service.dto.brand
 * @Description: 搜索次数增加
 * @date 2020/11/23 14:45
 **/
@Data
public class BrandSeachNumAddDTO {
    @NotNull(message = "品牌id不能为空")
    private Integer brandId;
}
