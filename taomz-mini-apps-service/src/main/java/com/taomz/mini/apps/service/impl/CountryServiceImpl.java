package com.taomz.mini.apps.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.CountryMapper;
import com.taomz.mini.apps.model.Country;
import com.taomz.mini.apps.service.CountryService;
import com.taomz.sha.util.response.BaseResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 国家表 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-25
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements CountryService {

    @Override
    public BaseResponseModel getCountryAll() {
        List<Country> countryList =  getBaseMapper().selectList(
                Wrappers.lambdaQuery(Country.class)
                        .eq(Country::getStatus, 1)
                        .select(
                                Country::getId,
                                Country::getNameEn,
                                Country::getName,
                                Country::getCode,
                                Country::getIcon));
        return new BaseResponseModel().warpSuccess().setContent(countryList);
    }
}
