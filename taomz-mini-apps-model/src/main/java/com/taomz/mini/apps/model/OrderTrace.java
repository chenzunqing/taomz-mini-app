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
 * 订单操作轨迹表
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order_trace")
public class OrderTrace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 操作
     */
    private String operate;

    /**
     * 历史操作轨迹
     */
    private String opeTrace;

    /**
     * 用户名称
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;


}
