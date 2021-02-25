package com.taomz.mini.apps.web.controller.goods;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.dto.goods.SpuQueryDTO;
import com.taomz.mini.apps.service.goods.GoodsService;
import com.taomz.mini.apps.util.enums.DeleteEnum;
import com.taomz.mini.apps.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : GoodsService
 * @Package : com.taomz.mini.apps.web.controller.goods
 * @Description: 产品controller
 * @date 2020/11/21 16:51
 **/
@RestController
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 一件代发产品列表
     */
    @PostMapping(value = "/wx/goods/wholesale/qry_app_spu_List")
    public String qryWholesaleList(@RequestBody SpuQueryDTO spuQueryDTO) {
        spuQueryDTO.setDeleteFlag(DeleteEnum.EFFECTIVE.getIntValue());
        return JSON.toJSONString(goodsService.qryWholesaleList(spuQueryDTO), DEFAULT_FEATURES);
    }
}
