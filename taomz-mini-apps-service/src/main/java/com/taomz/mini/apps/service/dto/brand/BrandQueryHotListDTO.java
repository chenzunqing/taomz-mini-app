package com.taomz.mini.apps.service.dto.brand;

import com.taomz.mini.apps.param.PageParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : wangling
 * @version V1.0
 * @title : BrandQueryHotListDTO
 * @Package : com.taomz.mini.apps.service.dto.brand
 * @Description: 热搜品牌
 * @date 2020/11/23 14:46
 **/
@Data
public class BrandQueryHotListDTO extends PageParam {
    @NotNull(message = "搜索类型不能为空")
    private Integer type;
}
