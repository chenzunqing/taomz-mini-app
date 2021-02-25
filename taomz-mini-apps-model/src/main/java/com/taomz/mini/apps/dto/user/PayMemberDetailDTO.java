package com.taomz.mini.apps.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * package ：com.ishop.dto
 * describe ：
 * Date ： 2018/9/14 11:42
 *
 * @author liaobing
 */
@Getter
@Setter
public class PayMemberDetailDTO implements Serializable {
    private static final long serialVersionUID = 1391802173990435595L;

    /**
     * 用户账号
     */
    private String accountNo;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 缴费单位
     */
    private String paymentUnit;
}
