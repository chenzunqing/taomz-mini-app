package com.taomz.mini.apps.service.activity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.activity.ActivityExtendMapper;
import com.taomz.mini.apps.dao.mapper.activity.ActivityPlanMapper;
import com.taomz.mini.apps.dto.activity.ActivityExtendDTO;
import com.taomz.mini.apps.dto.activity.vo.ActivityPlanVo;
import com.taomz.mini.apps.model.activity.ActivityExtend;
import com.taomz.mini.apps.model.activity.ActivityPlan;
import com.taomz.mini.apps.service.activity.ActivityExtendService;
import com.taomz.mini.apps.util.DateUtil;
import com.taomz.mini.apps.util.enums.activity.ProcessTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 活动扩展表 服务实现类
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-20
 */
@Service
public class ActivityExtendServiceImpl extends ServiceImpl<ActivityExtendMapper, ActivityExtend> implements ActivityExtendService {

    @Autowired
    private ActivityPlanMapper activityPlanMapper;
    @Override
    public ActivityExtendDTO getByActivityId(Integer activityId) {
        ActivityExtendDTO extendDTO = this.baseMapper.getByActivityId(activityId);
        if (extendDTO != null
                && extendDTO.getProcessType() == ProcessTypeEnum.TIME_LINE.getIntValue()) {
            List<ActivityPlan> planList = activityPlanMapper.getPlanByActivityId(activityId);
            if(CollectionUtils.isEmpty(planList)) return extendDTO;
            Set<String> onlyTimeSet = new HashSet<>();
            List<ActivityPlanVo> processPlanList = new ArrayList<>();
            //此处结构由产品全春设计修改  议程第一天显示全部  后面日期正常 看到此处代码的人喝喝喝三秒钟
            String firstTime = DateUtil.dateToDayString(planList.get(0).getPlanTime());
            List<ActivityPlan> noFirstList = new ArrayList<>();
            for (ActivityPlan plan : planList) {
                String planTime = DateUtil.dateToDayString(plan.getPlanTime());
                if (onlyTimeSet.add(planTime)) {
                    List<ActivityPlan> planDetailList = new ArrayList<>();
                    planDetailList.add(plan);

                    processPlanList.add(ActivityPlanVo.builder()
                            .planTime(planTime)
                            .planList(planDetailList).build());

                } else {
                    for (ActivityPlanVo activityPlanVo:processPlanList) {
                        if(activityPlanVo.getPlanTime().equals(planTime)){
                            activityPlanVo.getPlanList().add(plan);
                            continue;
                        }
                    }
                }
                if (!firstTime.equals(planTime)) {
                    noFirstList.add(plan);
                }
            }
            if(CollectionUtils.isNotEmpty(noFirstList)){
                for (ActivityPlanVo activityPlanVo:processPlanList) {
                    if(activityPlanVo.getPlanTime().equals(firstTime)){
                        activityPlanVo.getPlanList().addAll(noFirstList);
                        continue;
                    }
                }
            }
            extendDTO.setProcessPlanList(processPlanList);
        }
        return extendDTO;
    }
}
