package com.taomz.mini.apps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 产品推荐主表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_spu_recommend")
public class TSpuRecommend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型 (从分类表读取)
     */
    private String type;

    /**
     * 总数量
     */
    private Integer number;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 开关（0 -  开，1 - 关）
     */
    private Integer isSwitch;

    /**
     * 创建时间
     */
    private Date createTime;


}
