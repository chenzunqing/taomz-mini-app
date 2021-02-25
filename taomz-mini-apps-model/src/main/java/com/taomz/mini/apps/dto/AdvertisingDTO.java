package com.taomz.mini.apps.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaoxiaoli
 */
@Getter
@Setter
public class AdvertisingDTO implements Serializable {
    private static final long serialVersionUID = 3451327863819583283L;
    /**
     * 主键
     */
    private Long adId;

    /**
     * 广告位Code
     */
    private String adsCode;

    /**
     * 广告名称
     */
    private String adName;

    /**
     * 广告类型 0:图片,1:文字,2:flash
     */
    private Integer adType;

    /**
     * 图片地址
     */
    private String adImgUrl;

    /**
     * 链接地址
     */
    private String adLinkUrl;

    /**
     * 广告文字
     */
    private String adText;

    /**
     * 广告开始时间
     */
    private Date startTime;

    /**
     * 广告结束时间
     */
    private Date endTime;

    /**
     * 上下架标识 1上架 0下架
     */
    private Integer publishStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否有效 1有效 0无效
     */
    private Integer deleteFlag;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 页面跳转类型
     */
    private String adLinkType;

    /**
     * 跳转类型 0 H5   1原生
     */
    private Integer jumpType;
    /**
     * 跳转栏目值
     */
    private Integer jumpColumnValue;
    /**
     * 跳转地址
     */
    private String jumpUrl;
    /**
     * 跳转参数
     */
    private String jumpParam;
}