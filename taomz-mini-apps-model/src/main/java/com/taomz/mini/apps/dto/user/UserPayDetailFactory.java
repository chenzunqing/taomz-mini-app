package com.taomz.mini.apps.dto.user;

import com.taomz.mini.apps.model.user.UserPayDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : UserPayDetailFactory
 * @Package : com.taomz.mini.apps.dto.user
 * @Description: 品牌会员支付明细
 * @date 2020/11/23 16:49
 **/
public class UserPayDetailFactory implements Serializable {

    private static final long serialVersionUID = 1L;

    public static UserPayDetail factory(Integer id, Date startTime, Date endTime, String orderCode, Short orderType,
                                        Short payType, Short status, Integer userId, BigDecimal amount, String remark,
                                        String title, Integer businessId, Integer isNewAgreement) {
        UserPayDetail detail = new UserPayDetail();
        detail.setCreateTime(new Date());
        detail.setOrderCode(orderCode);
        detail.setOrderType(orderType);
        detail.setUserId(userId);
        detail.setStatus(status);
        detail.setAmount(amount);
        detail.setPayType(payType);
        detail.setStartTime(startTime);
        detail.setEndTime(endTime);
        detail.setId(id);
        detail.setRemark(remark);
        detail.setTitle(title);
        detail.setBusinessId(businessId);
        detail.setIsNewAgreement(isNewAgreement.shortValue());
        return detail;
    }
}
