package com.taomz.mini.apps.service.activity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.taomz.mini.apps.dao.mapper.activity.ActivityMapper;
import com.taomz.mini.apps.dao.mapper.activity.ActivityReserveRemindMapper;
import com.taomz.mini.apps.dao.mapper.activity.LiveMeetingMapper;
import com.taomz.mini.apps.dao.mapper.brand.BrandBasicInfoMapper;
import com.taomz.mini.apps.dto.activity.ActivityAppDetailDTO;
import com.taomz.mini.apps.dto.activity.ActivityAppListDTO;
import com.taomz.mini.apps.dto.activity.ActivityInvestmentConfigDTO;
import com.taomz.mini.apps.dto.activity.ActivityMemberConfigDTO;
import com.taomz.mini.apps.dto.activity.*;
import com.taomz.mini.apps.dto.activity.vo.ActivityScheduleMonthVo;
import com.taomz.mini.apps.dto.activity.vo.ActivityScheduleScreeningVo;
import com.taomz.mini.apps.dto.brand.BrandInfoDTO;
import com.taomz.mini.apps.model.activity.Activity;
import com.taomz.mini.apps.model.activity.ActivityReserveRemind;
import com.taomz.mini.apps.model.activity.NonMemConfig;
import com.taomz.mini.apps.param.ActivityDetailParam;
import com.taomz.mini.apps.param.ActivityExhibitorsBrandParam;
import com.taomz.mini.apps.param.ActivityParam;
import com.taomz.mini.apps.param.ExhibitionBrandParam;
import com.taomz.mini.apps.service.activity.*;
import com.taomz.mini.apps.service.comm.DictService;
import com.taomz.mini.apps.service.dto.activity.ActivityQueryDTO;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.mini.apps.util.DateUtil;
import com.taomz.mini.apps.util.enums.DeleteEnum;
import com.taomz.mini.apps.util.enums.ErrorMessageEnum;
import com.taomz.mini.apps.util.enums.activity.ActivityTypeEnum;
import com.taomz.mini.apps.util.enums.activity.JudgeFlagEnum;
import com.taomz.mini.apps.util.enums.activity.PublishEnum;
import com.taomz.mini.apps.util.enums.activity.StateEnum;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.reslut.PageUtils;
import com.taomz.sha.util.response.BaseResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Autowired
    private DictService dictService;
    @Autowired
    private ActivityAddressService activityAddressService;
    @Autowired
    private ActivityReserveRemindMapper reserveRemindMapper;
    @Autowired
    private ActivityInvestmentConfigService investmentConfigService;
    @Autowired
    private ActivityMemberConfigService memberConfigService;
    @Autowired
    private NonMemConfigService nonMemConfigService;
    @Autowired
    private ActivityExtendService activityExtendService;
    @Autowired
    private LiveMeetingMapper liveMeetingMapper;
    @Autowired
    private BrandBasicInfoMapper brandBasicInfoMapper;
    @Autowired
    private ActivityApplyInvestmentBrandService investmentBrandService;
    @Autowired
    private RedisService redisService;

    @Override
    public Activity getActivityById(Integer activityId) {
        return this.baseMapper.selectById(activityId);
    }

    @Override
    public BaseResponseModel signingUpActivity(ActivityQueryDTO activityQuery, Integer userId) {
        BaseResponseModel response = new BaseResponseModel();
        List<ActivityAppListDTO> resultList = this.baseMapper.signingUpActivity(activityQuery.getActivityId());
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (ActivityAppListDTO activity : resultList) {
                activity.setActivityWay(dictService.dictNameByCode(activity.getActivityWay()));
                activity.setAddress(activityAddressService.getAddressByActivityId(activity.getActivityId()));
                activity.setReserveFlag(getReserveFlag(activity.getActivityId(), userId));
                activity.setState(getActivityState(activity.getActivityId()));

                if (ActivityTypeEnum.BRAND_ACTIVITY.name().equals(activity.getActivityRole())) {
                    ActivityInvestmentConfigDTO investmentConfig = investmentConfigService
                            .getInvestmentConfigByActivityId(activity.getActivityId());

                    if (investmentConfig != null) {
                        activity.setSignUpStartTime(investmentConfig.getBrandStartTime());
                        activity.setSignUpEndTime(investmentConfig.getBrandEndTime());
                    }
                }

                if (ActivityTypeEnum.MEMBER_ACTIVITY.name().equals(activity.getActivityRole())) {
                    ActivityMemberConfigDTO memberConfig = memberConfigService.getMemberConfigByActivityId(activity.getActivityId());
                    if (memberConfig != null) {
                        activity.setSignUpStartTime(memberConfig.getMemberStartTime());
                        activity.setSignUpEndTime(memberConfig.getMemberEndTime());
                        int applyCount = memberConfigService.getApplyCount(activity.getActivityId()) + memberConfig.getVirtualApplyEnterNum();

                        if (activity.getState() == StateEnum.APPLYING.getIntValue()
                                && memberConfig.getViewEnterNumFlag() == JudgeFlagEnum.YES.getIntValue()
                                && applyCount > 10) {
                            activity.setViewApplyTotal(JudgeFlagEnum.YES.getIntValue());
                            activity.setApplyTotal(applyCount);
                        }
                    }

                }


                if (ActivityTypeEnum.PROFESSIONAL_AUDIENCE_ACTIVITY.name().equals(activity.getActivityRole())) {
                    NonMemConfig configDTO = nonMemConfigService.getNonMemConfigByActivityId(activity.getActivityId());
                    if (configDTO != null) {
                        activity.setSignUpStartTime(configDTO.getSignUpStartTime());
                        activity.setSignUpEndTime(configDTO.getSignUpEndTime());
                    }

                }
            }
        }
        return response.warpSuccess().setContent(resultList);
    }

    @Override
    public BaseResponseModel getSigningUpScheduleScreening() {
        BaseResponseModel response = new BaseResponseModel();
        List<ActivityScheduleScreeningVo> list = this.baseMapper.getSigningUpScheduleScreening();
        if (CollectionUtils.isEmpty(list)) return null;
        List<ActivityScheduleMonthVo> resultList = new ArrayList<>();
        Set<String> onlyMonthSet = new HashSet<>();
        for (ActivityScheduleScreeningVo scheduleScreening : list) {
            scheduleScreening.setActivityWay(dictService.dictNameByCode(scheduleScreening.getActivityWay()));
            String monthSchedule = DateUtil.getMonthByTime(scheduleScreening.getStartTime());
            if (onlyMonthSet.add(monthSchedule)) {
                List<ActivityScheduleScreeningVo> scheduleList = new ArrayList<>();
                scheduleList.add(scheduleScreening);

                resultList.add(ActivityScheduleMonthVo.builder()
                        .monthTime(monthSchedule)
                        .scheduleList(scheduleList).build());

            } else {
                List<ActivityScheduleMonthVo> monthList = resultList.stream().filter(
                        month -> Objects.equals(month.getMonthTime(), monthSchedule)).collect(Collectors.toList());
                List<ActivityScheduleScreeningVo> scheduleList = monthList.get(0).getScheduleList();

                scheduleList.add(scheduleScreening);

            }
        }
        return response.warpSuccess().setContent(resultList);
    }

    @Override
    public BaseResponseModel getActivityList(ActivityParam param) {
        Page<ActivityAppListDTO> page = new Page<>(param.getPageNum(), param.getPageSize());
        BaseResponseModel response = new BaseResponseModel();
        List<ActivityAppListDTO> list = this.baseMapper.getActivityList(page, param);
        if (CollectionUtils.isNotEmpty(list)) {
            for (ActivityAppListDTO activity : list) {
                activity.setActivityWay(dictService.dictNameByCode(activity.getActivityWay()));
                if (StringUtils.isNotEmpty(activity.getVideoUrl()) || activity.getMeetingLiveId() != null) {
                    activity.setHaveVideo(JudgeFlagEnum.YES.getIntValue());
                }
            }
            page.setRecords(list);
        }
        return response.warpSuccess().setContent(new PageUtils(page));
    }

    @Override
    public BaseResponseModel getAppActivityDetailByActivityId(ActivityDetailParam param) throws ExceptionWrapper {
        BaseResponseModel response = new BaseResponseModel();
        // 活动基本信息
        Activity activity = this.getActivityById(param.getActivityId());
        if (activity == null || activity.getDeleteFlag() == DeleteEnum.INVLID.getIntValue()) {
            log.error("activity does not exists.");
            throw new ExceptionWrapper(ErrorMessageEnum.活动不存在.getMessage());
        }
        if (activity.getPublishState() == PublishEnum.DOWN.getIntVlue()) {
            log.error("activity is down.");
            throw new ExceptionWrapper(ErrorMessageEnum.活动已下架.getMessage());
        }
        ActivityAppDetailDTO resultDTO = new ActivityAppDetailDTO();
        BeanCopier beanCopier = BeanCopier.create(Activity.class, ActivityAppDetailDTO.class, false);
        beanCopier.copy(activity, resultDTO, null);
        // 活动地址
        resultDTO.setAddress(activityAddressService.getAddressByActivityId(param.getActivityId()));
        // 活动扩展信息
        resultDTO.setExtend(activityExtendService.getByActivityId(param.getActivityId()));
        //参展品牌
        List<ActivityBrandDTO> brandList = investmentBrandService.getExhibitorsBrand(param.getActivityId());
        resultDTO.setViewBrandFlag(CollectionUtils.isNotEmpty(brandList) ? 1 : 0);
        resultDTO.setBrandList(brandList);

        if (activity.getMeetingLiveId() != null) {
            resultDTO.setHaveVideo(JudgeFlagEnum.YES.getIntValue());
            //直播信息
            resultDTO.setLiveMeeting(liveMeetingMapper.selectById(activity.getMeetingLiveId()));
        } else {
            //特色活动
            resultDTO.setActivityList(getProdChildActivity(activity.getActProdId(), param.getActivityId(), param.getUserId()));
            if (StringUtils.isNotEmpty(activity.getVideoUrl())) {
                resultDTO.setHaveVideo(JudgeFlagEnum.YES.getIntValue());
            }
        }
        //报名截止时间
        if (ActivityTypeEnum.BRAND_ACTIVITY.name().equals(activity.getActivityRole())) {
            ActivityInvestmentConfigDTO investmentConfig = investmentConfigService
                    .getInvestmentConfigByActivityId(param.getActivityId());
            if (investmentConfig != null) {
                resultDTO.setSignStartEndTime(investmentConfig.getBrandStartTime());
                resultDTO.setSignUpEndTime(investmentConfig.getBrandEndTime());
            }

        }

        if (ActivityTypeEnum.MEMBER_ACTIVITY.name().equals(activity.getActivityRole())) {
            ActivityMemberConfigDTO memberConfig = memberConfigService.getMemberConfigByActivityId(param.getActivityId());
            if (memberConfig != null) {
                resultDTO.setSignStartEndTime(memberConfig.getMemberStartTime());
                resultDTO.setSignUpEndTime(memberConfig.getMemberEndTime());
                int applyCount = memberConfigService.getApplyCount(param.getActivityId()) + memberConfig.getVirtualApplyEnterNum();
                //会员报名数开关开且报名超过10人展示
                if (memberConfig.getViewEnterNumFlag() == JudgeFlagEnum.YES.getIntValue()
                        && applyCount > 10) {
                    resultDTO.setViewApplyTotal(JudgeFlagEnum.YES.getIntValue());
                    resultDTO.setApplyTotal(applyCount);
                }
            }

        }

        if (ActivityTypeEnum.PROFESSIONAL_AUDIENCE_ACTIVITY.name().equals(activity.getActivityRole())) {
            NonMemConfig configDTO = nonMemConfigService.getNonMemConfigByActivityId(param.getActivityId());
            if (configDTO != null) {
                resultDTO.setSignStartEndTime(configDTO.getSignUpStartTime());
                resultDTO.setSignUpEndTime(configDTO.getSignUpEndTime());
            }
        }

        resultDTO.setState(getActivityState(param.getActivityId()));
        resultDTO.setReserveFlag(getReserveFlag(param.getActivityId(), param.getUserId()));
        return response.warpSuccess().setContent(resultDTO);
    }

    @Override
    public void reserve(ActivityDetailParam param) throws ExceptionWrapper {
        QueryWrapper<ActivityReserveRemind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", param.getUserId());
        queryWrapper.eq("activity_id", param.getActivityId());

        int count = reserveRemindMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new ExceptionWrapper(ErrorMessageEnum.已预约.getMessage());
        }

        ActivityReserveRemind reserveRemind = new ActivityReserveRemind();
        Date now = new Date();
        reserveRemind.setCreateTime(now);
        reserveRemind.setUpdateTime(now);
        reserveRemind.setActivityId(param.getActivityId());
        reserveRemind.setUserId(param.getUserId());
        reserveRemind.setState(0);
        reserveRemind.setDeleteFlag(DeleteEnum.EFFECTIVE.getIntValue());
        reserveRemindMapper.insert(reserveRemind);
    }


    @Override
    public int getActivityState(Integer activityId) {
        // 活动基本信息
        Activity activity = this.getActivityById(activityId);
        Date now = new Date();
        if (now.after(activity.getEndTime())) {
            return StateEnum.OVER.getIntValue();
        }
        if (DateUtil.isEffectiveDate(now, activity.getStartTime(), activity.getEndTime())) {
            return StateEnum.STARTING.getIntValue();
        }

        if (ActivityTypeEnum.BRAND_ACTIVITY.name().equals(activity.getActivityRole())) {
            ActivityInvestmentConfigDTO investmentConfig = investmentConfigService
                    .getInvestmentConfigByActivityId(activityId);

            if (investmentConfig != null) {
                if (DateUtil.isEffectiveDate(now, investmentConfig.getBrandStartTime(), investmentConfig.getBrandEndTime())) {
                    return StateEnum.APPLYING.getIntValue();
                }
                if (now.after(investmentConfig.getBrandEndTime())) {
                    return StateEnum.OVER.getIntValue();
                }
            }
        }

        if (ActivityTypeEnum.MEMBER_ACTIVITY.name().equals(activity.getActivityRole())) {
            ActivityMemberConfigDTO memberConfig = memberConfigService.getMemberConfigByActivityId(activityId);
            if (memberConfig != null) {
                if (memberConfig.getTimeoutSignUpFlag() == JudgeFlagEnum.NO.getIntValue()) {
                    if (DateUtil.isEffectiveDate(now, memberConfig.getMemberStartTime(), memberConfig.getMemberEndTime())) {
                        return StateEnum.APPLYING.getIntValue();
                    }
                    if (now.after(memberConfig.getMemberEndTime())) {
                        return StateEnum.OVER.getIntValue();
                    }
                } else {
                    if (DateUtil.isEffectiveDate(now, memberConfig.getTimeoutSignUpStartTime(), memberConfig.getTimeoutSignUpEndTime())) {
                        return StateEnum.APPLYING.getIntValue();
                    }
                    if (now.after(memberConfig.getTimeoutSignUpEndTime())) {
                        return StateEnum.OVER.getIntValue();
                    }
                }

            }
        }
        if (ActivityTypeEnum.PROFESSIONAL_AUDIENCE_ACTIVITY.name().equals(activity.getActivityRole())) {
            NonMemConfig configDTO = nonMemConfigService.getNonMemConfigByActivityId(activityId);
            if (configDTO != null) {
                if (configDTO.getSceneSignUpFlag() == 0) {
                    if (DateUtil.isEffectiveDate(now, configDTO.getSignUpStartTime(), configDTO.getSignUpEndTime())) {
                        return StateEnum.APPLYING.getIntValue();
                    }
                    if (now.after(configDTO.getSignUpEndTime())) {
                        return StateEnum.OVER.getIntValue();
                    }
                } else {
                    if (DateUtil.isEffectiveDate(now, configDTO.getSceneUpStartTime(), configDTO.getSceneUpEndTime())) {
                        return StateEnum.APPLYING.getIntValue();
                    }
                    if (now.after(configDTO.getSceneUpEndTime())) {
                        return StateEnum.OVER.getIntValue();
                    }
                }
            }

        }

        return StateEnum.NOT_START.getIntValue();
    }

    @Override
    public BaseResponseModel getAllExhibitionBrand(ExhibitionBrandParam param) {
        BaseResponseModel response = new BaseResponseModel();
        String ids = brandBasicInfoMapper.getRelationActivity(param.getActivityId());
        List<String> idList = Lists.newArrayList();
        if (StringUtils.isNotEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }
        List arrList = new ArrayList(idList);
        arrList.add(param.getActivityId().toString());
        response.warpSuccess().setContent(brandBasicInfoMapper.getAllExhibitionBrand(arrList, param.getBrandName()));
        return response;
    }

    @Override
    public Integer thumbUp(Integer id) {
        Object obj = redisService.get(RedisRootNamespace.LIVE_MEETING + id);
        Integer thumbUpNumber = 0;
        if (Objects.nonNull(obj)) {
            thumbUpNumber = Integer.valueOf(obj.toString()).intValue();
        }
        redisService.set(RedisRootNamespace.LIVE_MEETING + id, ++thumbUpNumber);
        return thumbUpNumber;
    }

    private int getReserveFlag(Integer activityId, Integer userId) {
        QueryWrapper<ActivityReserveRemind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("activity_id", activityId);
        int count = reserveRemindMapper.selectCount(queryWrapper);
        if (count > 0) {
            return JudgeFlagEnum.YES.getIntValue();
        } else {
            return JudgeFlagEnum.NO.getIntValue();
        }
    }

    public List<ActivityAppListDTO> getProdChildActivity(Integer prodActId, Integer activityId, Integer userId) {
        List<ActivityAppListDTO> resultList = this.baseMapper.getProdChildActivity(prodActId, activityId);
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (ActivityAppListDTO activity : resultList) {
                activity.setAddress(activityAddressService.getAddressByActivityId(activity.getActivityId()));
                activity.setState(getActivityState(activity.getActivityId()));
                activity.setReserveFlag(getReserveFlag(activity.getActivityId(), userId));
            }
        }
        return resultList;
    }

}
