package com.taomz.mini.apps.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户归属类别
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_category")
public class UserCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户类别（商会会员，网红，品牌方）
     */
    private String categoryType;

    /**
     * 会员等级ID
     */
    private Integer gradeId;

    /**
     * 用户状态（1-待初审，2-待复审，3-线上审核通过待付费，4-审核通过已付费，5-已拒绝，6-会员过期，7-线下付款待审核，8-线下付款已拒绝，9-线下续费待确认，10-线下续费已拒绝）
     */
    private Integer status;

    /**
     * 第一次入会时间
     */
    private Date initiateTime;

    /**
     * 最近一次的入会时间
     */
    private Date lastInitiateTime;

    /**
     * 过期时间
     */
    private Date expiryTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 会员试用（0/否，1/是）
     */
    private Integer isTryMember;

    /**
     * 会员是否申请过试用会员（0/否，1/是)
     */
    private Integer applyTryMember;


}
