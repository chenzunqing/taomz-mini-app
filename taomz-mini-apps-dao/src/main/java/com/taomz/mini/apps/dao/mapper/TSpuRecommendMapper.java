package com.taomz.mini.apps.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taomz.mini.apps.ext.BrandBasicIfoExt;
import com.taomz.mini.apps.model.SpuHotRecommend;
import com.taomz.mini.apps.model.TSpuRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品推荐主表 Mapper 接口
 * </p>
 *
 * @author liaobing
 * @since 2020-11-17
 */
public interface TSpuRecommendMapper extends BaseMapper<TSpuRecommend> {

    List<TSpuRecommend>  getSpuHotList();

    IPage<SpuHotRecommend> getSpuHotRecommend(Page<SpuHotRecommend> page,@Param("spuRecommendId") Integer spuRecommendId);

}
