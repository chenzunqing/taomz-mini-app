package com.taomz.mini.apps.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ProjectName: yf-coc
 * @Package: com.ishop.dto.order
 * @ClassName: BAMemberOfflinePaymentDetailDTO
 * @Author: guang
 * @Description: BA线下转账审核详情DTO
 * @Date: 2020/2/13 16:48
 * @Version: 1.0
 */
@Getter
@Setter
public class MemberOfflinePaymentDetailDTO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 银行卡账号
     */
    private String certificateNum;

    /**
     * 银行卡户名
     */
    private String payAccountName;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 应付金额
     */
    private BigDecimal totalFee;

    /**
     * 汇款凭证图片
     */
    private String certificateImg;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 主店铺名称
     */
    private String mainShopName;

    /**
     * 注册手机号
     */
    private String phone;

    /**
     * 拒绝理由
     */
    private String refusalReason;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 审核状态
     */
    private short confirmStatus;

    /**
     * 是否是续费(0续费/1缴费)
     */
    private Short isRenewal;
}
