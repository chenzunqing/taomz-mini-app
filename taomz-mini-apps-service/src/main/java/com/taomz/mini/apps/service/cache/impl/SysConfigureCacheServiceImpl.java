package com.taomz.mini.apps.service.cache.impl;

import com.taomz.mini.apps.model.sysconfig.TSysConfigure;
import com.taomz.mini.apps.service.cache.SysConfigureCacheService;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.mini.apps.service.sysconfig.TSysConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * package ：com.ishop.service.redis.impl
 * describe ：系统配置缓存
 * Date ： 2018/6/21 11:34
 *
 * @author liaobing
 */

@Service
public class SysConfigureCacheServiceImpl implements SysConfigureCacheService {

    @Autowired
    TSysConfigureService sysConfigureService;

    @Autowired
    RedisService redisService;


    /**
     * 读取缓存
     *
     * @return
     */
    @Override
    public TSysConfigure getSysConfigureFromCache() {
        Object object = redisService.get(RedisRootNamespace.SYS_CONFIGURE);
        if (null == object) {
            setSysConfigureToCache();
            object = redisService.get(RedisRootNamespace.SYS_CONFIGURE);
        }
        return (TSysConfigure) object;
    }

    /**
     * 设置缓存
     */
    @Override
    public void setSysConfigureToCache() {
        TSysConfigure sysConfigure = sysConfigureService.list().get(0);
        redisService.set(RedisRootNamespace.SYS_CONFIGURE, sysConfigure);
    }
}
