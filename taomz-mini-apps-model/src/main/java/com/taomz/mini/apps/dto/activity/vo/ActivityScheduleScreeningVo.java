package com.taomz.mini.apps.dto.activity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ActivityScheduleScreeningVo implements Serializable {
    /**
     * 活动ID
     */
    private Integer activityId;
    /**
     * 活动时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 地址所属市
     */
    private String city;
    /**
     * 活动方式  详细见字典表  峰会  月会  分享会等
     */
    private String activityWay;
}
