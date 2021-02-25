package com.taomz.mini.apps.dto.goods;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : AppGMSpuDTO
 * @Package : com.ishop.dto.brands
 * @Description: 好货品牌产品列表
 * @date 2020/11/17 16:38
 **/
@Data
@Accessors(chain = true)
public class GMSpuDTO implements Serializable {

    /**
     * 商品主键
     */
    private Long spuId;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 商品主图
     */
    private String mainImg;

}
