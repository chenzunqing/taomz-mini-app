package com.taomz.mini.apps.web.controller;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.service.CountryService;
import com.taomz.mini.apps.web.controller.base.BaseController;
import com.taomz.mini.apps.web.util.PropertyFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 国家表 前端控制器
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-25
 */
@RestController
public class CountryController extends BaseController {

    @Autowired
    private CountryService countryService;

    /**
     * 获取所有的国家列表信息
     * @return
     */
    @PostMapping("/wx/country/get_country_all")
    public String getCountryAll() {
        String[] ignoreProps = new String[] {"sort","status"};
        return JSON.toJSONString(countryService.getCountryAll(), PropertyFilterFactory.create(ignoreProps), DEFAULT_FEATURES);
    }
}
