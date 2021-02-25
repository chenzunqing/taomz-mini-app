package com.taomz.mini.apps.web.controller.dict;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.service.comm.DictService;
import com.taomz.mini.apps.service.dto.DictQueryDTO;
import com.taomz.mini.apps.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DictController extends BaseController {
    @Autowired
    private DictService dictService;

    @PostMapping(value = "/wx/dict/dic_list")
    public String getDictList(@Valid @RequestBody DictQueryDTO param) {
        return JSON.toJSONString(dictService.listDictByType(param.getDicType()),DEFAULT_FEATURES);
    }
}
