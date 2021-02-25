package com.taomz.mini.apps.dto.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * package ：com.ishop.dto describe ：品牌返回给前端的DTO Date ： 2018/6/21 17:39
 *
 * @author liaobing
 */
@Setter
@Getter
@ToString
public class BrandInfoDTO implements Serializable {
    private static final long serialVersionUID = 1700018321584878339L;

    private Integer id;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌英文名称
     */
    private String brandNameEn;

    /**
     * 入驻来源（数据字典）
     */
    private String enterSource;

    /**
     * 展位号
     */
    private String boothNumber;

    /**
     * 品牌Logo 地址
     */
    private String brandLogo;

    /**
     * 贸易种类（一般贸易，跨境贸易，国货）
     */
    private String tradeTypes;

    /**
     * 品牌国籍
     */
    private String countryCode;

    /**
     * 生产地址
     */
    private String productionAddress;

    /**
     * 品牌首字母
     */
    private String initials;

    /**
     * 状态
     */
    private Short status;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 优惠政策
     */
    private List<String> preferentialList;

}
