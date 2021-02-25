package com.taomz.mini.apps.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.user.ShopInfoMapper;
import com.taomz.mini.apps.model.user.ShopInfo;
import com.taomz.mini.apps.service.user.ShopInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 店铺信息 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
@Service
public class ShopInfoServiceImpl extends ServiceImpl<ShopInfoMapper, ShopInfo> implements ShopInfoService {

    /**
     * 获取主店铺Id
     *
     * @param userId
     * @param status
     * @return
     */
    @Override
    public Integer mainShopId(Integer userId, Integer status) {
        return super.baseMapper.mainShopId(userId, status);
    }
}
