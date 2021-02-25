package com.taomz.mini.apps.service.activity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.activity.ActivityMemberConfigDTO;
import com.taomz.mini.apps.model.activity.ActivityMemberConfig;

/**
 * <p>
 * 活动会员设置表 服务类
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-20
 */
public interface ActivityMemberConfigService extends IService<ActivityMemberConfig> {
    /**
     * 根据活动ID查询活动会员设置信息
     *
     * @param activityId
     * @return
     */
    ActivityMemberConfigDTO getMemberConfigByActivityId(Integer activityId);
    /**
     * APP查询会员报名人数 手机号剔重
     *
     * @param activityId
     * @return
     */
    int getApplyCount(Integer activityId);

}
