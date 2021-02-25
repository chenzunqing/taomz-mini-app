package com.taomz.mini.apps.dao.mapper.activity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.model.activity.ActivityPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动会议议程表 Mapper 接口
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-20
 */
public interface ActivityPlanMapper extends BaseMapper<ActivityPlan> {
    /**
     * 根据活动ID查询活动会议议程
     * @param activityId
     * @return
     */
    List<ActivityPlan> getPlanByActivityId(@Param("activityId") Integer activityId);
}
