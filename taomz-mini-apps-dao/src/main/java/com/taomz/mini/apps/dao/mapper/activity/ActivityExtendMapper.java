package com.taomz.mini.apps.dao.mapper.activity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.dto.activity.ActivityExtendDTO;
import com.taomz.mini.apps.model.activity.ActivityExtend;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 活动扩展表 Mapper 接口
 * </p>
 *
 * @author liaobing
 * @since 2020-11-20
 */
public interface ActivityExtendMapper extends BaseMapper<ActivityExtend> {

    ActivityExtendDTO getByActivityId(@Param("activityId") Integer activityId);
}
