package com.taomz.mini.apps.dto.goods;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : SkuDTO
 * @Package : com.taomz.mini.apps.dto.goods
 * @Description:
 * @date 2020/11/23 17:24
 **/
@Getter
@Setter
public class SkuDTO {

    /**
     * 商品规格主键
     */
    private Long skuId;

    /**
     * 商品ID
     */
    private Long spuId;

    /**
     * 规格名称
     */
    private String skuName;

    /**
     * 规格图地址
     */
    private String skuImgUrl;

    /**
     * 规格库存
     */
    private Long stockNum;

    /**
     * 规格销售量
     */
    private Long sellNum;

    /**
     * 删除标识 0：无效 1：有效
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 规格价格
     */
    private BigDecimal skuPrice;

    /**
     * 最小订购数
     */
    private Long minBuyNum;
    /**
     * 最大购买数
     */
    private Long maxBuyNum;
    /**
     * 单位
     */
    private String unit;

    /**
     * 折扣
     */
    private BigDecimal discount;

    /**
     * 规格数值
     */
    private BigDecimal skuValue;
}
