package com.taomz.mini.apps.service.activity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.activity.ActivityExtendDTO;
import com.taomz.mini.apps.model.activity.ActivityExtend;

/**
 * <p>
 * 活动扩展表 服务类
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-20
 */
public interface ActivityExtendService extends IService<ActivityExtend> {
    /**
     * 根据活动ID查询活动扩展信息
     * @param activityId
     * @return
     */
    ActivityExtendDTO getByActivityId(Integer activityId);

}
