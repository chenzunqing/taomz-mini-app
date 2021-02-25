package com.taomz.mini.apps.dao.mapper.activity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.model.activity.NonMemConfig;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 专业观众报名设置 Mapper 接口
 * </p>
 *
 * @author liaobing
 * @since 2020-11-20
 */
public interface NonMemConfigMapper extends BaseMapper<NonMemConfig> {
    /**
     * 根据活动ID查询专业观众配置
     * @param activityId
     * @return
     */
    NonMemConfig getNonMemConfigByActivityId(@Param("activityId")Integer activityId);
}
