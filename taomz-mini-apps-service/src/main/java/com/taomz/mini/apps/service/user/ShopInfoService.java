package com.taomz.mini.apps.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.user.ShopInfo;

/**
 * <p>
 * 店铺信息 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
public interface ShopInfoService extends IService<ShopInfo> {

    /**
     * 获取主店铺Id
     *
     * @param userId
     * @param status
     * @return
     */
    Integer mainShopId(Integer userId, Integer status);
}
