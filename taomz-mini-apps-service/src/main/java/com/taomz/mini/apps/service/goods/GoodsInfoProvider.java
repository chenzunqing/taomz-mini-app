package com.taomz.mini.apps.service.goods;

import com.taomz.mini.apps.dto.goods.SpuDTO;
import com.taomz.mini.apps.param.goods.GoodsProviderParam;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 商品对外接口
 * @Author: liaoxiaoli
 * @CreateDate: 2018/6/24 11:28
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/6/24 11:28
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface GoodsInfoProvider {
    /**
     * 查询商品信息  下单查询 查询得可采集商品
     * @param spuId 商品ID
     * @param includePreSku 是否包含之前的sku信息(购物车是需要历史sku信息,我们暂时没有购物车) true 包含 fasle 不包含
     * @return
     * @throws ExceptionWrapper
     */
    SpuDTO getGoodsSpuInfoBySpuId(Long spuId,
                                  boolean includePreSku) throws ExceptionWrapper;

    /**
     * 库存操作
     * @param stockChangeDtoList
     * @param operationType 1:库存扣减 2:库存加回
     * @return
     * @throws ExceptionWrapper
     */
    int stockChange(List<GoodsProviderParam> stockChangeDtoList, int operationType)throws ExceptionWrapper;

    /**
     * 销量增加接口
     * @param salesCountChangeDtoList
     * @param operationType 1:销量扣减 2:销量加回  用于判断出现异常销量扣回
     * @return
     * @throws ExceptionWrapper
     */
    int salesCountChange(List<GoodsProviderParam> salesCountChangeDtoList,
                         int operationType) throws ExceptionWrapper;

    /**
     * 批量查询sku库存
     * @param skulist skuid集合
     * @return
     * @throws ExceptionWrapper
     */
    Map<Long, Integer> getSkuStockMapBySkuids(List<GoodsProviderParam> skulist) throws ExceptionWrapper;

    /**
     * 校验商品
     * @param spuId
     * @param userId
     * @throws ExceptionWrapper
     */
    void checkGoods(Long spuId, Integer userId) throws ExceptionWrapper;
}
