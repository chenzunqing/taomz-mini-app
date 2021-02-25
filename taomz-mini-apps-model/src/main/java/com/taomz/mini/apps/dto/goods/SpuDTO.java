package com.taomz.mini.apps.dto.goods;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : SpuDTO
 * @Package : com.taomz.mini.apps.dto
 * @Description: 商品DTO
 * @date 2020/11/21 15:20
 **/
@Getter
@Setter
public class SpuDTO {

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

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 品牌国籍
     */
    private String countryCode;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 三级类目ID
     */
    private Long categoryId;

    /**
     * 二级类目ID
     */
    private Long secendCategoryId;

    /**
     * 一级类目ID
     */
    private Long firstCategoryId;

    /**
     * 分类/类目名称
     */
    private String categoryName;

    /**
     * 视频连接
     */
    private String videoUrl;

    /**
     * 商品描述 卖点
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
     * 总库存
     */
    private Long stockCount;

    /**
     * 总销量
     */
    private Long sellCount;

    /**
     * 上下架标识 1上架 0下架
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
     * 商品扩展信息
     */
    SpuExtendDTO spuExtend;

    /**
     * 是否可集采买卖 1可以 0不可以
     */
    private Integer buyFlag;

    /**
     * 浏览数
     */
    private Long viewCount;

    /**
     * 删除标识 0：无效 1：有效
     */
    private Integer deleteFlag;
    /**
     * 商品收藏数
     */
    private Long collectCount;

    /**
     * 商家用户ID
     */
    private Integer userId;

    /**
     * 是否是虚拟商品  1是 0否'
     */
    private Integer virtualFlag;

    /**
     * 发布渠道
     */
    private String publishChannel;

    /**
     * 明显单品 id
     */
    private Integer starId;


    /**
     * 品牌产品系列主键ID
     */
    private Integer brandProdSerId;

    /**
     * 产品备案号
     */
    private String prdRecordNumber;

    /**
     * 价格类型 1  价格区间 , 2 统一价格
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
     * 贸易方式（一般贸易，跨境贸易，国货）code
     */
    private String tradeTypeCodePc;

    /**
     * 产地
     */
    private String fromPlace;

    /**
     * 产地品牌
     */
    private String fromPlaceBrand;

    /**
     * 产地code
     */
    private String fromPlaceCodePc;

    /**
     * 置顶数值
     */
    private Integer upNum;

    /**
     * 产品系列名称
     */
    private String prodSeriesName;

    /**
     * 是否为明星单品 1 是，0 否
     */
    private Integer isStar;

    /**
     * 是否是主推品  1 是，0 否
     */
    private Integer isMainPush;

    /**
     * 人气值
     */
    private Integer isPersonFeel;

    /**
     * 是否收藏
     */
    private Integer isCollect;

    /**
     * 备案凭证地址
     */
    private String prdNumberImgUrl;

    /**
     * 是否样品：1是，0否
     */
    private Short isSample;

    /**
     * 新上显示图标
     */
    private Boolean isNewOn;

    /**
     * SKU数量
     */
    private int skuCount;

    /**
     * sku列表
     */
    List<SkuDTO> skuList;
}
