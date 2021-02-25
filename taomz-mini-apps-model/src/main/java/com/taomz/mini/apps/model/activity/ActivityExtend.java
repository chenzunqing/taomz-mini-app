package com.taomz.mini.apps.model.activity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 活动扩展表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_activity_extend")
public class ActivityExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动权限表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动主键ID
     */
    private Integer activityId;

    /**
     * 议程方式  1富文本编辑  2时间轴编辑框
     */
    private Integer processType;

    /**
     * 会议流程
     */
    private String process;

    /**
     * 会议简介
     */
    private String introduction;

    /**
     * 费用说明/须知
     */
    private String feeRemark;

    /**
     * 关联活动  活动类型是会员活动时可选关联品牌
     */
    private String relationActivity;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
