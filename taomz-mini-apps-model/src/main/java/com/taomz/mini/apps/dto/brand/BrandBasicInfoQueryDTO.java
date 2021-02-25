package com.taomz.mini.apps.dto.brand;

import com.taomz.mini.apps.param.PageParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * package ：com.ishop.param.brands
 * describe ：App 品牌列表页查询参数
 * Date ： 2019/9/16 16:55
 *
 * @author liaobing
 */
@Getter
@Setter
public class BrandBasicInfoQueryDTO extends PageParam implements Serializable {
    private static final long serialVersionUID = 5225224250084809067L;

    /**
     * 类目
     */
    private List<String> categoryItems;

    /**
     * 地区
     */
    private List<String> countryCodeItems;

    /**
     * 贸易方式
     */
    private List<String> tradeTypeItems;

    /**
     * 授权渠道
     */
    private List<String> authChannelItems;

    /**
     * 产品价格最小值
     */
    private BigDecimal intervalMin;

    /**
     * 产品价格最大值
     */
    private BigDecimal intervalMax;

    /**
     * 入驻开始时间
     */
    private Date recentStartTime;

    /**
     * 最高利润比
     */
    private BigDecimal highestProfitRatio;

    /**
     * 最低毛利润
     */
    private BigDecimal minimumProfitRatio;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 排序（0-人气/1-新上/2-促销/3-招募）
     */
    private Short sort;

    /**
     * 品牌推荐ID
     */
    private Integer brandRecommendId;

    /**
     * 品牌管家
     */
    private Integer brandStewardId;
    private String brandStewardCountryCode;
}
