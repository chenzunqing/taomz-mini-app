package com.taomz.mini.apps.service.activity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.activity.NonMemConfig;

/**
 * <p>
 * 专业观众报名设置 服务类
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-20
 */
public interface NonMemConfigService extends IService<NonMemConfig> {

    /**
     * 根据活动ID查询专业观众配置
     * @param activityId
     * @return
     */
    NonMemConfig getNonMemConfigByActivityId(Integer activityId);

}
