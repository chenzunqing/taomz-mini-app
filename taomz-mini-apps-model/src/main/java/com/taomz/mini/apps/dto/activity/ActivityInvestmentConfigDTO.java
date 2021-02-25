package com.taomz.mini.apps.dto.activity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ActivityInvestmentConfigDTO implements Serializable {
    /**
     * 活动品牌设置表主键ID
     */
    private Integer investmentConfigId;

    /**
     * 活动ID
     */
    private Integer activityId;

    /**
     * 品牌招商开始时间
     */
    private Date brandStartTime;

    /**
     * 品牌招商结束时间 
     */
    private Date brandEndTime;

    /**
     * 冠名费用
     */
    private BigDecimal titleFee;

    /**
     * 冠名工作人员数限制
     */
    private Integer titleWorkLimitNum;

    /**
     * 冠名赠送房间数 注：活动有酒店才配置
     */
    private Integer titleRoomNum;
    /**
     * 冠名数量限制
     */
    private Integer titleLimitNum;

    /**
     * 已报名冠名数量
     */
    private Integer titleNum;

    /**
     * 联合赞助费
     */
    private BigDecimal sponsorshipFee;

    /**
     * 联合赞助工作人员数限制
     */
    private Integer sponsorshipWorkLimitNum;

    /**
     * 联合赞助赠送房间数  注：活动有酒店才配置
     */
    private Integer sponsorshipRoomNum;
    /**
     * 联合赞助数量限制
     */
    private Integer sponsorshipLimitNum;

    /**
     * 已报名联合赞助数量
     */
    private Integer sponsorshipNum;
    /**
     * 联合赞助单品牌方最大购买展位数
     */
    private Integer sponsorshipBuyMax;

    /**
     * 展位费/个
     */
    private BigDecimal boothFee;

    /**
     * 参展工作人员数限制
     */
    private Integer boothWorkLimitNum;

    /**
     * 展位品牌数限制
     */
    private Integer boothBrandNum;

    /**
     * 展位赠送房间数  注：活动有酒店才配置
     */
    private Integer boothRoomNum;
    /**
     * 参展数量限制
     */
    private Integer boothLimitNum;
    /**
     * 一般展位单品牌方最大购买展位数
     */
    private Integer boothBuyMax;

    /**
     * 已报名参展数量
     */
    private Integer boothNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否有效 1有效 0无效
     */
    private Integer deleteFlag;


    /**
     * 招商报名状态
     */
    private Integer brandSignUpStatus;

    /**
     * 酒店商品ID
     */
    private Integer hotelGoodsId;
    /**
     * 是否已经发送展位号  1已发 0未发
     */
    private Integer boothNoFlag;
    /**
     * 参展协议ID
     */
    private Integer boothAgreementId;
    /**
     * 联合赞助协议ID
     */
    private Integer sponsorshipAgreementId;
    /**
     * 冠名协议ID
     */
    private Integer titleAgreementId;

    /**
     * 参展协议
     */
    private String boothAgreement;
    /**
     * 联合赞助协议
     */
    private String sponsorshipAgreement;
    /**
     * 冠名协议
     */
    private String titleAgreement;
    /**
     * 备注
     */
    private String remark;

 /**
     * 是否可报一般参展  1是 0否
     */
    private Integer boothFlag;
    /**
     * 是否可报冠名  1是 0否
     */
    private Integer titleFlag;
    /**
     * 是否可报联合赞助  1是  0否
     */
    private Integer sponsorshipFlag;

    /**
     * 是否有住宿  1是 0否
     */
    private Integer lodgingFlag;

}