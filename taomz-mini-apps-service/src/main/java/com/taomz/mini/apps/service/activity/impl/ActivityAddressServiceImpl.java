package com.taomz.mini.apps.service.activity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.activity.ActivityAddressMapper;
import com.taomz.mini.apps.model.activity.ActivityAddress;
import com.taomz.mini.apps.service.activity.ActivityAddressService;
import com.taomz.mini.apps.service.activity.ActivityCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityAddressServiceImpl extends ServiceImpl<ActivityAddressMapper, ActivityAddress> implements ActivityAddressService {
    @Autowired
    private ActivityCacheService activityCacheService;
    @Override
    public ActivityAddress getAddressByActivityId(Integer activityId) {
        ActivityAddress address = activityCacheService.getActivityAddressFromCache(activityId);
        if (address == null) {
            address = this.baseMapper.getByActivityId(activityId);
            if (address != null) {
                activityCacheService.setActivityAddressToCache(address);
            }
        }
        return address;
    }
}
