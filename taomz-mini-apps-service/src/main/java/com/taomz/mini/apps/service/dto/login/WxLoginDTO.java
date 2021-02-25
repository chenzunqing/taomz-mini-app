package com.taomz.mini.apps.service.dto.login;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : wangling
 * @version V1.0
 * @title : WxLoginDTO
 * @Package : com.taomz.mini.apps.service.dto.login
 * @Description: 微信小程序授权登录
 * @date 2020/11/18 14:38
 **/
@Data
public class WxLoginDTO {

    @NotBlank(message = "登录时获取的code不能为空！")
    private String code;

    /**
     * 活动ID
     */
    private Integer cmId;

    /**
     * 加密后用户ID
     */
    private String userIdEncrpt;
}
