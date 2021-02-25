package com.taomz.mini.apps.param;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ActivityExhibitorsBrandParam extends PageParam implements Serializable {
    /**
     * 活动Id
     */
    private Integer activityId;
    /**
     * 展位号
     */
    private String boothNumber;
    /**
     * 报名品牌ID
     */
    private Integer brandId;
    /**
     * 审核状态 0初始化 1通过 2不通过 4已支付 5取消 6退款中 7退款成功 10拒绝退款
     */
    private Integer auditState;
    /**
     * 合作类型 招商类型 1冠名 2联合赞助 3参展
     */
    private Integer investmentType;
    /**
     * 活动关联品牌
     */
    private String relationActivity;
}
