package com.taomz.mini.apps.service.pay.ali.impl;

import com.taomz.mini.apps.service.pay.ali.YFAliPayService;
import com.taomz.mini.apps.service.pay.impl.AliPayServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : YFAliPayServiceImpl
 * @Package : com.taomz.mini.apps.service.pay.ali.impl
 * @Description: 仪菲支付宝支付服务
 * @date 2020/12/4 9:57
 **/
@Service(value = "yfAliPayService")
public class YFAliPayServiceImpl extends AliPayServiceImpl implements YFAliPayService {

    @Value("${environment}")
    private String environment;

    @Value("${ali.yf.appId}")
    private String appId;

    @Value("${ali.yf.privateKey}")
    private String privateKey;

    @Value("${ali.yf.publicKey}")
    private String publicKey;

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public String getPrivateKey() {
        return privateKey;
    }

    @Override
    public String getPublicKey() {
        return publicKey;
    }

    @Override
    public Boolean isTest() {
        return "test".equals(environment) ? Boolean.TRUE : Boolean.FALSE;
    }
}
