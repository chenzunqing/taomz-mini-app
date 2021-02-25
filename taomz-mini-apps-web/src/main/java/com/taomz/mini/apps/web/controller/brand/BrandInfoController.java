package com.taomz.mini.apps.web.controller.brand;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.dto.brand.BrandBasicInfoQueryDTO;
import com.taomz.mini.apps.service.BrandStewardService;
import com.taomz.mini.apps.service.brand.BrandBasicInfoService;
import com.taomz.mini.apps.service.dto.brand.BrandQueryHotListDTO;
import com.taomz.mini.apps.service.dto.brand.BrandSeachNumAddDTO;
import com.taomz.mini.apps.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BrandInfoController extends BaseController {

    @Autowired
    private BrandStewardService brandStewardService;

    @Autowired
    private BrandBasicInfoService brandBasicInfoService;

    /**
     * 好货-品牌分类管理(品牌池〕
     *
     * @return
     */
    @PostMapping("/wx/brand/steward/getNationalLineList")
    public String getNationalLineList() {
        return JSON.toJSONString(brandStewardService.getNationalLineList());
    }

    /**
     * 好货-新上品牌
     *
     * @return
     */
    @PostMapping("/wx/user/brand/recent_brand_list")
    public String recentBrandList() {
        return JSON.toJSONString(brandBasicInfoService.queryRecentBrandList());
    }

    /**
     * 首页-产品推荐管理-列表
     *
     * @return
     */
    @PostMapping("/wx/spu/home/recommend/list_page")
    public String listPage() {
        return JSON.toJSONString(brandBasicInfoService.homeRecommendList());
    }

    /**
     * 获取品牌分类标签
     *
     * @return
     */
    @PostMapping(value = "/wx/brand/getBrandTypeTag")
    public String getBrandTypeTag() {
        return JSON.toJSONString(brandBasicInfoService.getBrandTypeTag());
    }

    /**
     * 查询好货页品牌List
     *
     * @param param
     * @return
     */
    @PostMapping("/wx/brand/good/meat/list")
    public String queryGMListPage(@RequestBody BrandBasicInfoQueryDTO param) {
        return JSON.toJSONString(brandBasicInfoService.queryGMListPage(param, getCurrentUserId()), DEFAULT_FEATURES);
    }

    @PostMapping("/wx/brand/search/add")
    public String addSerchNum(@Valid @RequestBody BrandSeachNumAddDTO dto) {
        return JSON.toJSONString(brandBasicInfoService.addSerchNum(dto.getBrandId()));
    }

    @PostMapping("/wx/brand/hot/list_by_type")
    public String hotListByType(@Valid @RequestBody BrandQueryHotListDTO appBrandQueryHotListDTO) {
        return JSON.toJSONString(brandBasicInfoService.hotListByType(appBrandQueryHotListDTO));
    }
}
