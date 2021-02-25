package com.taomz.mini.apps.dto.activity.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ActivityScheduleMonthVo implements Serializable {
    private String monthTime;

    List<ActivityScheduleScreeningVo> scheduleList;
}
