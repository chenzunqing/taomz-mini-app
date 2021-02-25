package com.taomz.mini.apps.web.controller.spu;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.service.dto.spu.HotRecommendQueryDTO;
import com.taomz.mini.apps.service.spu.TSpuRecommendService;
import com.taomz.mini.apps.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : wangling
 * @version V1.0
 * @title : SpuRecommendController
 * @Package : com.taomz.mini.apps.web.controller.spu
 * @Description: 产品推荐
 * @date 2020/11/23 16:36
 **/
@RestController
public class SpuRecommendController extends BaseController {

    @Autowired
    private TSpuRecommendService spuRecommendService;

    @PostMapping("/spu/hot/hot_recommend")
    public String hotRecommend() {
        return JSON.toJSONString(spuRecommendService.getHotRecommend());
    }

    @PostMapping("/spu/hot/hot_recommend_list")
    public String hotRecommendList(@Valid @RequestBody HotRecommendQueryDTO dto) {
        return JSON.toJSONString(spuRecommendService.getHotSpu(dto));
    }
}
