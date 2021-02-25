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
 * 活动会议议程表
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_activity_plan")
public class ActivityPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会议议程ID主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动ID
     */
    private Integer activityId;

    /**
     * 议程时间
     */
    private Date planTime;

    /**
     * 议程标题
     */
    private String planTitle;

    /**
     * 议程内容 JSON数组
     */
    private String planContent;

    /**
     * 议程图片
     */
    private String planImage;

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
     * 是否展示  1展示 0不展示
     */
    private Integer viewFlag;


}
