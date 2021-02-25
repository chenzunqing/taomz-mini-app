package com.taomz.mini.apps.service.user.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.user.UserCategoryMapper;
import com.taomz.mini.apps.model.user.UserCategory;
import com.taomz.mini.apps.service.user.UserCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户归属类别 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
@Service
public class UserCategoryServiceImpl extends ServiceImpl<UserCategoryMapper, UserCategory> implements UserCategoryService {

    @Override
    public UserCategory findByCategoryType(Integer userId, String type, Integer status, List<Integer> statusItems) {
        LambdaQueryWrapper<UserCategory> wrapper = Wrappers.lambdaQuery(UserCategory.class)
                .eq(UserCategory::getUserId, userId)
                .eq(UserCategory::getCategoryType, type)
                .eq(Objects.nonNull(status), UserCategory::getStatus, status)
                .eq(CollectionUtil.isNotEmpty(statusItems), UserCategory::getStatus, statusItems);
        return getOne(wrapper);
    }
}
