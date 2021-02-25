package com.taomz.mini.apps.model.activity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 活动招商配置表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_activity_investment_config")
public class ActivityInvestmentConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动品牌设置表主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 耦合字段 活动产品ID
     */
    private Integer actProdId;

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
     * 是否可报冠名  1是 0否
     */
    private Integer titleFlag;

    /**
     * 冠名费用
     */
    private BigDecimal titleFee;

    /**
     * 冠名可带工作人员数限制
     */
    private Integer titleWorkLimitNum;

    /**
     * 冠名赠送房间数 注：有住宿才配置
     */
    private Integer titleRoomNum;

    /**
     * 冠名总数量限制
     */
    private Integer titleLimitNum;

    /**
     * 已报名冠名数量
     */
    private Integer titleNum;

    /**
     * 是否可报联合赞助  1是  0否
     */
    private Integer sponsorshipFlag;

    /**
     * 联合赞助费
     */
    private BigDecimal sponsorshipFee;

    /**
     * 联合赞助可带工作人员数限制
     */
    private Integer sponsorshipWorkLimitNum;

    /**
     * 联合赞助赠送房间数  注：有住宿才配置
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
     * 是否可报一般参展  1是 0否
     */
    private Integer boothFlag;

    /**
     * 展位费/个
     */
    private BigDecimal boothFee;

    /**
     * 参展可带工作人员数限制
     */
    private Integer boothWorkLimitNum;

    /**
     * 展位品牌数限制
     */
    private Integer boothBrandNum;

    /**
     * 展位赠送房间数  注：有住宿才配置
     */
    private Integer boothRoomNum;

    /**
     * 参展数量限制
     */
    private Integer boothLimitNum;

    /**
     * 已报名参展数量
     */
    private Integer boothNum;

    /**
     * 一般展位单品牌方最大购买展位数
     */
    private Integer boothBuyMax;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 修改时间
     */
    private Integer updateTime;

    /**
     * 是否有效 1有效 0无效
     */
    private Integer deleteFlag;

    /**
     * 参展协议
     */
    private Integer boothAgreementId;

    /**
     * 联合赞助协议
     */
    private Integer sponsorshipAgreementId;

    /**
     * 冠名协议
     */
    private Integer titleAgreementId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否已经发送展位号  1已发 0未发
     */
    private Integer boothNoFlag;

    /**
     * 是否有住宿  1是 0否
     */
    private Integer lodgingFlag;

    /**
     * 虚拟工作人员总数
     */
    private Integer virtualWorkNum;


}
