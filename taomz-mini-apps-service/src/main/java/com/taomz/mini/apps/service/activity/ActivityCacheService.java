package com.taomz.mini.apps.service.activity;

import com.taomz.mini.apps.dto.activity.ActivityInvestmentConfigDTO;
import com.taomz.mini.apps.dto.activity.ActivityMemberConfigDTO;
import com.taomz.mini.apps.model.activity.ActivityAddress;

public interface ActivityCacheService {
    /**
     * 从缓存中获取活动地址
     *
     * @param activityId
     * @return
     */
    ActivityAddress getActivityAddressFromCache(Integer activityId);

    /**
     * 缓存活动地址
     *
     * @param address
     */
    void setActivityAddressToCache(ActivityAddress address);

    /**
     * 根据活动ID从缓存中查询招商配置信息
     *
     * @param activityId 活动ID
     * @return
     */
    ActivityInvestmentConfigDTO getInvestmentConfigFromCache(Integer activityId);

    /**
     * 缓存招商配置信息
     *
     * @param cacheData
     */
    void setInvestmentConfigToCache(ActivityInvestmentConfigDTO cacheData);

    /**
     * 根据活动ID从缓存中查询会员报名配置信息
     *
     * @param activityId 活动ID
     * @return
     */
    ActivityMemberConfigDTO getMemberConfigFromCache(Integer activityId);

    /**
     * 缓存会员报名配置信息
     *
     * @param cacheData
     */
    void setMemberConfigToCache(ActivityMemberConfigDTO cacheData);
}
