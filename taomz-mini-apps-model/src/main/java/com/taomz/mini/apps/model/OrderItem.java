package com.taomz.mini.apps.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("t_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    private String skuName;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 活动单价
     */
    private BigDecimal actPrice;

    /**
     * 商品数量
     */
    private Integer num;


}
