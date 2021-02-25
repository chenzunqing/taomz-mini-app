package com.taomz.mini.apps.util.constant;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : LiveConstant
 * @Package : com.taomz.live.portal.util.constant
 * @Description: 直播常量
 * @date 2020/7/21 11:48
 **/
public class LiveConstant {

    /**
     * 直播推流地址有效期
     */
    public final static int TENCENT_PUSH_VAILD_DAY = 7;

    /**
     * 直播播放地址有效期
     */
    public final static int TENCENT_PLAY_VAILD_DAY = 1;

    public final static String TENCENT_REALM_NAME = "live.tencentcloudapi.com";

    /**
     * 推流地址
     */
    public static final String PUSH_URL = "rtmp://";

    public static final String PUSH_DOMIAN_NAME = ".livepush.myqcloud.com";

    public static final String LIVE_DOMIAN_NAME = ".livecdn.liveplay.myqcloud.com";

    /**
     * 正常
     */
    public static final int LIVE_NORMAL = 0;

    /**
     * 中断
     */
    public static final int LIVE_BREAK = 1;

    /**
     * 直播中断时间(minute)
     */
    public static int BREAK_DIFF = 15;

    /**
     * 直播未开播可等待时间(minute)
     */
    public static int RESERVED_DIFF = 60;
}
