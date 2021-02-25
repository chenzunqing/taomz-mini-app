package com.taomz.mini.apps.service.order;

import com.taomz.mini.apps.dto.user.PayMemberDetailDTO;
import com.taomz.mini.apps.param.OrderItemParam;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.pay.PayOrder;
import com.taomz.sha.util.response.BaseResponseModel;

import java.text.ParseException;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : MemberOrderService
 * @Package : com.taomz.mini.apps.service.order
 * @Description: 会员订单接口
 * @date 2020/11/23 15:30
 **/
public interface MemberOrderService {

    /**
     * 创建会员订单
     * @param currentUserId
     * @param orderItemParam
     * @return
     * @throws ExceptionWrapper
     */
    BaseResponseModel createMemberOrder(Integer currentUserId,
                                        OrderItemParam orderItemParam) throws ExceptionWrapper, ParseException;

    /**
     * 根据用户Id 判断新老协议创建订单逻辑
     *
     * @param userId
     * @return
     */
    Integer getIsNewAgreement(Integer userId);

    /**
     * 统一下单
     * @param payOrder
     * @param userId
     * @return {@link BaseResponseModel}
     */
    BaseResponseModel toPay(PayOrder payOrder, Integer userId) throws ExceptionWrapper;

    /**
     * 会员支付前续费页面
     * @param shopSpuId
     * @param shopSkuId
     * @param currentUserId
     * @return {@link PayMemberDetailDTO}
     * @throws ExceptionWrapper
     */
    BaseResponseModel payMemberDetail(Long shopSpuId, Long shopSkuId, int currentUserId) throws ExceptionWrapper;
}
