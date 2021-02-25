package com.taomz.mini.apps.dto.activity.vo;

import com.taomz.mini.apps.model.activity.ActivityPlan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class ActivityPlanVo {
    private String planTime;

    List<ActivityPlan> planList;
}
