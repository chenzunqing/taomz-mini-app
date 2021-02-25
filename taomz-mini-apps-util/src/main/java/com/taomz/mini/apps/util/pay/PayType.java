package com.taomz.mini.apps.util.pay;

import com.taomz.mini.apps.util.TransactionType;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : PayType
 * @Package : com.taomz.mini.apps.util.pay
 * @Description: 支付类型
 * @date 2020/12/7 16:38
 **/
public enum PayType {

    aliPay {

        @Override
        public TransactionType getTransactionType(String transactionType) {
            return AliPayEnum.valueOf(transactionType);
        }
    },
    wxPay {

        @Override
        public TransactionType getTransactionType(String transactionType) {
            return WxPayEnum.valueOf(transactionType);
        }
    };

    /**
     * 根据支付类型获取交易类型
     * @param transactionType 类型值
     * @return  交易类型
     */
    public abstract TransactionType getTransactionType(String transactionType);
}
