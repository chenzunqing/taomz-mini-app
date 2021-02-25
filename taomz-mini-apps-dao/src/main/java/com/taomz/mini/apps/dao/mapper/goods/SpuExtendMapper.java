package com.taomz.mini.apps.dao.mapper.goods;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.dto.goods.SpuExtendDTO;
import com.taomz.mini.apps.model.goods.SpuExtend;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品扩展信息 Mapper 接口
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
public interface SpuExtendMapper extends BaseMapper<SpuExtend> {

    SpuExtendDTO getSpuExtendBySpuId(@Param("spuId") Long spuId);
}
