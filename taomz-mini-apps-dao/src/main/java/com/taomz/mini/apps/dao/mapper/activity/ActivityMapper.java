package com.taomz.mini.apps.dao.mapper.activity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taomz.mini.apps.dto.activity.ActivityAppListDTO;
import com.taomz.mini.apps.dto.activity.vo.ActivityScheduleScreeningVo;
import com.taomz.mini.apps.model.activity.Activity;
import com.taomz.mini.apps.param.ActivityParam;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author : liaoxiaoli
 * @version V1.0
 * @title : ActivityMapper
 * @Package : com.taomz.mini.apps.dao.mapper.activity
 * @Description: 活动
 * @date 2020/11/19 19:13
 **/
public interface ActivityMapper extends BaseMapper<Activity> {


    /**
     * 小程序查询正在报名中的活动
     * @param activityId
     * @return
     */
    List<ActivityAppListDTO> signingUpActivity(@Param("activityId")Integer activityId);

    /**
     * 小程序查询正在报名中的活动日程
     * @return
     */
    List<ActivityScheduleScreeningVo> getSigningUpScheduleScreening();

    /**
     * 小程序查询活动列表
     * @param param
     * @return
     */
    List<ActivityAppListDTO> getActivityList(Page page, @Param("param") ActivityParam param);

    /**
     * 小程序详情页查询特色活动  组合活动下其他活动
     * @param prodActId
     * @param activityId
     * @return
     */
    List<ActivityAppListDTO> getProdChildActivity(@Param("prodActId")Integer prodActId,
                                                  @Param("activityId")Integer activityId);
}
