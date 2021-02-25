package com.taomz.mini.apps.service.cache;

import com.taomz.mini.apps.dto.AdvertisingDTO;

import java.util.List;

public interface AdvertisingCacheService {
    /**
     * 获取广告信息从缓存中
     *
     * @param adsCode
     * @return
     */
    List<AdvertisingDTO> getAdvertisingListFromCache(String adsCode);

    /**
     * 删除广告信息缓存
     *
     * @param adsCode
     */
    void removeAdvertisingListFromCache(String adsCode);

    /**
     * 设置广告信息入缓存
     *
     * @param adsCode
     * @param cacheList
     */
    void setAdvertisingListToCache(String adsCode, List<AdvertisingDTO> cacheList);
}
