package com.taomz.mini.apps.dao.mapper.goods;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.dto.goods.SkuDTO;
import com.taomz.mini.apps.model.goods.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品规格表 Mapper 接口
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
public interface SkuMapper extends BaseMapper<Sku> {

    List<SkuDTO> getSkuListBySpuId(@Param(value = "deleteFlag") Integer deleteFlag,
                                   @Param(value = "spuId") Long spuId);

    /**
     * 库存扣减
     *
     * @param skuId
     * @param decreaseNum
     * @return
     */
    int decreaseStockNum(@Param("skuId") Long skuId, @Param("decreaseNum") Integer decreaseNum);

    /**
     * 库存增加
     *
     * @param skuId
     * @param increaseNum
     * @return
     */
    int increaseStockNum(@Param("skuId") Long skuId, @Param("increaseNum") Integer increaseNum);

    /**
     * 销量扣减
     *
     * @param skuId
     * @return
     */
    int decreaseSellNum(@Param("skuId") Long skuId,@Param("decreaseNum") Integer decreaseNum);

    /**
     * 销量增加
     *
     * @param skuId
     * @return
     */
    int increaseSellNum(@Param("skuId") Long skuId, @Param("increaseNum") Integer increaseNum);
}
