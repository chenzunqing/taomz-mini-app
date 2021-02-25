package com.taomz.mini.apps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.OrderRegionDTO;
import com.taomz.mini.apps.model.OrderRegion;
import com.taomz.sha.util.response.BaseResponseModel;

import java.util.List;

/**
 * <p>
 * 全国省市区地址表 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-26
 */
public interface OrderRegionService extends IService<OrderRegion> {

    /**
     * 查询省市区
     * @param provinceId
     * @return {@link List<OrderRegionDTO>}
     */
    BaseResponseModel queryProvinces(Integer provinceId);
}
