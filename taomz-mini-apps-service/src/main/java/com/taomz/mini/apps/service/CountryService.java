package com.taomz.mini.apps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.Country;
import com.taomz.sha.util.response.BaseResponseModel;

import java.util.List;

/**
 * <p>
 * 国家表 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-25
 */
public interface CountryService extends IService<Country> {

    /**
     * 获取所有国家信息
     *
     * @return
     */
    BaseResponseModel getCountryAll();
}
