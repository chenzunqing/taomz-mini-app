package com.taomz.mini.apps.dto.brand;

import com.taomz.mini.apps.dto.goods.GMSpuDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : {@link GMBrandInfoDTO}
 * @Package : {@link com.taomz.mini.apps.dto}
 * @Description: 品牌返回给小程序的 DTO
 * @date 2020/11/21 15:20
 **/
@Setter
@Getter
@ToString
public class GMBrandInfoDTO implements Serializable {
    private static final long serialVersionUID = 1700018321584878339L;

    /**
     * 品牌ID
     */
    private Integer id;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌简介
     */
    private String brandIdea;

    /**
     * 品牌Logo 地址
     */
    private String brandLogo;

    /**
     * 品牌国籍
     */
    private String countryCode;

    /**
     * 国籍ICON
     */
    private String countryIcon;

    /**
     * 贸易种类（一般贸易，跨境贸易，国货）
     */
    private String tradeTypes;

    /**
     * 授权渠道
     */
    private String authChannel;

    /**
     * 主营类目
     */
    private String mainCategory;

    /**
     * 主营类目
     */
    private List<String> mainCategoryItems;

    /**
     * 新上显示图标(0-新上)
     */
    private Short isNewOn;

    /**
     * 人气值显示图标
     */
    private Boolean isPopularityShow;

    /**
     * 招募信息
     */
    private String recruitDetail;

    /**
     * 招募信息是否展示（1展示 0不展示）
     */
    private Short viewFlag;

    /**
     * 政策内容
     */
    private String policyContent;

    /**
     * 政策结束时间
     */
    private Date policyEndTime;

    /**
     * 产品价格最小值
     */
    private BigDecimal intervalMin;

    /**
     * 产品价格最大值
     */
    private BigDecimal intervalMax;

    /**
     * 突出亮点
     */
    private String highlight;

    /**
     * 价格显示标志（TRUE-显示/FALSE-不显示）
     */
    private Boolean priceDisplayFlag;

    /**
     * 人气
     */
    private Long popularity;

    /**
     * 产品列表
     */
    List<GMSpuDTO> spuDTOS;

}
