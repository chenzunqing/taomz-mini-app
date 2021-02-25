package com.taomz.mini.apps.service.activity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.activity.ActivityBrandDTO;
import com.taomz.mini.apps.model.activity.ActivityApplyInvestmentBrand;

import java.util.List;

public interface ActivityApplyInvestmentBrandService extends IService<ActivityApplyInvestmentBrand> {
    /**
     * 活动详情页查询活动相关联报名成功参展品牌
     * @param activityId
     * @return
     */
    List<ActivityBrandDTO> getExhibitorsBrand(Integer activityId);
}
