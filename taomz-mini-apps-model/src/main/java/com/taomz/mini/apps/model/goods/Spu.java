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
 * 商品或者产品表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_spu")
public class Spu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 商品主图
     */
    private String mainImg;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 类目ID
     */
    private Long categoryId;

    /**
     * 视频链接
     */
    private String videoUrl;

    /**
     * 商品描述或者卖点
     */
    private String advertorial;

    /**
     * 显示价格
     */
    private BigDecimal viewPrice;

    /**
     * 最低折扣
     */
    private BigDecimal discount;

    /**
     * 是否可集采买卖 1可以 0不可以
     */
    private Integer buyFlag;

    /**
     * 浏览数
     */
    private Integer viewCount;

    /**
     * 商品收藏数
     */
    private Integer collectCount;

    /**
     * 总库存
     */
    private Integer stockCount;

    /**
     * 总销量
     */
    private Integer sellCount;

    /**
     * 上下架标识 1上架 0下架 2品牌过期下架
     */
    private Integer publishStatus;

    /**
     * 审核状态 0初始化 1通过 2不通过
     */
    private Integer auditStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 上架时间
     */
    private Date onTime;

    /**
     * 下架时间
     */
    private Date upTime;

    /**
     * 是否有效  1有效 0无效
     */
    private Integer deleteFlag;

    /**
     * 商家ID
     */
    private Integer userId;

    /**
     * 是否是虚拟商品 1是 0否
     */
    private Integer virtualFlag;

    /**
     * 发布渠道
     */
    private String publishChannel;

    /**
     * 品牌产品系列主键ID
     */
    private Integer brandProdSerId;

    /**
     * 产品备案号
     */
    private String prdRecordNumber;

    /**
     * 价格类型 1 默认 价格区间 , 2 统一价格 
     */
    private Integer priceType;

    /**
     * 产品统一价格
     */
    private BigDecimal uniformPrice;

    /**
     * 代发说明
     */
    private String substitutionNotes;

    /**
     * 寄样说明
     */
    private String sendingShow;

    /**
     * 咨询数 
     */
    private Integer consult;

    /**
     * 置顶数 数值越大排在上
     */
    private Integer upNum;

    /**
     * 最低价格
     */
    private BigDecimal minPrice;

    /**
     * 最高价格
     */
    private BigDecimal maxPrice;

    /**
     * 最低毛利率（%）
     */
    private BigDecimal minGrossProfit;

    /**
     * 最大毛利率（%）
     */
    private BigDecimal maxGrossProfit;

    /**
     * 贸易方式（一般贸易，跨境贸易，国货）
     */
    private String tradeType;

    /**
     * 产地
     */
    private String fromPlace;

    /**
     * 备案凭证地址
     */
    private String prdNumberImgUrl;

    /**
     * 是否样品：1是，0否
     */
    private Short isSample;


}
