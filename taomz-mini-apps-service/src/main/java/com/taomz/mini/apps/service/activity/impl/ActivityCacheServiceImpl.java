package com.taomz.mini.apps.service.activity.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.dto.activity.ActivityInvestmentConfigDTO;
import com.taomz.mini.apps.dto.activity.ActivityMemberConfigDTO;
import com.taomz.mini.apps.model.activity.ActivityAddress;
import com.taomz.mini.apps.service.activity.ActivityCacheService;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.mini.apps.util.BeanUtil2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ActivityCacheServiceImpl implements ActivityCacheService {
    @Autowired
    private RedisService redisService;
    @Override
    public ActivityAddress getActivityAddressFromCache(Integer activityId) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.ACTIVITY_ADDRESS, activityId);
            return JSON.parseObject((String) redisService.get(redisKey),ActivityAddress.class);
        } catch (Exception e) {
            log.error("getActivityAddressFromCache Error:{} ", e);
        }
        return null;
    }

    @Override
    public void setActivityAddressToCache(ActivityAddress address) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.ACTIVITY_ADDRESS, address.getActivityId());
            redisService.set(redisKey, JSON.toJSONString(address), Long.valueOf(RedisRootNamespace.DEFAULT_ONE_DAY_TIMEOUT));
        } catch (Exception e) {
            log.error("setActivityAddressToCache Error:{} ", e);
        }
    }

    @Override
    public ActivityInvestmentConfigDTO getInvestmentConfigFromCache(Integer activityId) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.ACTIVITY_INVESTMENT_CONFIG_INFO, activityId);
            Map<String, String> map = redisService.hgetall(redisKey);
            if (map == null || map.size() == 0) {
                return null;
            }
            return BeanUtil.mapToBean(map, ActivityInvestmentConfigDTO.class, true);
        } catch (Exception e) {
            log.error("getInvestmentConfigFromCache Error:{} ", e);
            return null;
        }
    }

    @Override
    public void setInvestmentConfigToCache(ActivityInvestmentConfigDTO cacheData) {
        if (cacheData == null) {
            return;
        }
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.ACTIVITY_INVESTMENT_CONFIG_INFO, cacheData.getActivityId());
            Map<String, String> cacheMap = (Map<String, String>) BeanUtil2.objectToMap(cacheData);
            redisService.hsetAll(redisKey, cacheMap, RedisRootNamespace.DEFAULT_ONE_DAY_TIMEOUT, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("setInvestmentConfigToCache Error:{} ", e);
        }

    }

    @Override
    public ActivityMemberConfigDTO getMemberConfigFromCache(Integer activityId) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.ACTIVITY_MEMBER_CONFIG_INFO, activityId);
            Map<String, String> map = redisService.hgetall(redisKey);
            if (map == null || map.size() == 0) {
                return null;
            }
            return BeanUtil.mapToBean(map, ActivityMemberConfigDTO.class, true);
        } catch (Exception e) {
            log.error("getMemberConfigFromCache Error:{} ", e);
            return null;
        }
    }

    @Override
    public void setMemberConfigToCache(ActivityMemberConfigDTO cacheData) {
        if (cacheData == null) {
            return;
        }
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.ACTIVITY_MEMBER_CONFIG_INFO, cacheData.getActivityId());
            Map<String, String> cacheMap = (Map<String, String>) BeanUtil2.objectToMap(cacheData);
            redisService.hsetAll(redisKey, cacheMap, RedisRootNamespace.DEFAULT_ONE_DAY_TIMEOUT, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("setInvestmentConfigToCache Error:{} ", e);
        }
    }

}
