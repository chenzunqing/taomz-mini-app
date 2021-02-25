package com.taomz.mini.apps.service.redis;

public interface RedisRootNamespace {
    String CACHE_ROOT = "taomz:live:protal";
    String CACHE = "commerce";
    String NAMESPACE_SEPARATOR = ":";
    String NO_REPEAT = "noRepeat";
    String YF_COC_LOGIN_CACHE = "commerce:user:login:token";
    String DICT_MAP_CACHE = "commerce:dict:dictmap";
    String LIVE_JOIN_EXPLAINATION = RedisRootNamespace.CACHE_ROOT + RedisRootNamespace.NAMESPACE_SEPARATOR + "live:join:explaination";
    String LIVE_PV = RedisRootNamespace.CACHE_ROOT + RedisRootNamespace.NAMESPACE_SEPARATOR + "live:pv";
    String LIVE_UV = RedisRootNamespace.CACHE_ROOT + RedisRootNamespace.NAMESPACE_SEPARATOR + "live:uv";
    String LIVE_THUMB_Up = RedisRootNamespace.CACHE_ROOT + RedisRootNamespace.NAMESPACE_SEPARATOR + "live:thumb:up";

    /**
     * redis默认超时小时，单位 秒
     */
    long DEFAULT_ONE_DAY_TIMEOUT = 24 * 3600;
    /**
     * 商品默认缓存30天
     */
    int GOODS_DEFAULT_TIMEOUT = 30;


    /**
     * 直播大会
     */
    String LIVE_MEETING = RedisRootNamespace.CACHE_ROOT + RedisRootNamespace.NAMESPACE_SEPARATOR + "wx:live:meeting";
    /**
     * 商品缓存公共开始
     */
    String GOODS_CACHE = "commerce" + RedisRootNamespace.NAMESPACE_SEPARATOR + "goods";

    /**
     * 活动缓存公共开始
     */
    String ACTIVITY_CACHE = "commerce" + RedisRootNamespace.NAMESPACE_SEPARATOR + "activity";

    /**
     * 广告缓存公共开始
     */
    String ADVERTISING_INFO = "commerce" + RedisRootNamespace.NAMESPACE_SEPARATOR + "advertising";

    /**
     * 活动地址
     */
    String ACTIVITY_ADDRESS = RedisRootNamespace.ACTIVITY_CACHE + RedisRootNamespace.NAMESPACE_SEPARATOR + "address";

    /**
     * 活动招商配置信息
     */
    String ACTIVITY_INVESTMENT_CONFIG_INFO = RedisRootNamespace.ACTIVITY_CACHE + RedisRootNamespace.NAMESPACE_SEPARATOR + "investment:config";

    /**
     * 活动会员报名配置信息
     */
    String ACTIVITY_MEMBER_CONFIG_INFO = RedisRootNamespace.ACTIVITY_CACHE + RedisRootNamespace.NAMESPACE_SEPARATOR + "member:config";

    /**
     * 系统配置缓存
     */
    String SYS_CONFIGURE = RedisRootNamespace.CACHE_ROOT + RedisRootNamespace.NAMESPACE_SEPARATOR + "sys_configure";
    /**
     * 广告位广告信息
     */
    String ADVERTISING_SIT_INFO = RedisRootNamespace.ADVERTISING_INFO + RedisRootNamespace.NAMESPACE_SEPARATOR + "info";


    /**
     * 商品基本信息
     */
    String GOODS_BASIC_CACHE = RedisRootNamespace.GOODS_CACHE + RedisRootNamespace.NAMESPACE_SEPARATOR + "basicinfo";

    /**
     * 商品规格列表信息
     */
    String GOODS_SKU_CACHE = RedisRootNamespace.GOODS_CACHE + RedisRootNamespace.NAMESPACE_SEPARATOR + "sku";

    /**
     * sku库存
     */
    String GOODS_SKU_STOCK_CACHE = RedisRootNamespace.GOODS_SKU_CACHE + RedisRootNamespace.NAMESPACE_SEPARATOR + "stock";

    /**
     * sku销量
     */
    String GOODS_SKU_SELL_CACHE = RedisRootNamespace.GOODS_SKU_CACHE + RedisRootNamespace.NAMESPACE_SEPARATOR + "sell";

    /**
     * 数据字典
     */
    String DICT_CACHE = RedisRootNamespace.CACHE + RedisRootNamespace.NAMESPACE_SEPARATOR + "dict";

    String TOKEN = "commerce:user:login:token";
    String OPENID = "commerce:user:miniapp:wxopenid";
    String MEMBERCODE = "commerce:user:code";

    /**
     * 缓存1小时，单位 秒
     */
    long ONE_HOURS_TIMEOUT = 60 * 60;
}
