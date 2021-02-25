package com.taomz.mini.apps.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 全国省市区地址表
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order_region")
public class OrderRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 省市区ID
     */
    private Integer provinceId;

    /**
     * 所属地区id
     */
    private Integer parentId;

    /**
     * 省市区名称
     */
    private String name;

    /**
     * 详细名称
     */
    private String mergerName;

    /**
     * 简称
     */
    private String shortName;

    private String mergerShortName;

    /**
     * 等级类型
     */
    private Integer levelType;

    /**
     * 行政编码
     */
    private String cityCode;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 简拼
     */
    private String jianpin;

    private String firstchar;

    /**
     * 经度
     */
    private Float lng;

    /**
     * 纬度
     */
    private Float lat;

    /**
     * 备注
     */
    private String remarks;


}
