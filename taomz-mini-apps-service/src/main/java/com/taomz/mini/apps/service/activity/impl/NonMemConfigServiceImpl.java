package com.taomz.mini.apps.service.activity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.activity.NonMemConfigMapper;
import com.taomz.mini.apps.model.activity.NonMemConfig;
import com.taomz.mini.apps.service.activity.NonMemConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 专业观众报名设置 服务实现类
 * </p>
 *
 * @author liaobing
 * @since 2020-11-20
 */
@Service
public class NonMemConfigServiceImpl extends ServiceImpl<NonMemConfigMapper, NonMemConfig> implements NonMemConfigService {

    @Override
    public NonMemConfig getNonMemConfigByActivityId(Integer activityId) {
        return this.baseMapper.getNonMemConfigByActivityId(activityId);
    }
}
