package com.taomz.mini.apps.param.goods;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : GoodsProviderParam
 * @Package : com.taomz.mini.apps.param.goods
 * @Description: 商品接口参数
 * @date 2020/11/23 17:21
 **/
@Data
@Accessors(chain = true)
public class GoodsProviderParam {

    /**
     * 商品id
     */
    private Long spuId;

    /**
     * skuId
     */
    private Long skuId;
    /**
     * 变更数量 正数 加/减取决于操作类型
     */
    private Integer changeNum;
}
