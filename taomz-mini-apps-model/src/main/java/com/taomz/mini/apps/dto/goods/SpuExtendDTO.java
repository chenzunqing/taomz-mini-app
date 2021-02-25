package com.taomz.mini.apps.dto.goods;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpuExtendDTO {
    /**
     * 商品扩展信息ID
     */
    private Long spuExtendId;

    /**
     * 商品ID
     */
    private Long spuId;

    /**
     * 产品参数 不同品类产品参数不同存 json串
     */
    private String spuParam;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图文详情
     */
    private String detailDesc;
    /**
     * 分销政策
     */
    private String distributionPolicy;

}