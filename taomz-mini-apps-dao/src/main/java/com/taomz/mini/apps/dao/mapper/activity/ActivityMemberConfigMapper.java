package com.taomz.mini.apps.dao.mapper.activity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.dto.activity.ActivityMemberConfigDTO;
import com.taomz.mini.apps.model.activity.ActivityMemberConfig;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 活动会员设置表 Mapper 接口
 * </p>
 *
 * @author liaobing
 * @since 2020-11-20
 */
public interface ActivityMemberConfigMapper extends BaseMapper<ActivityMemberConfig> {
    /**
     * 根据活动ID查询活动会员报名配置信息
     *
     * @param activityId
     * @return
     */
    ActivityMemberConfigDTO getMemberConfigByActivityId(@Param("activityId") Integer activityId);
    /**
     * APP查询会员报名人数 手机号剔重
     * @param activityId
     * @return
     */
    int getApplyCount(@Param("activityId") Integer activityId);

}
