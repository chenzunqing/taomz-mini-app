package com.taomz.mini.apps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 一级类目ID
     */
    private Integer mainId;

    /**
     * 等级
     */
    private Integer lev;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 是否有效  1有效 0无效
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
     * 类型 1资讯 2品牌主营类目 3商品类目
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 类目主图
     */
    private String imgUrl;

    /**
     * 上下架标识 1上架 0下架
     */
    private Integer publishStatus;


}
