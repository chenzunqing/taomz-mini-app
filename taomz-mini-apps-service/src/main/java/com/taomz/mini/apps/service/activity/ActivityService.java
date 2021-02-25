package com.taomz.mini.apps.service.activity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.brand.BrandInfoDTO;
import com.taomz.mini.apps.model.activity.Activity;
import com.taomz.mini.apps.param.ActivityDetailParam;
import com.taomz.mini.apps.param.ActivityParam;
import com.taomz.mini.apps.param.ExhibitionBrandParam;
import com.taomz.mini.apps.service.dto.activity.ActivityQueryDTO;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.sha.util.response.BaseResponseModel;

import java.util.List;


public interface ActivityService extends IService<Activity> {
    /**
     * 根据活动ID查询活动基本信息
     *
     * @param activityId
     * @return
     */
    Activity getActivityById(Integer activityId);

    /**
     * 查询正在报名中和预热的活动
     * @param startTime
     * @param userId
     * @return
     */
    /**
     * 查询正在报名中和预热的活动
     * @param activityQuery
     * @param userId
     * @return
     */
    BaseResponseModel signingUpActivity(ActivityQueryDTO activityQuery, Integer userId);

    /**
     * 查询正在报名中和预热的活动日程  按移动端要求返回List 不返回MAP
     * @return
     */
    BaseResponseModel getSigningUpScheduleScreening();

    /**
     * 查询活动列表
     * @param param
     * @return
     */
    BaseResponseModel getActivityList(ActivityParam param);

    /**
     * 小程序端活动详情页 20201116
     * @param param
     * @return
     */
    BaseResponseModel getAppActivityDetailByActivityId(ActivityDetailParam param) throws ExceptionWrapper;

    /**
     * 预约报名提醒
     * @param param
     */
    void reserve(ActivityDetailParam param) throws ExceptionWrapper;

    /**
     * 根据活动ID查询活动状态
     * @param activityId
     * @return
     */
    int getActivityState(Integer activityId);

    /**
     * 获取全部参展品牌
     * @param param
     * @return
     */
    BaseResponseModel getAllExhibitionBrand(ExhibitionBrandParam param);

    /**
     * 直播大会点赞
     * @param id
     * @return
     */
    Integer thumbUp(Integer id);

}
