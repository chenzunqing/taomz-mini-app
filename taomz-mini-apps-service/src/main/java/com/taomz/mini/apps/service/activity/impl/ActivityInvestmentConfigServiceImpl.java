package com.taomz.mini.apps.service.activity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.activity.ActivityInvestmentConfigMapper;
import com.taomz.mini.apps.dto.activity.ActivityInvestmentConfigDTO;
import com.taomz.mini.apps.model.activity.ActivityInvestmentConfig;
import com.taomz.mini.apps.service.activity.ActivityCacheService;
import com.taomz.mini.apps.service.activity.ActivityInvestmentConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动招商配置表 服务实现类
 * </p>
 *
 * @author liaobing
 * @since 2020-11-20
 */
@Service
public class ActivityInvestmentConfigServiceImpl extends ServiceImpl<ActivityInvestmentConfigMapper, ActivityInvestmentConfig> implements ActivityInvestmentConfigService {
    @Autowired
    private ActivityCacheService activityCacheService;
    @Override
    public ActivityInvestmentConfigDTO getInvestmentConfigByActivityId(Integer activityId) {
        ActivityInvestmentConfigDTO config = this.baseMapper.getInvestmentByActivityId(activityId);
       /* if (config == null) {
            config = this.baseMapper.getInvestmentByActivityId(activityId);
            if (config != null) {
                activityCacheService.setInvestmentConfigToCache(config);
            }
        }*/
        return config;
    }
}
