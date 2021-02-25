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
 * 专业观众报名设置
 * </p>
 *
 * @author liaobing
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_non_mem_config")
public class NonMemConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 活动产品id
     */
    private Integer actProdId;

    /**
     * 活动设置-逛展，1 开启，0 关闭
     */
    private Integer actSetStrollFlag;

    /**
     * 逛展开始时间显示
     */
    private Date strollStartTime;

    /**
     * 逛展结束时间显示
     */
    private Date strollEndTime;

    /**
     * 逛展总人数上限
     */
    private Integer strollTotal;

    /**
     * 单个专业观众可逛展人数上限
     */
    private Integer strollSingleNum;

    /**
     * 逛展观众是否收费：1 收费，0 否
     */
    private Boolean strollChargeFlag;

    /**
     * 逛展观众收费金额
     */
    private BigDecimal strollCharge;

    /**
     * 协议内容
     */
    private String agreementAbout;

    /**
     * 备注
     */
    private String remark;

    /**
     * h5观众报名设置是否生效：1 生效 ，0否
     */
    private Boolean signUpFlag;

    /**
     * 报名背景图地址
     */
    private String backgroundImg;

    /**
     * 报名背景颜色：#00000
     */
    private String backgroundColor;

    /**
     * 报名开始时间
     */
    private Date signUpStartTime;

    /**
     * 报名结束时间
     */
    private Date signUpEndTime;

    /**
     * 会员app是否现场报名：1 是，0否
     */
    private Integer sceneSignUpFlag;

    /**
     * 现场报名开始时间
     */
    private Date sceneUpStartTime;

    /**
     * 现场报名结束时间
     */
    private Date sceneUpEndTime;

    /**
     * 是否h5邀请码设置：1 是，0 否
     */
    private Integer inviteFlag;

    /**
     * 会员邀请码人数上限
     */
    private Integer inviteMemNum;

    /**
     * 品牌邀请码人数上限
     */
    private Integer inviteBrandNum;

    /**
     * h5退款是否设置：1 是，0 否
     */
    private Integer refundFlag;

    /**
     * 退款开始时间
     */
    private Date refundStartTime;

    /**
     * 退款结束时间
     */
    private Date refundEndTime;

    /**
     * 是否有效：1是，0否
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 分享图地址
     */
    private String shareImg;

    /**
     * 分享标题
     */
    private String shareTitle;

    /**
     * 分享地址
     */
    private String shareAddress;

    /**
     * 分享简介
     */
    private String shareAbout;

    /**
     * 是否实名开关（0-否，1-是）
     */
    private Integer isOpenReal;

    /**
     * 报名前提示文案
     */
    private String promptCopy;

    /**
     * 进场须知
     */
    private String entryNotice;

    /**
     * 二维码地址
     */
    private String qrCode;

    /**
     * 二维码说明
     */
    private String qrCodeDesc;


}
