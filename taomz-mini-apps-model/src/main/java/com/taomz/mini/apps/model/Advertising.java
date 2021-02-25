package com.taomz.mini.apps.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 广告信息
 * </p>
 *
 * @author liaobing
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_advertising")
public class Advertising implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告位编码
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
     *  跳转类型 0 H5   1原生
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
