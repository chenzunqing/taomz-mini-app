package com.taomz.mini.apps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.OrderRegionMapper;
import com.taomz.mini.apps.dto.OrderRegionDTO;
import com.taomz.mini.apps.model.OrderRegion;
import com.taomz.mini.apps.service.OrderRegionService;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.sha.util.response.BaseResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 全国省市区地址表 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-26
 */
@Service
public class OrderRegionServiceImpl extends ServiceImpl<OrderRegionMapper, OrderRegion> implements OrderRegionService {

    @Autowired
    private RedisService redisService;

    public BaseResponseModel queryProvinces(Integer provinceId) {
        if (Objects.isNull(provinceId)) {
            String provinceKey = redisService.generateCacheKey("commerce:address_list:address", "privince", "100000");
            Object object = redisService.get(provinceKey);
            if (Objects.nonNull(object)) {
                return new BaseResponseModel().warpSuccess().setContent(object);
            }
            List<OrderRegion> orderRegionList = getBaseMapper().selectList(
                    Wrappers.lambdaQuery(OrderRegion.class)
                            .eq(OrderRegion::getParentId, 100000));
            List<OrderRegionDTO> orderRegioDTOList =  JSON.parseArray(JSON.toJSONString(orderRegionList), OrderRegionDTO.class);
            redisService.set(provinceKey, orderRegioDTOList);
            return new BaseResponseModel().warpSuccess().setContent(orderRegioDTOList);
        } else {
            String cityKey = redisService.generateCacheKey("commerce:address_list:address", "city");
            String citys = redisService.hmGet(cityKey, Integer.toString(provinceId), String.class);
            if (StrUtil.isNotBlank(citys)) {
                return new BaseResponseModel().warpSuccess().setContent(JSON.parseArray(citys, OrderRegionDTO.class));
            }
            List<OrderRegion> orderRegionList = getBaseMapper().selectList(
                    Wrappers.lambdaQuery(OrderRegion.class)
                            .eq(OrderRegion::getParentId, provinceId));
            List<OrderRegionDTO> orderRegioDTOList = JSON.parseArray(JSON.toJSONString(orderRegionList), OrderRegionDTO.class);
            redisService.hmSet(cityKey, Integer.toString(provinceId), JSON.toJSONString(orderRegioDTOList));
            return new BaseResponseModel().warpSuccess().setContent(orderRegioDTOList);
        }
    }
}
