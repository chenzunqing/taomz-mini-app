package com.taomz.mini.apps.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.user.UserCategory;

import java.util.List;

/**
 * <p>
 * 用户归属类别 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
public interface UserCategoryService extends IService<UserCategory> {

    /**
     * 根据用户类型 查询用户类别
     * @param userId
     * @param type
     * @param status
     * @param statusItems
     * @return {@link UserCategory}
     */
    UserCategory findByCategoryType(Integer userId, String type, Integer status, List<Integer> statusItems);
}
