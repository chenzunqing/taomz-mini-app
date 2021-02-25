package com.taomz.mini.apps.service.goods;

import com.taomz.mini.apps.dto.goods.SkuDTO;
import com.taomz.mini.apps.dto.goods.SpuDTO;

import java.util.List;
import java.util.Map;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : GoodsCacheService
 * @Package : com.taomz.mini.apps.service.goods
 * @Description: 商品缓存接口
 * @date 2020/11/23 17:42
 **/
public interface GoodsCacheService {

    /**
     * 增加字段数量
     *
     * @param spuId
     * @param field
     * @param increaseNum
     * @return
     */
    Long increaseField(Long spuId, String field, Integer increaseNum);

    /**
     * 获取商品基本信息从缓存（不包含商品扩展，即只有主表基本数据）
     *
     * @param spuId
     * @return
     */
    SpuDTO getGoodsInfoFromCache(Long spuId);

    /**
     * 商品基本信息写入缓存（不包含商品扩展，即只有主表基本数据）
     *
     * @param goodsInfo
     * @return
     */
    void setGoodsInfoToCache(SpuDTO goodsInfo);

    /**
     * 更新SKU库存量 会同步更新SPU总库存
     *
     * @param spuId
     * @param skuId
     * @param type      添加/减少
     * @param changeNum 变更数量
     * @return
     */
    int updateSkuStock(Long spuId, Long skuId, int type, Integer changeNum);

    /**
     * 更新SKU销量 会同步更新SPU总销量
     *
     * @param spuId
     * @param skuId
     * @param type      添加/减少
     * @param changeNum 变更数量
     * @return
     */
    int updateSkuSell(Long spuId, Long skuId, int type, Integer changeNum);

    /**
     * 获取商品规格信息
     *
     * @param spuId
     * @return
     */
    List<SkuDTO> getSkuListFromCache(Long spuId, boolean includePreSku);

    /**
     * 缓存商品规格信息
     *
     * @param skuList
     * @param spuId
     * @param includePreSku 是否含有删除的sku
     */
    void setSkuListToCache(List<SkuDTO> skuList, Long spuId, boolean includePreSku);

    /**
     * 获取指定SPUID下的SKUID的库存
     *
     * @param spuId
     * @param skuId
     * @return
     */
    Integer getSkuStock(Long spuId, Long skuId);

    /**
     * 获取指定SPUID下的SKUID的销量
     *
     * @param spuId
     * @param skuId
     * @return
     */
    Integer getSkuSell(Long spuId, Long skuId);


    /**
     * 初始化商品SKU库存销量到redis
     *
     * @param spuId
     * @param skuStockMap
     * @param skuSellMap
     */
    void initSkuCounter(Long spuId, Map<String, Long> skuStockMap, Map<String, Long> skuSellMap);
}
