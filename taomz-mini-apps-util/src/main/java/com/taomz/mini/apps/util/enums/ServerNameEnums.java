package com.taomz.mini.apps.util.enums;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title: ServerNameEnums
 * @Package: com.taomz.sha.util
 * @Description: 服务
 * @date 2020/7/11 14:41
 **/
public enum ServerNameEnums {

    LIVE_MGT("http://TAOMZ-LIVE-MGT", "视频直播运营端服务"),
    LIVE_PORT("http://TAOMZ-LIVE-PORT", "视频直播用户端服务"),
    COMMON_CERTIFY("http://TAOMZ-COMMON-CERTIFY", "用户认证通用服务");

    private String serverName;
    private String message;

    public String getServerName() {
        return serverName;
    }
    public String getMessage() {
        return message;
    }

    ServerNameEnums(String serverName, String message) {
        this.serverName = serverName;
        this.message = message;
    }
}
