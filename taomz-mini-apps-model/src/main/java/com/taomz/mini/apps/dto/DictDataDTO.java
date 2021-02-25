package com.taomz.mini.apps.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DictDataDTO implements Serializable {
    /**
     * 主键
     */
    private Long dictId;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 字典类型名称
     */
    private String dictTypeName;

    /**
     * 字典code
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 是否有子集
     */
    private Integer hasChild;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer sort;

    List<DictDataDTO> childList;

}