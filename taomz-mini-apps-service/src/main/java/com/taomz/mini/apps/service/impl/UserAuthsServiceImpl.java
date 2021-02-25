package com.taomz.mini.apps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.TUserAuthsMapper;
import com.taomz.mini.apps.model.login.TUserAuths;
import com.taomz.mini.apps.service.UserAuthsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : wangling
 * @version V1.0
 * @title : UserAuthsServiceImpl
 * @Package : com.taomz.mini.apps.service.impl
 * @Description: 第三方认证
 * @date 2020/11/18 12:50
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserAuthsServiceImpl extends ServiceImpl<TUserAuthsMapper, TUserAuths> implements UserAuthsService {

    /**
     * 根据微信小程序openid查询用户认证信息
     *
     * @param wxLiteappOpenid
     * @return
     */
    @Override
    public TUserAuths getTUserAuths(String wxLiteappOpenid) {
        QueryWrapper<TUserAuths> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(TUserAuths.FIELD_WX_LITEAPP_OPENID, wxLiteappOpenid);
        TUserAuths tUserAuths = this.getOne(queryWrapper);
        return tUserAuths;
    }

    @Override
    public void insertTUser(Integer userId, String openId) {
        TUserAuths tUserAuths = getTUserAuths(openId);
        if (null != tUserAuths) {

        } else {
            TUserAuths auths = new TUserAuths();
            auths.setWxLiteappOpenid(openId);
            auths.setUserId(userId);
            auths.setIdentifyType("miniApp");
            this.save(auths);
        }
    }
}
