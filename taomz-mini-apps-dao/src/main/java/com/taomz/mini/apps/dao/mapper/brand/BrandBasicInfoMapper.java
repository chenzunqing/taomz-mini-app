package com.taomz.mini.apps.dao.mapper.brand;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taomz.mini.apps.dto.brand.BrandInfoDTO;
import com.taomz.mini.apps.dto.brand.SpuRecommendHomeDTO;
import com.taomz.mini.apps.dto.brand.GMBrandInfoDTO;
import com.taomz.mini.apps.ext.BrandBasicIfoExt;
import com.taomz.mini.apps.model.brand.AppBrandBasicInfoRecentDTO;
import com.taomz.mini.apps.model.brand.BrandBasicInfo;
import com.taomz.mini.apps.model.brand.BrandRecommend;
import com.taomz.mini.apps.dto.brand.BrandBasicInfoQueryDTO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 品牌基本信息 Mapper 接口
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
public interface BrandBasicInfoMapper extends BaseMapper<BrandBasicInfo> {
     /**
      * 好货-新上品牌
      * @return
      */
     List<AppBrandBasicInfoRecentDTO> queryRecentBrandList();
     /**
      * 全部参展品牌
      * @return
      */
     List<BrandInfoDTO> getAllExhibitionBrand(@Param("idList") List<String> idList, @Param("brandName") String brandName);

     /**
      * 查询好货页品牌List
      * @param param
      * @return
      */
     Page<GMBrandInfoDTO> queryGMListPage(Page<GMBrandInfoDTO> pagination, @Param("param") BrandBasicInfoQueryDTO param);


     IPage<BrandBasicIfoExt> queryHostBrandListForType(Page<BrandBasicIfoExt> page,@Param("weekOrMonthTime") String weekOrMonthTime);

     /**
      * 首页产品推荐
      * @return
      */
     List<SpuRecommendHomeDTO> homeRecommendList();

     /**
      * 首页品牌分类标签
      * @return
      */
     List<BrandRecommend> getBrandTypeTag();

     /**
      * @return 关联活动查询
      */
     String getRelationActivity(@Param("activityId")Integer activityId);
}
