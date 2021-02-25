package com.taomz.mini.apps.util.pay;

import com.taomz.mini.apps.util.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : PayOrder
 * @Package : com.taomz.mini.apps.util.pay
 * @Description: 付款订单
 * @date 2020/11/23 10:49
 **/
@Setter
@Getter
public class PayOrder {

    /**
     * 平台ID（标识不同平台）
     */
    private String platformId;

    /**
     * APPID
     */
    private String appId;

    /**
     * 商品名称
     */
    private String subject;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 商户订单号
     */
    @NotBlank(message = "订单号不能为空")
    private String outTradeNo;

    /**
     * 支付创建ip
     */
    private String spbillCreateIp;

    /**
     * 付款条码串,人脸凭证，有关支付代码相关的，
     */
    private String authCode;

    /**
     * WAP支付链接
     */
    private String wapUrl;

    /**
     * WAP支付网页名称
     */
    private String wapName;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 附加信息
     */
    private String addition;

    /**
     * 异步回调url
     */
    private String notifyUrl;

    /**
     * 同步回调url
     */
    private String returnUrl;

    /**
     * 用户付款中途退出返回商户网站的地址
     */
    private String quitUrl;

    /**
     * 交易类型
     */
    private String transactionType;

    /**
     * 支付类型
     */
    private PayType payType;

    /**
     * 订单过期时间
     */
    private Date expirationTime;

    /**
     * 交易类型
     */
    private TransactionType type;

    /**
     * 支付宝 PC扫码支付的方式，支持前置模式和
     * 跳转模式。
     * 前置模式是将二维码前置到商户的订单确认页的模式。需要商户在
     * 自己的页面中以 iframe 方式请求 支付宝页面。具体分为以下几种：
     * 0：订单码-简约前置模式，对应 iframe 宽度不能小于600px，高度不能小于300px；
     * 1：订单码-前置模式，对应iframe 宽度不能小于 300px，高度不能小于600px；
     * 2：订单码-跳转模式
     * 3：订单码-迷你前置模式，对应 iframe 宽度不能小于 75px，高度不能小于75px；
     * 4：订单码-可定义宽度的嵌入式二维码，商户可根据需要设定二维码的大小。
     *
     * 跳转模式下，用户的扫码界面是由支付宝生成的，不在商户的域名下。
     */
    private String qrPayMode;

    /**
     * 支付宝 商户自定义二维码宽度注：qr_pay_mode=4时该参数生效
     */
    private Long qrcodeWidth;

    public PayOrder() {

    }

    public PayOrder(String subject, String body, BigDecimal price, String outTradeNo, TransactionType type) {
        this.subject = subject;
        this.body = body;
        this.price = price;
        this.outTradeNo = outTradeNo;
        this.type = type;
    }

    public PayOrder(String subject, String body, BigDecimal price, String outTradeNo) {
        this.subject = subject;
        this.body = body;
        this.price = price;
        this.outTradeNo = outTradeNo;
    }
}
