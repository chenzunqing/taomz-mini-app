package com.taomz.mini.apps.dto.activity;

import com.taomz.mini.apps.dto.activity.vo.ActivityPlanVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ActivityExtendDTO implements Serializable {
    /**
     * 活动扩展表主键
     */
    private Integer activityExtendId;

    /**
     * 活动主键ID
     */
    private Integer activityId;

    /**
     * 关联活动  活动类型是会员活动时可选关联品牌
     */
    private String relationActivity;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 会议流程
     */
    private String process;

    /**
     * 会议简介
     */
    private String introduction;

    /**
     * 费用说明/须知
     */
    private String feeRemark;
    /**
     * 议程方式  1富文本编辑  2时间轴编辑框
     */
    private Integer processType;
    /**
     * 会议议程计划
     */
    List<ActivityPlanVo> processPlanList;


}