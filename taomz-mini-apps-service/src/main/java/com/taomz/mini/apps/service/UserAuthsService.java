package com.taomz.mini.apps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.login.TUserAuths;

/**
 * @author : wangling
 * @version V1.0
 * @title : UserAuthsService
 * @Package : com.taomz.mini.apps.service
 * @Description: 用户第三方认证
 * @date 2020/11/18 12:49
 **/
public interface UserAuthsService extends IService<TUserAuths> {

    TUserAuths getTUserAuths(String wxLiteappOpenid);

    void insertTUser(Integer userId,String openId);
}
