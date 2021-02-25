package com.taomz.mini.apps.service.dto.login;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : wangling
 * @version V1.0
 * @title : WxPhoneDTO
 * @Package : com.taomz.mini.apps.service.dto.login
 * @Description: 获取微信手机号接口参数
 * @date 2020/11/18 15:10
 **/
@Data
public class WxPhoneDTO {

    @NotBlank(message = "参数：encryptedData 不能为空")
    private String encryptedData;
    @NotBlank(message = "参数：iv 不能为空")
    private String iv;
    @NotBlank(message = "参数：sessionKey 不能为空")
    private String sessionKey;
    @NotBlank(message = "参数：openId 不能为空")
    private String openId;

    /**
     * 活动ID
     */
    private Integer cmId;

    private String message;

    /**
     * 加密后用户ID
     */
    private String userIdEncrpt;

}
