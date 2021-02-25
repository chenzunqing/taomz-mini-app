package com.taomz.mini.apps.dao.mapper.goods;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taomz.mini.apps.dto.goods.GMSpuDTO;
import com.taomz.mini.apps.model.goods.Spu;
import com.taomz.mini.apps.dto.goods.SpuQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品或者产品表 Mapper 接口
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
public interface SpuMapper extends BaseMapper<Spu> {

    /**
     * 总库存扣减
     *
     * @param spuId
     * @param decreaseNum
     * @return
     */
    int decreaseStockNum(@Param("spuId") Long spuId, @Param("decreaseNum") Integer decreaseNum);

    /**
     * 总库存增加
     *
     * @param spuId
     * @param increaseNum
     * @return
     */
    int increaseStockNum(@Param("spuId") Long spuId, @Param("increaseNum") Integer increaseNum);

    /**
     * 总销量扣减
     *
     * @param spuId
     * @return
     */
    int decreaseSellNum(@Param("spuId") Long spuId, @Param("decreaseNum") Integer decreaseNum);

    /**
     * 总销量增加
     *
     * @param spuId
     * @return
     */
    int increaseSellNum(@Param("spuId") Long spuId, @Param("increaseNum") Integer increaseNum);

    /**
     * 好货 查询品牌产品App
     *
     * @param brandId
     * @return
     */
    List<GMSpuDTO> qryGMSpuAppList(@Param("brandId") Integer brandId);

    /**
     * app 查询产品列表
     *
     * @param param
     * @return
     */
    Page<com.taomz.mini.apps.dto.goods.SpuDTO> qryWholesaleList(Page<com.taomz.mini.apps.dto.goods.SpuDTO> pagination, @Param("param") SpuQueryDTO param);
}
