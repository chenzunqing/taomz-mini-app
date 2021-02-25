package com.taomz.mini.apps.service;

import com.taomz.mini.apps.service.dto.login.WxPhoneDTO;
import com.taomz.sha.util.response.BaseResponseModel;

/**
 * @author : wangling
 * @version V1.0
 * @title : LoginService
 * @Package : com.taomz.mini.apps.service
 * @Description:
 * @date 2020/11/18 12:53
 **/
public interface LoginService {

    BaseResponseModel loginByWx(String code);

    BaseResponseModel getPhoneNum(WxPhoneDTO wxPhoneDTO) throws Exception;
}
