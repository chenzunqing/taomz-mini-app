package com.taomz.mini.apps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.login.TUser;
import com.taomz.sha.util.response.BaseResponseModel;

/**
 * @author : wangling
 * @version V1.0
 * @title : UserService
 * @Package : com.taomz.mini.apps.service
 * @Description: 用户
 * @date 2020/11/18 12:48
 **/
public interface UserService extends IService<TUser> {


    String getToken(String phone,String openId, Integer cmId, String message, String encrpt);

    BaseResponseModel getUserInfo(Integer userId);
}
