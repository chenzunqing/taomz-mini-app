package com.taomz.mini.apps.service.brand;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.brand.BrandBasicInfoQueryDTO;
import com.taomz.mini.apps.model.brand.BrandBasicInfo;
import com.taomz.mini.apps.service.dto.brand.BrandQueryHotListDTO;
import com.taomz.sha.util.response.BaseResponseModel;

/**
 * <p>
 *
 * 品牌基本信息 服务类
 * </p>
 *
 * @author fmz
 * @since 2020-11-21
 */
public interface BrandBasicInfoService extends IService<BrandBasicInfo> {

    BaseResponseModel queryRecentBrandList();

    /**
     * 首页产品推荐
     *
     * @return
     */
    BaseResponseModel homeRecommendList();

    /**
     * 品牌分类标签
     *
     * @return
     */
    BaseResponseModel getBrandTypeTag();

    /**
     * 查询好货页品牌List
     *
     * @param param
     * @return
     */
    BaseResponseModel queryGMListPage(BrandBasicInfoQueryDTO param, Integer userId);

    /**
     * 搜索品牌次数新增
     *
     * @param brandId
     * @return
     */
    BaseResponseModel addSerchNum(Integer brandId);

    /**
     * 热搜品牌
     *
     * @param brandQueryHotListDTO
     * @return
     */
    BaseResponseModel hotListByType(BrandQueryHotListDTO brandQueryHotListDTO);
}
