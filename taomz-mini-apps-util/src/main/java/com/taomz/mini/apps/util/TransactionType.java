package com.taomz.mini.apps.util;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : TransactionType
 * @Package : com.ishop.tools.util.wechat
 * @Description: 交易类型
 * @date 2020/11/19 10:02
 **/
public interface TransactionType {

    /**
     * 获取交易类型
     * @return {@link String}
     */
    String getType();

    /**
     * 获取接口
     * @return {@link String}
     */
    String getMethod();
}
