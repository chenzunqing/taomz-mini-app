package com.taomz.mini.apps.service.cache;

import com.taomz.mini.apps.model.sysconfig.TSysConfigure;

/**
 * package ：com.ishop.service.redis
 * describe ：系统配置缓存类
 * Date ： 2018/6/21 11:30
 *
 * @author liaobing
 */
public interface SysConfigureCacheService {

    /**
     * 获取缓存配置
     *
     * @return
     */
    TSysConfigure getSysConfigureFromCache();

    /**
     * 设置缓存配置
     */
    void setSysConfigureToCache();
}
