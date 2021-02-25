package com.taomz.mini.apps.service.goods.impl;

import com.taomz.mini.apps.dao.mapper.goods.SkuMapper;
import com.taomz.mini.apps.dao.mapper.goods.SpuMapper;
import com.taomz.mini.apps.dto.goods.SkuDTO;
import com.taomz.mini.apps.param.goods.GoodsProviderParam;
import com.taomz.mini.apps.service.goods.GoodsCacheService;
import com.taomz.mini.apps.service.goods.GoodsSkuService;
import com.taomz.mini.apps.util.enums.BuyEnum;
import com.taomz.mini.apps.util.enums.DeleteEnum;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : GoodsSkuServiceImpl
 * @Package : com.taomz.mini.apps.service.goods.impl
 * @Description:
 * @date 2020/11/23 17:55
 **/
@Service
@Slf4j
public class GoodsSkuServiceImpl implements GoodsSkuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private GoodsCacheService goodsCacheService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateGoodsSkuStock(List<GoodsProviderParam> stockChangeDtoList, int operationType)
            throws ExceptionWrapper {
        int updateNum;
        for (GoodsProviderParam stockChangeDto : stockChangeDtoList) {
            if (1 == operationType) {
                // db库存扣减
                updateNum = skuMapper.decreaseStockNum(stockChangeDto.getSkuId(), stockChangeDto.getChangeNum());
                if (updateNum == 0) {
                    // 没更新到数据 则为异常场景
                    throw new ExceptionWrapper("更新库存失败");
                }

                updateNum = spuMapper.decreaseStockNum(stockChangeDto.getSpuId(), stockChangeDto.getChangeNum());
                if (updateNum == 0) {
                    // 没更新到数据 则为异常场景
                    throw new ExceptionWrapper("更新库存失败");
                }
            } else {
                // db库存加回
                skuMapper.increaseStockNum(stockChangeDto.getSkuId(), stockChangeDto.getChangeNum());
                spuMapper.increaseStockNum(stockChangeDto.getSpuId(), stockChangeDto.getChangeNum());
                // 加回不考虑是否已经更新成功,已经到库存最大值时,就会出现更新不到数据
            }

            // 缓存操作 针对库存扣减频繁动作 这里不删除缓存 在缓存上做扣减加回
            goodsCacheService.updateSkuStock(stockChangeDto.getSpuId(), stockChangeDto.getSkuId(), operationType,
                    stockChangeDto.getChangeNum());

        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateGoodsSellCount(List<GoodsProviderParam> salesCountChangeDtoList, int operationType)
            throws ExceptionWrapper {
        int updateNum;
        for (GoodsProviderParam sellChangeDto : salesCountChangeDtoList) {
            if (1 == operationType) {
                // 销量扣减
                updateNum = skuMapper.decreaseSellNum(sellChangeDto.getSkuId(), sellChangeDto.getChangeNum());
                if (updateNum == 0) {
                    // 没更新到数据 则为异常场景
                    throw new ExceptionWrapper("更新销量失败");
                }
                updateNum = spuMapper.decreaseSellNum(sellChangeDto.getSpuId(), sellChangeDto.getChangeNum());
                if (updateNum == 0) {
                    // 没更新到数据 则为异常场景
                    throw new ExceptionWrapper("更新销量失败");
                }
            } else {
                // 销量新增
                skuMapper.increaseSellNum(sellChangeDto.getSkuId(), sellChangeDto.getChangeNum());
                spuMapper.increaseSellNum(sellChangeDto.getSpuId(), sellChangeDto.getChangeNum());
            }

            // 缓存操作 针对库存扣减频繁动作 这里不删除缓存 在缓存上做扣减加回
            goodsCacheService.updateSkuSell(sellChangeDto.getSpuId(), sellChangeDto.getSkuId(), operationType,
                    1);
        }
        return 1;
    }

    @Override
    public List<SkuDTO> getSkuListBySpuId(Long spuId, boolean includePreSku, Integer buyFlag) {
        // 从缓存中查询sku列表
        List<SkuDTO> skuList = goodsCacheService.getSkuListFromCache(spuId, includePreSku);
        if (CollectionUtils.isNotEmpty(skuList)) {
            if (buyFlag != null && BuyEnum.CAN_NOT_BUY.getIntValue() == buyFlag) {
                return skuList;
            }
            initSkuStock(skuList, spuId);
            return skuList;
        }
        // 需要查询以往所有商品sku列表
        if (includePreSku) {
            skuList = skuMapper.getSkuListBySpuId(null, spuId);
        } else { // 只查询当前有效得商品sku列表
            skuList = skuMapper.getSkuListBySpuId(DeleteEnum.EFFECTIVE.getIntValue(), spuId);
        }
        if (CollectionUtils.isNotEmpty(skuList)) {
            goodsCacheService.setSkuListToCache(skuList, spuId, includePreSku);
            if (BuyEnum.CAN_BUY.getIntValue() == buyFlag) {
                initSkuStock(skuList, spuId);
            }
        } else {
            // 防止移动端出现null
            skuList = new ArrayList<>();
        }
        return skuList;
    }

    private void initSkuStock(List<SkuDTO> skuList, Long spuId) {
        for (SkuDTO skuinfo : skuList) {
            Integer skuSellCount = goodsCacheService.getSkuSell(spuId, skuinfo.getSkuId());
            Integer skuStockCount = goodsCacheService.getSkuStock(spuId, skuinfo.getSkuId());
            // 初始化入库入redis
            if (skuSellCount == null || skuStockCount == null) {
                // 缓存中无销量或者无库存时重新加载数据库存入缓存
                Map<String, Long> skuStockCountMap = new HashMap<>();
                Map<String, Long> skuSellCountMap = new HashMap<>();
                skuList = skuMapper.getSkuListBySpuId(DeleteEnum.EFFECTIVE.getIntValue(), spuId);
                if (CollectionUtils.isNotEmpty(skuList)) {
                    for (SkuDTO sku : skuList) {
                        skuStockCountMap.put(sku.getSkuId().toString(), sku.getStockNum());
                        skuSellCountMap.put(sku.getSkuId().toString(), sku.getSellNum());
                    }
                    goodsCacheService.initSkuCounter(spuId, skuStockCountMap, skuSellCountMap);
                }
                return;
            }
        }
    }
}
