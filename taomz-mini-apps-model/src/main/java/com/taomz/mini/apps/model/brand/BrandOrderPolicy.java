package com.taomz.mini.apps.model.brand;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订货政策表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_brand_order_policy")
public class BrandOrderPolicy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 政策内容
     */
    private String content;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 订货政策类型
     */
    private String type;

    /**
     * 是否上下架（0-下架，1-上架）
     */
    private Integer isShelf;

    /**
     * 是否删除（0-否，1-是）
     */
    private Integer isDelete;

    /**
     * 审核状态（0-未审核，1-已审核，2-审核拒绝）
     */
    private Integer status;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建更新时间
     */
    private Date updateTime;


}
