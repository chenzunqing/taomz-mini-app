package com.taomz.mini.apps.service.goods.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.taomz.mini.apps.dao.mapper.goods.SkuMapper;
import com.taomz.mini.apps.dto.goods.SkuDTO;
import com.taomz.mini.apps.dto.goods.SpuDTO;
import com.taomz.mini.apps.param.goods.GoodsProviderParam;
import com.taomz.mini.apps.service.goods.GoodsCacheService;
import com.taomz.mini.apps.service.goods.GoodsInfoProvider;
import com.taomz.mini.apps.service.goods.GoodsService;
import com.taomz.mini.apps.service.goods.GoodsSkuService;
import com.taomz.mini.apps.util.enums.BuyEnum;
import com.taomz.mini.apps.util.enums.DeleteEnum;
import com.taomz.mini.apps.util.enums.activity.PublishEnum;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 商品对外接口实现
 * @Author: liaoxiaoli
 * @CreateDate: 2018/6/24 11:32
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/6/24 11:32
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
@Slf4j
public class GoodsInfoProviderImpl implements GoodsInfoProvider {

    @Autowired
    private GoodsSkuService goodsSkuService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private GoodsCacheService goodsCacheService;

    @Override
    public SpuDTO getGoodsSpuInfoBySpuId(Long spuId, boolean includePreSku) throws ExceptionWrapper {
        try {
            //获取商品基本信息
            SpuDTO spuInfo = goodsService.getSpuInfoBySpuId(spuId,null);
            if(spuInfo == null){
                throw new ExceptionWrapper("商品不存在");
            }
            if(spuInfo.getBuyFlag() != BuyEnum.CAN_BUY.getIntValue()){
                throw new ExceptionWrapper("商品不可售卖,商品id:"+ spuId);
            }
            if(spuInfo.getPublishStatus() != PublishEnum.UP.getIntVlue()){
                log.warn("spuId:{} is down",spuId);
                throw new ExceptionWrapper("商品已下架,商品id:"+spuId);
            }
            //商品Sku
            List<SkuDTO> skuList = goodsSkuService.getSkuListBySpuId(spuId, includePreSku,spuInfo.getBuyFlag());
            spuInfo.setSkuList(skuList);
            return spuInfo;
        } catch (ExceptionWrapper e){
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionWrapper("商品信息异常");
        }
    }

    @Override
    public int stockChange(List<GoodsProviderParam> stockChangeDtoList, int operationType) throws ExceptionWrapper {
        // 参数校验
        if(1 != operationType && 2 != operationType){
            log.error("input operationType:{}",operationType);
            throw new ExceptionWrapper("库存操作类型有误");
        }
        // 商品信息校验
        if(CollectionUtil.isEmpty(stockChangeDtoList)){
            log.error("empty spu list");
            throw new ExceptionWrapper("商品列表为空");
        }
        Map<Long, SpuDTO> spuMap = new HashMap<>();
        SpuDTO spuInfoViewResult = null;
        List<SkuDTO> skuInfoViewResultList = null;
        for (GoodsProviderParam stockChangeParam:stockChangeDtoList) {
            // 参数校验
            if (stockChangeParam.getSkuId() == null ||
                    stockChangeParam.getChangeNum() == null || stockChangeParam.getSpuId() == null) {
                log.error("some parameter is null.");
                throw new ExceptionWrapper("库存操作参数信息有误");
            }

            // 查询商品的信息
            if (spuMap.get(stockChangeParam.getSpuId()) != null) {
                spuInfoViewResult = spuMap.get(stockChangeParam.getSpuId());
            } else {
                spuInfoViewResult = goodsService.getSpuInfoBySpuId(
                        stockChangeParam.getSpuId(), DeleteEnum.EFFECTIVE.getIntValue());
            }
            if(spuInfoViewResult == null){
                log.warn("spuId:{} not found",stockChangeParam.getSpuId());
                throw new ExceptionWrapper("商品不存在," + "商品id:" + stockChangeParam.getSpuId());
            }

            if(spuInfoViewResult.getPublishStatus() != PublishEnum.UP.getIntVlue()){
                log.warn("spuId:{} is down",stockChangeParam.getSpuId());
                throw new ExceptionWrapper("商品已下架,商品id:" + stockChangeParam.getSpuId());
            }

            // 基本信息校验 是否可卖，上下架,库存剩余,sku是否存在
            if(spuInfoViewResult.getBuyFlag()!= BuyEnum.CAN_BUY.getIntValue()){
                log.warn("spuId:{} can not buy",stockChangeParam.getSpuId());
                throw new ExceptionWrapper("商品不可售卖,商品id:" + stockChangeParam.getSpuId());
            }

            //虚拟商品不校验库存
            if(spuInfoViewResult.getVirtualFlag() == 1){
                log.info("spuId:{} is virtual goods ", stockChangeParam.getSpuId());
                continue;
            }

            skuInfoViewResultList = goodsSkuService.getSkuListBySpuId(
                    stockChangeParam.getSpuId(), false, spuInfoViewResult.getBuyFlag());

            boolean findSku = false;
            if(CollectionUtil.isNotEmpty(skuInfoViewResultList)){
                for(SkuDTO skuInfoViewResult:skuInfoViewResultList){
                    if(skuInfoViewResult.getSkuId().compareTo(stockChangeParam.getSkuId()) == 0){
                        // 判断库存是否足够 只在扣减下检查,加回只要sku匹配就行
                        if(1 == operationType){
                            if(stockChangeParam.getChangeNum()> skuInfoViewResult.getStockNum()){
                                log.error("stock num is not enough,skuId:{},latest sktock num is:{} ",stockChangeParam
                                        .getSkuId(),skuInfoViewResult.getStockNum());
                                // 库存不足,提示报错
                                throw new ExceptionWrapper("商品库存不足");
                            }
                            if( stockChangeParam.getChangeNum()< skuInfoViewResult.getMinBuyNum()){
                                log.error("The number of purchases is less than the minimum purchase quantity," +
                                        "skuId:{},min buy num is:{} ",stockChangeParam.getSkuId(),
                                        skuInfoViewResult.getMinBuyNum());
                                throw new ExceptionWrapper("购买数小于最低购买数量");
                            }
                            if( stockChangeParam.getChangeNum()> skuInfoViewResult.getMaxBuyNum()){
                                log.error("Purchases greater than the maximum purchases," +
                                                "skuId:{},min buy num is:{} ",stockChangeParam.getSkuId(),
                                        skuInfoViewResult.getMinBuyNum());
                                throw new ExceptionWrapper("购买数大于最大购买数量");
                            }
                        }
                        findSku = true;
                        break;
                    }
                }
            }
            if(!findSku){
                throw new ExceptionWrapper("未找到匹配的SKU");
            }
        }
        // 去扣减 加回 操作
        goodsSkuService.updateGoodsSkuStock(stockChangeDtoList, operationType);
        return 1;
    }

