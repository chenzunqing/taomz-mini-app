package com.taomz.mini.apps.web.controller;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.service.LoginService;
import com.taomz.mini.apps.service.UserService;
import com.taomz.mini.apps.service.dto.login.WxLoginDTO;
import com.taomz.mini.apps.service.dto.login.WxPhoneDTO;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.mini.apps.util.AESUtil;
import com.taomz.mini.apps.web.controller.base.BaseController;
import com.taomz.mini.apps.web.util.PropertyFilterFactory;
import com.taomz.sha.util.response.BaseResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : wangling
 * @version V1.0
 * @title : LoginController
 * @Package : com.taomz.mini.apps.web.controller
 * @Description: 小程序登录
 * @date 2020/11/18 12:21
 **/
@RestController
public class LoginController extends BaseController {

    @Value("${user.userId.key}")
    private String decrypKey;

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    /**
     * 微信小程序 授权认证
     *
     * @param wxLoginDTO
     * @return
     */
    @PostMapping("/wx/login")
    public String login(@Valid @RequestBody WxLoginDTO wxLoginDTO) {
        return JSON.toJSONString(loginService.loginByWx(wxLoginDTO.getCode()));
    }

    /**
     * 解析微信小程序的手机号
     *
     * @param wxPhoneDTO
     * @return
     */
    @PostMapping("/wx/get_wx_phone_num")
    public String getWxPhoneNum(@Valid @RequestBody WxPhoneDTO wxPhoneDTO) throws Exception {
        return JSON.toJSONString(loginService.getPhoneNum(wxPhoneDTO));
    }

    @PostMapping("/wx/user_info")
    public String getUserInfo() {
        BaseResponseModel res = userService.getUserInfo(Integer.valueOf(getRequest().getAttribute("userId").toString()));
        String[] ignoreProps = new String[]{"apple", "birthday", "deviceToken", "deviceType", "maxLoginNumber", "qq", "registerChannel", "saleName", "toKen", "weiXin", "logoutTime", "createTime", "loginTime", "passWord"};
        return JSON.toJSONString(res, PropertyFilterFactory.create(ignoreProps), DEFAULT_FEATURES);
    }

    @PostMapping("/wx/logout")
    public String logout(@RequestHeader("login_token") String loginToken) {
        BaseResponseModel res = new BaseResponseModel();
        try {
            String key = redisService.generateCacheKey(RedisRootNamespace.TOKEN, loginToken);
            redisService.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(res.warpSuccess());
    }

    /**
     * 获取用户加密Id
     *
     * @return
     */
    @PostMapping("/wx/help/get_user_id")
    public String getEncrptUserId() {
        return JSON.toJSONString(
                new BaseResponseModel()
                        .warpSuccess()
                        .setContent(
                                SecureUtil.aes(AESUtil.generateAESKey(decrypKey, CharsetUtil.UTF_8))
                                        .encryptHex(getCurrentUserId().toString(), CharsetUtil.UTF_8)),
                DEFAULT_FEATURES);
    }
}
