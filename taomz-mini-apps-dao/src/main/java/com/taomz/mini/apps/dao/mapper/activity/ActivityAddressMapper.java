package com.taomz.mini.apps.dao.mapper.activity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.model.activity.ActivityAddress;
import org.apache.ibatis.annotations.Param;

public interface ActivityAddressMapper extends BaseMapper<ActivityAddress> {
    /**
     * 根据活动ID查询活动地址
     * @param activityId
     * @return
     */
    ActivityAddress getByActivityId(@Param("activityId") Integer activityId);
}
