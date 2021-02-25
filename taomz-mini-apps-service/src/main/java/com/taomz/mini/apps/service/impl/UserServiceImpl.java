package com.taomz.mini.apps.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.TUserMapper;
import com.taomz.mini.apps.model.login.TUser;
import com.taomz.mini.apps.model.user.UserCategory;
import com.taomz.mini.apps.service.UserAuthsService;
import com.taomz.mini.apps.service.UserService;
import com.taomz.mini.apps.service.campaign.TShareFissionRecordService;
import com.taomz.mini.apps.service.campaign.UserHelpService;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.mini.apps.service.user.UserCategoryService;
import com.taomz.mini.apps.util.AESUtil;
import com.taomz.mini.apps.util.DateUtil;
import com.taomz.mini.apps.util.MD5Utils;
import com.taomz.mini.apps.util.StringUtil;
import com.taomz.mini.apps.util.enums.ShareFissionSuccEnum;
import com.taomz.sha.util.response.BaseCodeResultForTaomz;
import com.taomz.sha.util.response.BaseResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author : wangling
 * @version V1.0
 * @title : UserServiceImpl
 * @Package : com.taomz.mini.apps.service.impl
 * @Description: 用户
 * @date 2020/11/18 12:50
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements UserService {

    @Value("${user.userId.key}")
    private String decrypKey;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserCategoryService userCategoryService;

    @Autowired
    private UserAuthsService userAuthsService;

    @Autowired
    private TShareFissionRecordService shareFissionRecordService;

    @Autowired
    private UserHelpService userHelpService;

    public TUser getUser(String phone) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(TUser.FIELD_PHONE, phone);
        TUser user = this.getOne(queryWrapper);
        return user;
    }

    public TUser saveUser(String phone, String openId) {
        int number = (int) ((Math.random() * 9 + 1) * 10000000);
        TUser user = new TUser();
        user.setPhone(phone);
        user.setPassWord(MD5Utils.encode(String.valueOf(number)));
        user.setRegisterChannel("miniApp");
        user.setCreateTime(new Date());
        user.setMemberCode(queryMemberCode());
        user.setNickName(StringUtil.replace(phone, "****", 3, 7));
        this.save(user);
        return user;
    }

    @Override
    public String getToken(String phone, String openId, Integer cmId, String message, String encrpt) {
        Integer isNewUser = 0;
        Integer pUserId = null;
        Integer shareUserId = null;
        TUser newUser = getUser(phone);
        if (Objects.isNull(newUser)) {
            newUser = saveUser(phone, openId);
            if (StrUtil.isNotBlank(encrpt)) {
                pUserId = Integer.valueOf(SecureUtil.aes(
                        AESUtil.generateAESKey(decrypKey, CharsetUtil.UTF_8))
                        .decryptStr(encrpt, CharsetUtil.CHARSET_UTF_8)
                );
            }
            if (StrUtil.isNotBlank(message)) {
                shareUserId = Integer.valueOf(SecureUtil.aes(
                        AESUtil.generateAESKey(decrypKey, CharsetUtil.UTF_8))
                        .decryptStr(message, CharsetUtil.CHARSET_UTF_8)
                );
            }
            if (Objects.nonNull(cmId)) {
                log.info("分享裂变活动ID：{}", cmId);
                isNewUser = 1;
                // 裂变分享
                if (Objects.nonNull(pUserId)) {
                    log.info("分享裂变用户ID：{}", pUserId);
                    shareFissionRecordService.save(cmId, pUserId, ShareFissionSuccEnum.REGISTER_SUCCESS, newUser.getId(), 8, isNewUser);
                }
                userHelpService.userSurplusHelp(cmId, newUser.getId(), shareUserId, isNewUser);
            }
        }
        String token = MD5Utils.encryption(newUser.getPhone());
        // token保存redis
        userAuthsService.insertTUser(newUser.getId(), openId);
        String key = redisService.generateCacheKey(RedisRootNamespace.TOKEN, token);
        redisService.set(key, JSON.toJSONString(newUser), RedisRootNamespace.DEFAULT_ONE_DAY_TIMEOUT);
        return token;
    }

    @Override
    public BaseResponseModel getUserInfo(Integer userId) {
        BaseResponseModel res = new BaseResponseModel();
        Assert.isTrue(null != userId, BaseCodeResultForTaomz.LOGIN_FAIL.getMessage());
        TUser user = this.getById(userId);
        LambdaQueryWrapper<UserCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserCategory::getUserId,userId);
        List<UserCategory> list = userCategoryService.list(queryWrapper);
        HashMap map = new HashMap();
        map.put("user",user);
        map.put("userCategory",list);
        return res.setContent(map).warpSuccess();
    }

    // 获取用户编码
    public String queryMemberCode() {
        // （年份后两位）+02（月）+ 6位随机数
        String memberCode = "M" + DateUtil.getYear(new Date()).toString().substring(2, 4)
                + String.format("%02d", DateUtil.getThisMonth()) + (int) ((Math.random() * 9 + 1) * 100000);
        // 判断缓存是否存在
        Object value = redisService.get(redisService.generateCacheKey(RedisRootNamespace.MEMBERCODE, memberCode));
        if (value != null) {
            return queryMemberCode();
        } else {
            redisService.set(redisService.generateCacheKey(RedisRootNamespace.MEMBERCODE, memberCode), memberCode);
            return memberCode;
        }
    }

}
