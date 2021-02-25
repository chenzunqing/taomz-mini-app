package com.taomz.mini.apps.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 国家表
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 英文国家名字
     */
    private String nameEn;

    /**
     * 中文国家名字
     */
    private String name;

    /**
     * 国家编码
     */
    private String code;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态  1有效  0  无效
     */
    private Integer status;


}
