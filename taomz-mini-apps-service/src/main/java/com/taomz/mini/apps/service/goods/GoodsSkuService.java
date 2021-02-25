package com.taomz.mini.apps.service.goods;

import com.taomz.mini.apps.dto.goods.SkuDTO;
import com.taomz.mini.apps.param.goods.GoodsProviderParam;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;

import java.util.List;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : GoodsSkuService
 * @Package : com.taomz.mini.apps.service.goods
 * @Description:
 * @date 2020/11/23 17:54
 **/
public interface GoodsSkuService {

    /**
     * 更新商品库存
     *
     * @param stockChangeDtoList
     * @param operationType
     * @return
     * @throws ExceptionWrapper
     */
    int updateGoodsSkuStock(List<GoodsProviderParam> stockChangeDtoList, int operationType)
            throws ExceptionWrapper;

    /**
     * 销量修改
     *
     * @param salesCountChangeDtoList
     * @param operationType
     * @return
     * @throws ExceptionWrapper
     */
    int updateGoodsSellCount(List<GoodsProviderParam> salesCountChangeDtoList, int operationType)
            throws ExceptionWrapper;

    /**
     * 根据商品ID查询商品规格列表
     *
     * @param spuId
     * @param includePreSku
     *            true 包含以往所有的sku false 不包含
     * @param buyFlag
     *            是否可采集
     * @return
     */
    List<SkuDTO> getSkuListBySpuId(Long spuId, boolean includePreSku, Integer buyFlag);
}
