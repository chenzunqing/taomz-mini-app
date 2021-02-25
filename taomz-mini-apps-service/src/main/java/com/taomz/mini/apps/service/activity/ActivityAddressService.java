package com.taomz.mini.apps.service.activity;

import com.taomz.mini.apps.model.activity.ActivityAddress;

public interface ActivityAddressService {
    /**
     * 根据活动ID查询活动地址
     *
     * @param activityId
     * @return
     */
    ActivityAddress getAddressByActivityId(Integer activityId);
}
