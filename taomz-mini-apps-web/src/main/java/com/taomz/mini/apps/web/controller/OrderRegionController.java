package com.taomz.mini.apps.web.controller;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.service.OrderRegionService;
import com.taomz.mini.apps.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 全国省市区地址表 前端控制器
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-26
 */
@Slf4j
@RestController
public class OrderRegionController extends BaseController {

    @Autowired
    private OrderRegionService orderRegionService;

    /**
     * 查询省市区
     * @param provinceId 省市区ID
     * @return
     */
    @GetMapping(value = "/wx/region/query_address")
    public String queryProvinces(@RequestParam(required = false) Integer provinceId) {
        return JSON.toJSONString(
                orderRegionService.queryProvinces(provinceId),
                DEFAULT_FEATURES);
    }
}
