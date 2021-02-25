package com.taomz.mini.apps.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : orderItemParam
 * @Package : com.taomz.mini.apps.param
 * @Description:
 * @date 2020/11/23 15:44
 **/
@Data
@Accessors(chain = true)
public class OrderItemParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private String orderCode;

    /**
     * 品牌编码
     */
    private Integer brandId;

    /**
     * spu_id
     */
    private Long spuId;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 活动单价
     */
    private BigDecimal actPrice;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 商品数量
     */
    private Integer num;
}
