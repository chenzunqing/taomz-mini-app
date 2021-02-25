package com.taomz.mini.apps.service.activity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.activity.ActivityInvestmentConfigDTO;
import com.taomz.mini.apps.model.activity.ActivityInvestmentConfig;

/**
 * <p>
 * 活动招商配置表 服务类
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-20
 */
public interface ActivityInvestmentConfigService extends IService<ActivityInvestmentConfig> {

    /**
     * 根据活动ID查询活动招商配置信息
     *
     * @param activityId
     * @return
     */
    ActivityInvestmentConfigDTO getInvestmentConfigByActivityId(Integer activityId);

}
