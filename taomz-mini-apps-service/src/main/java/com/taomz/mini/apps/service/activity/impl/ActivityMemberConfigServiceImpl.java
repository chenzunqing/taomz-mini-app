package com.taomz.mini.apps.service.activity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.activity.ActivityMemberConfigMapper;
import com.taomz.mini.apps.dto.activity.ActivityMemberConfigDTO;
import com.taomz.mini.apps.model.activity.ActivityMemberConfig;
import com.taomz.mini.apps.service.activity.ActivityMemberConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动会员设置表 服务实现类
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-20
 */
@Service
public class ActivityMemberConfigServiceImpl extends ServiceImpl<ActivityMemberConfigMapper, ActivityMemberConfig> implements ActivityMemberConfigService {


    @Override
    public ActivityMemberConfigDTO getMemberConfigByActivityId(Integer activityId) {
        return this.baseMapper.getMemberConfigByActivityId(activityId);
    }

    @Override
    public int getApplyCount(Integer activityId) {
        return this.baseMapper.getApplyCount(activityId);
    }

}
