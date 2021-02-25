package com.taomz.mini.apps.service.impl;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.model.login.TUser;
import com.taomz.mini.apps.model.login.TUserAuths;
import com.taomz.mini.apps.service.LoginService;
import com.taomz.mini.apps.service.UserAuthsService;
import com.taomz.mini.apps.service.UserService;
import com.taomz.mini.apps.service.dto.login.WxPhoneDTO;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.mini.apps.util.HttpsUtils;
import com.taomz.mini.apps.util.MD5Utils;
import com.taomz.mini.apps.util.enums.ErrorMessageEnum;
import com.taomz.sha.util.response.BaseResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author : wangling
 * @version V1.0
 * @title : LoginServiceImpl
 * @Package : com.taomz.mini.apps.service.impl
 * @Description: 登录
 * @date 2020/11/18 12:53
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserAuthsService userAuthsService;

    @Autowired
    private UserService userService;

    @Value("${wx.url}")
    private String url;

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @Override
    public BaseResponseModel loginByWx(String code) {
        log.info("微信登录参数:" + code);
        BaseResponseModel res = new BaseResponseModel();
        // 调用WX Api 获取用户的openId + sessionKey
        JSONObject jsonObject = getSessionKeyAndOpenId(code);
        log.info("调用微信登录认证返回：" + JSON.toJSONString(jsonObject));
        String wxErrode = jsonObject.getString("errcode");
        HashMap<String, String> map = new HashMap<>();
        String token = "";
        if (StringUtils.isEmpty(wxErrode)) {
            String openId = jsonObject.getString("openid");
            // 校验用户是否验证
            TUserAuths auths = userAuthsService.getTUserAuths(openId);
            log.info("openid:{}" + openId + "校验返回:{}" + JSON.toJSONString(auths));
            String verified = "";    //1-未校验 0-已校验
            String resOpenId = MD5Utils.encryption(openId);
            if (null != auths) {
                TUser tUser = userService.getById(auths.getUserId());
                token = MD5Utils.encryption(tUser.getPhone());
                // token保存redis
                String key = redisService.generateCacheKey(RedisRootNamespace.TOKEN, token);
                redisService.set(key, JSON.toJSONString(tUser), RedisRootNamespace.DEFAULT_ONE_DAY_TIMEOUT);
                verified = "0";    //1-未校验 0-已校验
            } else {
                //openid 存入redis
                String key = redisService.generateCacheKey(RedisRootNamespace.OPENID, resOpenId);
                redisService.set(key, openId);
                verified = "1";
            }
            map.put("verified", verified);
            map.put("openId", resOpenId);
            map.put("sessionKey", jsonObject.getString("session_key"));
        } else {
            return res.warpFailure().setDesc(jsonObject.getString("errmsg")).setCode(wxErrode);
        }
        return res.warpSuccess().setContent(map).setAccessToken(token);
    }

    /**
     * 解析用户手机号
     *
     * @param dto
     * @return
     */
    @Override
    public BaseResponseModel getPhoneNum(WxPhoneDTO dto) throws Exception {
        log.info("获取手机号参数：" + JSON.toJSONString(dto));
        BaseResponseModel res = new BaseResponseModel();
        String token = "";
        byte[] dataByte = Base64.decode(dto.getEncryptedData());      // 被加密的数据
        byte[] keyByte = Base64.decode(dto.getSessionKey());         // 加密秘钥
        byte[] ivByte = Base64.decode(dto.getIv());             // 偏移量
        try {
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);

            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                JSONObject obj = JSON.parseObject(result);
                log.info("解析后的手机号为：" + obj.getString("phoneNumber"));
                String key = redisService.generateCacheKey(RedisRootNamespace.OPENID, dto.getOpenId());
                Object openId = redisService.get(key);
                Assert.isTrue(null != openId, ErrorMessageEnum.OPENID_IS_NULL.getMessage());
                token = userService.getToken(obj.getString("phoneNumber"), openId.toString(), dto.getCmId(), dto.getMessage(), dto.getUserIdEncrpt());
            }
        } catch (Exception e) {
            throw new Exception("系统错误：", e);
        }
        return res.setAccessToken(token).warpSuccess();
    }

    /**
     * 调用微信API
     *
     * @param code
     * @return
     * @throws Exception
     */
    public JSONObject getSessionKeyAndOpenId(String code) {
        String param = "appid=" + appId + "&secret=" + appSecret + "&js_code=" + code
                + "&grant_type=authorization_code";
        String requestUrl = url + "?" + param;
        JSONObject jsonObject = HttpsUtils.doPost(requestUrl);
        return jsonObject;
    }

}
