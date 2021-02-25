package com.taomz.mini.apps.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author : wangling
 * @version V1.0
 * @title : StringUtil
 * @Package : com.taomz.mini.apps.util
 * @Description:
 * @date 2020/11/18 10:23
 **/

public class StringUtil {

    public static final String NUMBERCHAR = "0123456789";

    public static String replace(String str, String content, int startIndex, int endIndex){
        return str.substring(0, startIndex) + content + str.substring(endIndex);
    }

    public static String getUrlByParam(String jumpUrl, String jumpParam) {
        if (StringUtils.isEmpty(jumpUrl))
            return jumpParam;
        if (StringUtils.isEmpty(jumpParam))
            return jumpUrl;
        return jumpUrl + "?" + jumpParam;
    }

    /**
     * 生成订单号
     *
     * @return
     */
    public static String generateOrderNo(String type) {
        String temp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Random random = new Random();
        String num = generateRandomNum(5);
        return type + temp + num;
    }

    public static String generateRandomNum(int count) {
        Random random = new Random();
        StringBuffer flag = new StringBuffer();
        for (int i = 0; i < count; i++) {
            flag.append(NUMBERCHAR.charAt(random.nextInt(9)));
        }
        return flag.toString();
    }
}
