package com.taomz.mini.apps.service.activity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.activity.ActivityApplyInvestmentBrandMapper;
import com.taomz.mini.apps.dao.mapper.activity.ActivityExtendMapper;
import com.taomz.mini.apps.dto.activity.ActivityBrandDTO;
import com.taomz.mini.apps.dto.activity.ActivityExtendDTO;
import com.taomz.mini.apps.model.activity.Activity;
import com.taomz.mini.apps.model.activity.ActivityApplyInvestmentBrand;
import com.taomz.mini.apps.param.ActivityExhibitorsBrandParam;
import com.taomz.mini.apps.service.activity.ActivityApplyInvestmentBrandService;
import com.taomz.mini.apps.service.activity.ActivityExtendService;
import com.taomz.mini.apps.service.activity.ActivityService;
import com.taomz.mini.apps.util.enums.activity.ActivityTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 活动品牌招商申请流水表 服务实现类
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-21
 */
@Service
public class ActivityApplyInvestmentBrandServiceImpl extends ServiceImpl<ActivityApplyInvestmentBrandMapper, ActivityApplyInvestmentBrand> implements ActivityApplyInvestmentBrandService {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityExtendMapper activityExtendMapper;

    @Override
    public List<ActivityBrandDTO> getExhibitorsBrand(Integer activityId) {
        Activity activity = activityService.getActivityById(activityId);
        ActivityExtendDTO extend = activityExtendMapper.getByActivityId(activityId);
        String relationActivity = String.valueOf(activityId);
        if (activity != null
                && activity.getActivityRole() != null
                && ActivityTypeEnum.MEMBER_ACTIVITY.name().equals(activity.getActivityRole())
                && StringUtils.isNotEmpty(extend.getRelationActivity())) {
            relationActivity = extend.getRelationActivity();
        }
        return this.baseMapper.getApplyBrandList(relationActivity);
    }
}
