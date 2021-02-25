package com.taomz.mini.apps.service.cache.impl;

import com.taomz.mini.apps.dto.AdvertisingDTO;
import com.taomz.mini.apps.service.cache.AdvertisingCacheService;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.mini.apps.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AdvertisingCacheServiceImpl implements AdvertisingCacheService {
    @Autowired
    RedisService redisService;

    @Override
    public List<AdvertisingDTO> getAdvertisingListFromCache(String adsCode) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.ADVERTISING_SIT_INFO, adsCode);
            return (List<AdvertisingDTO>) redisService.get(redisKey);
        } catch (Exception e) {
            log.error("getAdvertisingListFromCache Error:{} ", e);
        }
        return null;
    }

    @Override
    public void removeAdvertisingListFromCache(String adsCode) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.ADVERTISING_SIT_INFO, adsCode);
            redisService.remove(redisKey);
        } catch (Exception e) {
            log.error("removeAdvertisingListFromCache Error:{} ", e);
        }
    }

    @Override
    public void setAdvertisingListToCache(String adsCode, List<AdvertisingDTO> cacheList) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.ADVERTISING_SIT_INFO, adsCode);
            redisService.set(redisKey, cacheList);
            redisService.expire(redisKey, RedisRootNamespace.ONE_HOURS_TIMEOUT, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("setAdvertisingListToCache Error:{} ", e);
        }
    }
}
