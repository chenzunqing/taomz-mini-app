package com.taomz.mini.apps.web.controller;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.service.AdvertisingService;
import com.taomz.mini.apps.service.dto.AdvertisingQueryDTO;
import com.taomz.mini.apps.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class AdvertisingController extends BaseController {
    @Autowired
    private AdvertisingService advertisingService;

    @PostMapping(value = "/wx/advertising")
    public String getAdvertising(@Valid @RequestBody AdvertisingQueryDTO param){
        return JSON.toJSONString(advertisingService.findByCode(param.getAdsCode()),DEFAULT_FEATURES);
    }
}
