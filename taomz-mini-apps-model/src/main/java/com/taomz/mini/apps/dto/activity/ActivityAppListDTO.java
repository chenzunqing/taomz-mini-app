package com.taomz.mini.apps.dto.activity;

import com.taomz.mini.apps.model.activity.ActivityAddress;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ActivityAppListDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 活动Id
     */
    private Integer activityId;
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
     * 视频地址
     */
    private String videoUrl;
    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;
    /**
     * 活动状态 1进行中 2未开始 3已结束 4报名中
     */
    private Integer state;
    /**
     * 活动方式  详细见字典表  峰会  月会  分享会等
     */
    private String activityWay;
    /**
     * 活动角色
     */
    private String activityRole;

    /**
     * 是否展示报名数  1展示 0不展示
     */
    private int viewApplyTotal;
    /**
     * 报名总数
     */
    private Integer applyTotal;
    /**
     * 报名开始时间
     */
    private Date signUpStartTime;

    /**
     * 报名结束时间
     */
    private Date signUpEndTime;
    /**
     * 是否预约  0未预约  1已预约
     */
    private int reserveFlag;
    /**
     * 活动地址
     */
    private ActivityAddress address;
    /**
     * 是否有播放按钮  1有 0没有
     */
    private int haveVideo;
    private Integer meetingLiveId;

}
