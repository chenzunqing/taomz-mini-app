package com.taomz.mini.apps.web.controller.user;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.dto.user.UserPayDetailQueryDTO;
import com.taomz.mini.apps.service.user.UserPayDetailService;
import com.taomz.mini.apps.web.controller.base.BaseController;
import com.taomz.mini.apps.web.util.PropertyFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : UserPayDetailController
 * @Package : com.taomz.mini.apps.web.controller.user
 * @Description: 用户支付明细DTO
 * @date 2020/11/25 11:31
 **/
@RestController
public class UserPayDetailController extends BaseController {

    @Autowired
    private UserPayDetailService userPayDetailService;

    @PostMapping("/wx/user/trades/records")
    private String tradesRecords(@RequestBody UserPayDetailQueryDTO param) {
        param.setUserId(getCurrentUserId());
        String[] ignoreProps = new String[] {"type"};
        return JSON.toJSONString(
                userPayDetailService.findPage(param),
                PropertyFilterFactory.create(ignoreProps),
                DEFAULT_FEATURES);
    }
}
