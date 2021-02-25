package com.taomz.mini.apps.ext;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taomz.mini.apps.model.login.TUser;
import lombok.Data;

import java.util.Date;

/**
 * @author : wangling
 * @version V1.0
 * @title : TUserExt
 * @Package : com.taomz.mini.apps.ext
 * @Description:
 * @date 2020/11/24 11:45
 **/
@Data
public class TUserExt extends TUser {

    /**
     *用户归属类别id
     */
    private Integer userCategoryId;

    /**
     *用户类别（商会会员，网红，品牌方）
     */
    private String categoryType;

    /**
     *会员等级ID
     */
    private Integer gradeId;

    /**
     *用户状态（1-待初审，2-待复审，3-线上审核通过待付费，4-审核通过已付费，5-已拒绝，6-会员过期，7-线下付款待审核，8-线下付款已拒绝，9-线下续费待确认，10-线下续费已拒绝)
     */
    private Integer status;

    /**
     *第一次入会时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initiateTime;

    /**
     *最近一次的入会时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastInitiateTime;

    /**
     *过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiryTime;

    /**
     *创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *会员试用（0/否，1/是）
     */
    private Integer isTryMember;

    /**
     *会员是否申请过试用会员（0/否，1/是)
     */
    private Integer applyTryMember;
}
