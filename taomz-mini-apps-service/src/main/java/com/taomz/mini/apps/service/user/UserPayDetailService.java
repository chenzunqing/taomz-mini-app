package com.taomz.mini.apps.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.user.UserPayDetailQueryDTO;
import com.taomz.mini.apps.model.user.UserPayDetail;
import com.taomz.sha.util.response.BaseResponseModel;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : UserPayDetailService
 * @Package : com.taomz.mini.apps.service.user
 * @Description: 用户缴费明细接口
 * @date 2020/11/25 10:49
 **/
public interface UserPayDetailService extends IService<UserPayDetail> {

    /**
     * 分页查询
     * @param param 查询参数 {@link UserPayDetailQueryDTO}
     * @return {@link BaseResponseModel}
     */
    BaseResponseModel findPage(UserPayDetailQueryDTO param);
}
