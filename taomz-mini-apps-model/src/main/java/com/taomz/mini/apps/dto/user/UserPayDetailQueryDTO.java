package com.taomz.mini.apps.dto.user;

import com.taomz.mini.apps.param.PageParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : UserPayDetailQueryDTO
 * @Package : com.taomz.mini.apps.dto.user
 * @Description: 用户缴费明细查询条件DTO
 * @date 2020/11/25 10:47
 **/
@Setter
@Getter
public class UserPayDetailQueryDTO extends PageParam {

    /**
     * 订单类型
     */
    private Short orderType;

    /**
     * 用户ID
     */
    private Integer userId;
}
