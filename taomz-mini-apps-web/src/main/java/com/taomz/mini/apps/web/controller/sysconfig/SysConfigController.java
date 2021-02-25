/**
 * @title : SysConfigController
 * @Package : com.taomz.mini.apps.web.controller.cache
 * @Description: 系统配置接口
 * @author xiaoluban@taomz.com
 * @date 2020/11/23 1:51 下午
 * @version V1.0
 **/
package com.taomz.mini.apps.web.controller.sysconfig;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.service.cache.SysConfigureCacheService;
import com.taomz.sha.util.response.BaseResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoluban@taomz.com
 * @version V1.0
 * @title : SysConfigController
 * @Package : com.taomz.mini.apps.web.controller.cache
 * @Description: 系统配置接口
 * @date 2020/11/23 1:51 下午
 **/
@RestController
public class SysConfigController {

    @Autowired
    private SysConfigureCacheService sysConfigureCacheService;

    /**
     * 获取系统配置，图片服务器地址
     *
     * @return
     */
    @PostMapping("/sys/config/images/url")
    public String querySysConfig() {
        return JSON.toJSONString(new BaseResponseModel(sysConfigureCacheService.getSysConfigureFromCache().getImageService()));
    }
}
