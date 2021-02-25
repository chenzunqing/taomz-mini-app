package com.taomz.mini.apps.model.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("m_activity")
public class Activity {
    /**
     * 活动主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动产品ID
     */
    private Integer actProdId;

    /**
     * 活动标题
     */
    private String activityName;

    /**
     * 活动主图
     */
    private String mainImage;
    /**
     * 封面图
     */
    private String coverImage;
    /**
     * 活动角色
     */
    private String activityRole;

    /**
     * 视频地址
     */
    private String videoUrl;


    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 是否有效 1有效 0无效
     */
    private Integer deleteFlag;

    /**
     * 上下架标识 1上架 0下架
     */
    private Integer publishState;

    /**
     * 大会直播id
     */
    private Integer meetingLiveId;
    /**
     * 图片直播地址
     */
    private String imageLiveUrl;
}
