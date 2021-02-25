package com.taomz.mini.apps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 发票表开票历史
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_invoice_history")
public class InvoiceHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发票类型（1-电子发票，2-增值税发票，3-普通发票）
     */
    private Integer invoiceType;

    /**
     * 抬头类型（0-公司，1-个人）
     */
    private Integer titleType;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 抬头名称
     */
    private String titleName;

    /**
     * 纳税人识别号
     */
    private String dutyParagraph;

    /**
     * 注册电话
     */
    private String telephone;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 发票内容
     */
    private String invoiceContent;

    /**
     * 收件邮箱
     */
    private String email;

    /**
     * 状态（0-拒绝，1-资质审核中，2-开票中，3-开票成功，9-资质不通过）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户ID
     */
    private Integer createUserId;

    /**
     * 单位注册地址
     */
    private String registerAddress;

    /**
     * 开票员
     */
    private String clerk;

    /**
     * 发票号码
     */
    private String fphm;

    /**
     * 发票代码
     */
    private String fpdm;

    /**
     * 发票请求流水号
     */
    private String fpqqlsh;

    /**
     * 发票 pdf 地址
     */
    private String url;

    /**
     * 本地存储地址
     */
    private String localPath;

    /**
     * 备注
     */
    private String remark;

    /**
     * 开票类型(1,正票;2,红票)
     */
    private Integer kptype;

    /**
     * 冲红标识(0:正常，1:发票已冲红，2：作废)
     */
    private Integer flag;

    /**
     * 开票订单号
     */
    private String orderno;

    /**
     * 发票接收地址
     */
    private String address;

    /**
     *  待开标志（0：待开/未支付，1：允许开票/支付成功）
     */
    private Integer dealFlag;

    /**
     * 允许开票时间
     */
    private Date kpDate;

    /**
     * 通知手机号码(开票必填)
     */
    private String noticePhone;

    /**
     * 子账号ID
     */
    private Integer subUserId;

    /**
     * 审核用户ID
     */
    private String confirmUserId;

    /**
     * 审核时间
     */
    private Date confirmTime;

    /**
     * 发票模板ID
     */
    private Integer invoiceId;

    /**
     * 对应蓝票ID
     */
    private Integer blueInvoiceId;

    /**
     * 用户类别（商会会员，网红，品牌方）
     */
    private String categoryType;

    /**
     * 订单金额
     */
    private BigDecimal orderFee;

    /**
     * 发票金额
     */
    private BigDecimal invoiceAmount;

    /**
     * 审核描述
     */
    private String description;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 移动号码
     */
    private String receiverMobile;

    /**
     * 物流状态(0:待邮寄，1:邮寄成功)
     */
    private Integer logisticsStatus;

    /**
     * 开票时间
     */
    private Date kprq;

    /**
     * 物流公司编号
     */
    private String logisticsNum;

    /**
     * 快递单号
     */
    private String courierNum;

    /**
     * 开票状态（0-通过，1-未审核）
     */
    private Integer kpStatus;

    /**
     * 调用次数
     */
    private Integer invokeCount;

    /**
     * 开票方式(0：自动,1：手动)
     */
    private Integer kpfs;

    /**
     * 开票信息返回
     */
    private String kpInfo;


}
