package com.taomz.mini.apps.model.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品规格表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sku")
public class Sku implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品规格主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品ID
     */
    private Integer spuId;

    /**
     * 规格名称
     */
    private String skuName;

    /**
     * 规格价格
     */
    private BigDecimal skuPrice;

    /**
     * 最小订购数
     */
    private Integer minBuyNum;

    /**
     * 最大购买数
     */
    private Integer maxBuyNum;

    /**
     * 规格图地址
     */
    private String skuImgUrl;

    /**
     * 规格库存
     */
    private Integer stockNum;

    /**
     * 规格销售量
     */
    private Integer sellNum;

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
