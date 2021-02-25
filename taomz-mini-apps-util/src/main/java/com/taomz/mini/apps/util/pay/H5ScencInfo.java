package com.taomz.mini.apps.util.pay;

import lombok.Getter;
import lombok.Setter;

/**
 * @ProjectName: yf-coc
 * @Package: com.ishop.tools.util.wechat
 * @ClassName: H5
 * @Author: guang
 * @Description: H5支付scene_info
 * @Date: 2019/11/8 11:58
 * @Version: 1.0
 */
@Getter
public class H5ScencInfo {

    private H5 h5_info;

    public void setH5_info(String type, String wap_url, String wap_name) {
        h5_info = new H5();
        h5_info.setType(type);
        h5_info.setWap_url(wap_url);
        h5_info.setWap_name(wap_name);
    }

    @Setter
    @Getter
    static class H5 {
        private String type;
        private String wap_url;
        private String wap_name;
    }
}
