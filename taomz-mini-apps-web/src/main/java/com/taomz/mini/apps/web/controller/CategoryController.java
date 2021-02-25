package com.taomz.mini.apps.web.controller;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.dto.CategoryQueryDTO;
import com.taomz.mini.apps.service.CategoryService;
import com.taomz.mini.apps.util.enums.activity.PublishEnum;
import com.taomz.mini.apps.web.controller.base.BaseController;
import com.taomz.mini.apps.web.util.PropertyFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : CategoryController
 * @Package : com.taomz.mini.apps.web.controller
 * @Description: 类别信息
 * @date 2020/11/21 16:55
 **/
@RestController
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/wx/category/get_category")
    public String getAllCategory(@RequestBody CategoryQueryDTO categoryQueryDTO) {
        String[] ignoreProps = new String[] {"deleteFlag","updateTime", "type", "publishStatus"};
        return JSON.toJSONString(
                categoryService.getAllCategory(categoryQueryDTO, PublishEnum.UP.getIntVlue()),
                PropertyFilterFactory.create(ignoreProps),
                DEFAULT_FEATURES);
    }
}