    @Override
    public int salesCountChange(List<GoodsProviderParam> salesCountChangeDtoList, int operationType) throws ExceptionWrapper {
        // 参数校验
        if(1 != operationType && 2 != operationType){
            log.error("input operationType:{}",operationType);
            throw new ExceptionWrapper("销量操作参数信息有误");
        }
        // 商品信息校验
        if(CollectionUtil.isEmpty(salesCountChangeDtoList)){
            log.error("empty spu list");
            throw new ExceptionWrapper("商品列表为空");
        }

        try{
            for(GoodsProviderParam sellchangeDto : salesCountChangeDtoList){
                // 参数校验
                if ( sellchangeDto.getSkuId() == null || sellchangeDto.getSpuId() == null) {
                    log.error("some parameter is null.");
                    throw new ExceptionWrapper("销量操作参数信息有误");
                }
                Map<Long, SpuDTO> spuMap = new HashMap<>();
                SpuDTO spuInfoViewResult = null;

                // 查询商品的信息
                if (spuMap.get(sellchangeDto.getSpuId()) != null) {
                    spuInfoViewResult = spuMap.get(sellchangeDto.getSpuId());
                } else {
                    spuInfoViewResult = goodsService.getSpuInfoBySpuId(sellchangeDto.getSpuId(),DeleteEnum.EFFECTIVE.getIntValue());
                }

                if(spuInfoViewResult == null){
                    log.warn("spuId:{} not found",sellchangeDto.getSpuId());
                    throw new ExceptionWrapper("商品不存在," + "商品id:"+sellchangeDto.getSpuId());
                }
            }
            goodsSkuService.updateGoodsSellCount(salesCountChangeDtoList, operationType);
        }catch (ExceptionWrapper e){
            log.error("some sku's sell is not enough.errMsg:{}",e.getMessage());
            throw new ExceptionWrapper(e.getMessage(),e);
        }catch (Exception e){
            log.error("some error happened.errMsg:{}",e.getMessage());
            throw new ExceptionWrapper("更新销量失败");
        }
        return 1;
    }

    @Override
    public Map<Long, Integer> getSkuStockMapBySkuids(List<GoodsProviderParam> skulist) throws ExceptionWrapper {
        if(CollectionUtil.isEmpty(skulist)){
            return null;
        }
        Map<Long, Integer> resultMap = new HashMap<>(skulist.size());
        for (GoodsProviderParam stockDto:skulist) {
            // 参数校验
            if (stockDto.getSkuId() == null || stockDto.getSpuId() == null) {
                log.error("some parameter is null.");
                throw new ExceptionWrapper("库存查询参数信息有误");
            }
            if(resultMap.get(stockDto.getSkuId()) == null){
                Integer stockNum = goodsCacheService.getSkuStock(stockDto.getSpuId(), stockDto.getSkuId());
                if(stockNum == null){
                    List<SkuDTO> skuList = skuMapper.getSkuListBySpuId(DeleteEnum.EFFECTIVE.getIntValue(), stockDto.getSpuId());
                    Map<String,Long> skuStockCountMap = new HashMap<>();
                    Map<String, Long> skuSellCountMap = new HashMap<>();
                    if (CollectionUtil.isNotEmpty(skuList)) {
                        for (SkuDTO sku : skuList) {
                            skuStockCountMap.put(sku.getSkuId().toString(), sku.getStockNum());
                            skuSellCountMap.put(sku.getSkuId().toString(), sku.getSellNum());
                        }
                        goodsCacheService.initSkuCounter(stockDto.getSpuId(),skuStockCountMap,skuSellCountMap);
                    }

                }
                resultMap.put(stockDto.getSkuId(),stockNum);
            }
        }

        return resultMap;
    }

    @Override
    public void checkGoods(Long spuId, Integer userId) throws ExceptionWrapper {
        if (spuId == null) {
            throw new ExceptionWrapper("商品ID为空");
        }
        //获取商品基本信息
        SpuDTO spuInfo = goodsService.getSpuInfoBySpuId(spuId, DeleteEnum.EFFECTIVE.getIntValue());
        if (spuInfo == null) {
            throw new ExceptionWrapper("商品不存在");
        }
    }

}
