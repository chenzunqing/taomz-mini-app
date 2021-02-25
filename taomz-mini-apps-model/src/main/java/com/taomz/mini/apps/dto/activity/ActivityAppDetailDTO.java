package com.taomz.mini.apps.dto.activity;

import com.taomz.mini.apps.model.activity.ActivityAddress;
import com.taomz.mini.apps.model.activity.LiveMeeting;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ActivityAppDetailDTO implements Serializable {
    /**
     * 活动Id
     */
    private Integer activityId;

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
     * 活动角色
     */
    private String activityRole;
    /**
     * 活动方式  详细见字典表  峰会  月会  分享会等
     */
    private String activityWay;
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
     * 是否展示报名数  1展示 0不展示
     */
    private int viewApplyTotal;

    /**
     * 报名总数
     */
    private Integer applyTotal;
    /**
     * 活动扩展信息
     */
    private ActivityExtendDTO extend;
    /**
     * 活动地址
     */
    private ActivityAddress address;
    /**
     * 大会直播id
     */
    private Integer meetingLiveId;

    /**
     * 大会直播信息
     */
    private LiveMeeting liveMeeting;
    /**
     * 是否展示品牌  1展示 0不展示
     */
    private int viewBrandFlag;
    /**
     * 参展品牌
     */
    List<ActivityBrandDTO> brandList;
    /**
     * 特色活动
     */
    List<ActivityAppListDTO> activityList;
    /**
     * 报名开始时间
     */
    private Date signStartEndTime;
    /**
     * 报名截止时间
     */
    private Date signUpEndTime;
    /**
     * 活动状态  1进行中 2未开始 3已结束 4报名中
     */
    private Integer state;

    /**
     * 是否预约  0未预约  1已预约
     */
    private int reserveFlag;
    /**
     * 封面图
     */
    private String coverImage;

    /**
     * 视频标识  0本地视频 1第三方视频大会直播
     */
    private int videoFlag;
    /**
     * 是否有播放按钮  1有 0没有
     */
    private int haveVideo;
    /**
     * 图片直播地址
     */
    private String imageLiveUrl;
}
