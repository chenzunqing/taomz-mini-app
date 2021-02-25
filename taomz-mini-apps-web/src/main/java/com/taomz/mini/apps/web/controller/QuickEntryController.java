package com.taomz.mini.apps.web.controller;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.service.QuickEntryService;
import com.taomz.mini.apps.service.dto.QuickEntryQueryDTO;
import com.taomz.mini.apps.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : wangling
 * @version V1.0
 * @title : QuickEntryController
 * @Package : com.taomz.mini.apps.web.controller.base
 * @Description: 快捷入口
 * @date 2020/11/18 8:51
 **/
@RestController
public class QuickEntryController extends BaseController {

    @Autowired
    private QuickEntryService quickEntryService;

    @PostMapping("/quick_entry/home_list")
    public String homeList(@Valid @RequestBody QuickEntryQueryDTO QuickEntryQueryDTO) {
        String[] ignoreProps = new String[] {  };
        return JSON.toJSONString(quickEntryService.homeList(QuickEntryQueryDTO),DEFAULT_FEATURES);
    }
}
