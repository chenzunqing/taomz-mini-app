package com.taomz.mini.apps.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CategoryDTO implements Serializable {

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 等级
     */
    private Integer lev;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 是否有效  1有效 0无效
     */
    private Integer deleteFlag;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 类型 1资讯
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 主图
     */
    private String imgUrl;

    /**
     * 一级类目ID
     */
    private Long mainId;

    /**
     * 上下架标识 1上架 0下架
     */
    private Integer publishStatus;

    List<CategoryDTO> childList;

}