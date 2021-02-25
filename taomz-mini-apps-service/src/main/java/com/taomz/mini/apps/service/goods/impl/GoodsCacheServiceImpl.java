package com.taomz.mini.apps.service.goods.impl;

import cn.hutool.core.bean.BeanUtil;
import com.taomz.mini.apps.dto.goods.SkuDTO;
import com.taomz.mini.apps.dto.goods.SpuDTO;
import com.taomz.mini.apps.service.goods.GoodsCacheService;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.mini.apps.util.BeanUtil2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : GoodsCacheServiceImpl
 * @Package : com.taomz.mini.apps.service.goods.impl
 * @Description: 商品缓存接口实现
 * @date 2020/11/23 17:43
 **/
@Service
@Slf4j
public class GoodsCacheServiceImpl implements GoodsCacheService {

    @Autowired
    private RedisService redisService;

    @Override
    public Long increaseField(Long spuId, String field, Integer increaseNum) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_BASIC_CACHE, spuId);
            if (redisService.exists(redisKey) && redisService.hExists(redisKey, field)) {
                return redisService.hincrby(redisKey, field, increaseNum);
            }
        } catch (Exception e) {
            log.error("increaseField Error:{} ", e);
        }
        return null;
    }

    @Override
    public SpuDTO getGoodsInfoFromCache(Long spuId) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_BASIC_CACHE, spuId);
            Map<String, String> map = redisService.hgetall(redisKey);
            if (map == null || map.size() == 0) {
                return null;
            }
            return BeanUtil.mapToBean(map, SpuDTO.class, true);
        } catch (Exception e) {
            log.error("getGoodsInfoFromCache Error:{} ", e);
            return null;
        }
    }

    @Override
    public void setGoodsInfoToCache(SpuDTO goodsInfo) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_BASIC_CACHE, goodsInfo.getSpuId());
            Map<String, String> cacheMap = (Map<String, String>) BeanUtil2.objectToMap(goodsInfo);
            redisService.hsetAll(redisKey, cacheMap, RedisRootNamespace.GOODS_DEFAULT_TIMEOUT, TimeUnit.DAYS);
        } catch (Exception e) {
            log.error("setGoodsInfoToCache Error:{} ", e);
        }
    }

    /**
     * 更新SKU库存量 会同步更新SPU总库存
     *
     * @param spuId
     * @param skuId
     * @param type      添加/减少
     * @param changeNum 变更数量
     * @return 返回值说明 1:无变更或更新成功 -1:库存信息需要初始化 0异常
     */
    @Override
    public int updateSkuStock(Long spuId, Long skuId, int type, Integer changeNum) {
        try {
            log.info("updateSkuNumber from redis...spuId:{},skuId:{},type:{},num:{}", spuId, skuId, type, changeNum);
            if (changeNum == null) {
                return 1;
            }
            //库存hash key
            String stockKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_SKU_STOCK_CACHE, spuId);
            String skuIdStr = skuId.toString();
            //库存扣减的 乘以 -1 扣负T
            changeNum = 1 == type ? changeNum * (-1) : changeNum;
            if (redisService.exists(stockKey) && redisService.hExists(stockKey, skuIdStr)) {
                redisService.hincrby(stockKey, skuIdStr, changeNum);
            } else {
                return -1;
            }
            //SPU总库存
            this.increaseField(spuId, "stockCount", changeNum);
            return 1;
        } catch (Exception e) {
            log.error("updateSkuStock Error:{} ", e);
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 更新SKU销量 会同步更新SPU总销量
     *
     * @param spuId
     * @param skuId
     * @param type      添加/减少
     * @param changeNum 变更数量
     * @return 返回值说明 1:无变更或更新成功 -1:销量信息需要初始化 0异常
     */
    @Override
    public int updateSkuSell(Long spuId, Long skuId, int type, Integer changeNum) {
        try {
            log.info("updateSkuSell from redis...spuId:{},skuId:{},type:{},num:{}",
                    spuId, skuId, type, changeNum);
            if (changeNum == null) {
                return 1;
            }
            //销量hash key
            String sellKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_SKU_SELL_CACHE, spuId);
            String skuIdStr = skuId.toString();

            //销量扣减的 乘以 -1 扣负T
            changeNum = 1 == type ? changeNum * (-1) : changeNum;
            if (redisService.exists(sellKey) && redisService.hExists(sellKey, skuIdStr)) {
                redisService.hincrby(sellKey, skuIdStr, changeNum);
            } else {
                return -1;
            }
            //SPU总销量
            this.increaseField(spuId, "sellCount", changeNum);
            return 1;
        } catch (Exception e) {
            log.error("updateSkuSell Error:{} ", e);
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<SkuDTO> getSkuListFromCache(Long spuId, boolean includePreSku) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_SKU_CACHE, spuId, includePreSku);
            return (List<SkuDTO>) redisService.get(redisKey);
        } catch (Exception e) {
            log.error("getSkuListFromCache Error:{} ", e);
        }
        return null;
    }

    @Override
    public void setSkuListToCache(List<SkuDTO> skuList, Long spuId, boolean includePreSku) {
        try {
            String redisKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_SKU_CACHE, spuId, includePreSku);
            redisService.set(redisKey, skuList, 60 * 60L);
        } catch (Exception e) {
            log.error("setSkuListToCache Error:{} ", e);
        }
    }

    @Override
    public Integer getSkuStock(Long spuId, Long skuId) {
        log.info("getSkuStock from redis...spuId:{},skuId:{}", spuId, skuId);
        String stockKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_SKU_STOCK_CACHE, spuId);
        return redisService.hmGetIntegerValue(stockKey, String.valueOf(skuId));
    }

    @Override
    public Integer getSkuSell(Long spuId, Long skuId) {
        log.info("getSkuSell from redis...spuId:{},skuId:{}", spuId, skuId);
        String sellKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_SKU_SELL_CACHE, spuId);
        return redisService.hmGetIntegerValue(sellKey, String.valueOf(skuId));
    }

    @Override
    public void initSkuCounter(Long spuId, Map<String, Long> skuStockMap, Map<String, Long> skuSellMap) {
        String stockKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_SKU_STOCK_CACHE, spuId);
        if (skuStockMap != null && !skuStockMap.isEmpty()) {
            // 设置商品SKU库存
            redisService.hsetAllValueLong(stockKey, skuStockMap);
        }
        String sellKey = redisService.generateCacheKey(RedisRootNamespace.GOODS_SKU_SELL_CACHE, spuId);
        if (skuSellMap != null && !skuSellMap.isEmpty()) {
            // 设置商品SKU销量
            redisService.hsetAllValueLong(sellKey, skuStockMap);
        }
    }
}
