package com.taomz.mini.apps.service.dto.spu;

import com.taomz.mini.apps.param.PageParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : wangling
 * @version V1.0
 * @title : HotRecommendQueryDTO
 * @Package : com.taomz.mini.apps.service.dto.spu
 * @Description:
 * @date 2020/11/26 9:39
 **/
@Data
public class HotRecommendQueryDTO extends PageParam {

    @NotNull(message = "分类id不能为空")
    private Integer spuRecommendId;
}
