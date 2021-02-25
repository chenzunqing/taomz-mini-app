package com.taomz.mini.apps.web.controller.activity;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.param.ActivityDetailParam;
import com.taomz.mini.apps.param.ActivityParam;
import com.taomz.mini.apps.param.ExhibitionBrandParam;
import com.taomz.mini.apps.service.activity.ActivityService;
import com.taomz.mini.apps.service.dto.activity.ActivityQueryDTO;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.web.controller.base.BaseController;
import com.taomz.sha.util.response.BaseResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ActivityController extends BaseController {
    @Autowired
    private ActivityService activityService;

    /**
     * 查询正在报名中和预热的活动
     * @param activityQueryDTO
     * @return
     */
    @PostMapping("/wx/activity/signing_up_list")
    public String signingUpList(@RequestBody ActivityQueryDTO activityQueryDTO) {
        return JSON.toJSONString(activityService.signingUpActivity(activityQueryDTO, this.getCurrentUserId())
                , DEFAULT_FEATURES);
    }

    /**
     * 查询正在报名中和预热的活动日程  按移动端要求返回List 不返回MAP
     * @return
     */
    @PostMapping(value = "/wx/activity/signing_up_schedule")
    public String getSigningUpSchedule() {
        return JSON.toJSONString(activityService.getSigningUpScheduleScreening() ,DEFAULT_FEATURES);
    }

    /**
     * 查询活动列表
     * @param param
     * @return
     */
    @PostMapping(value = "/wx/activity/list")
    public String getActivityList(@RequestBody ActivityParam param) {
        return JSON.toJSONString(activityService.getActivityList(param) ,DEFAULT_FEATURES);
    }

    /**
     * 小程序端活动详情页
     * @param param
     * @return
     * @throws ExceptionWrapper
     */
    @PostMapping(value = "/wx/activity/detail")
    public String getActivityDetail(@Valid @RequestBody ActivityDetailParam param) throws ExceptionWrapper {
        param.setUserId(this.getCurrentUserId());
        return JSON.toJSONString(activityService.getAppActivityDetailByActivityId(param) ,DEFAULT_FEATURES);
    }

    /**
     * 预约活动报名提醒
     * @param param
     * @return
     * @throws ExceptionWrapper
     */
    @PostMapping(value = "/wx/activity/reserve")
    public String reserve(@Valid @RequestBody ActivityDetailParam param) throws ExceptionWrapper {
        param.setUserId(this.getCurrentUserId());
        activityService.reserve(param);
        return JSON.toJSONString(new BaseResponseModel().warpSuccess().setDesc("预约活动报名提醒成功"));
    }

    /**
     * 根据活动id获取全部参展品牌
     * param
     * @return
     */
    @PostMapping("/wx/activity/detail/v2/get_all_exhibition_brand")
    public String getAllExhibitionBrand(@Valid @RequestBody ExhibitionBrandParam param) {
        return JSON.toJSONString(activityService.getAllExhibitionBrand(param));
    }

    /**
     * 直播大会点赞
     *
     * @return
     */
    @PostMapping(value = "/wx/activity/live/thumbUp")
    public String thumbUp(@RequestBody ExhibitionBrandParam param) {
        return JSON.toJSONString(activityService.thumbUp(param.getId()));
    }
}
