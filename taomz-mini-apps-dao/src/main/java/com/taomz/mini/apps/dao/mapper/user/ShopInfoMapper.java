package com.taomz.mini.apps.dao.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.model.user.ShopInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 店铺信息 Mapper 接口
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
public interface ShopInfoMapper extends BaseMapper<ShopInfo> {

    /**
     * 获取主店铺Id
     *
     * @param userId
     * @param status
     * @return
     */
    Integer mainShopId(@Param("userId") Integer userId, @Param("status") Integer status);
}
