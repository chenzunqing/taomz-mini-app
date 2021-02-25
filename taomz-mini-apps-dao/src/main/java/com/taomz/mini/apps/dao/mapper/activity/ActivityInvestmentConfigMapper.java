package com.taomz.mini.apps.dao.mapper.activity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.dto.activity.ActivityInvestmentConfigDTO;
import com.taomz.mini.apps.model.activity.ActivityInvestmentConfig;
import org.apache.ibatis.annotations.Param;

public interface ActivityInvestmentConfigMapper extends BaseMapper<ActivityInvestmentConfig> {
    /**
     * 根据活动ID查询活动招商配置信息
     *
     * @param activityId
     * @return
     */
    ActivityInvestmentConfigDTO getInvestmentByActivityId(@Param("activityId") Integer activityId);
}
