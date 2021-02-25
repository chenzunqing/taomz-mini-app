package com.taomz.mini.apps.service.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.user.MemberOfflinePaymentMapper;
import com.taomz.mini.apps.dao.mapper.user.UserPayDetailMapper;
import com.taomz.mini.apps.dto.MemberOfflinePaymentDetailDTO;
import com.taomz.mini.apps.dto.user.UserPayDetailDTO;
import com.taomz.mini.apps.dto.user.UserPayDetailQueryDTO;
import com.taomz.mini.apps.model.user.UserPayDetail;
import com.taomz.mini.apps.service.order.OrderService;
import com.taomz.mini.apps.service.user.UserPayDetailService;
import com.taomz.mini.apps.util.enums.MemberOfflinePayStatusEnum;
import com.taomz.mini.apps.util.enums.OrderTypeEnum;
import com.taomz.mini.apps.util.reslut.PageResult;
import com.taomz.sha.util.response.BaseResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : UserPayDetailServiceImpl
 * @Package : com.taomz.mini.apps.service.user.impl
 * @Description: 用户缴费明细服务
 * @date 2020/11/25 10:50
 **/
@Service
public class UserPayDetailServiceImpl extends ServiceImpl<UserPayDetailMapper, UserPayDetail> implements UserPayDetailService {

    @Autowired
    private MemberOfflinePaymentMapper memberOfflinePaymentMapper;

    @Autowired
    private OrderService orderService;

    @Override
    public BaseResponseModel findPage(UserPayDetailQueryDTO param) {
        Page<UserPayDetailDTO> page = new Page<>(param.getPageNum(), param.getPageSize());
        Page<UserPayDetailDTO> brandInfoDTOPage = getBaseMapper().findPage(page, param);
        brandInfoDTOPage.getRecords().forEach(detailDTO -> {
            if (OrderTypeEnum.MEMBER.getCode().equals(detailDTO.getType()) || OrderTypeEnum.BRAND.getCode().equals(detailDTO.getType())) {
                JSONObject jsonObject = orderService.switchFlag(detailDTO.getOrderCode(), detailDTO.getOrderStatus(), detailDTO.getPaymentTime(), detailDTO.getAmount());
                if (jsonObject != null) {
                    detailDTO.setAllowInvoice(jsonObject.getShort("allowInvoice"));
                    detailDTO.setInvoiceStatus(jsonObject.getShort("invoiceStatus"));
                }
            } else {
                detailDTO.setAllowInvoice((short) 0);
            }

            //判断状态
            MemberOfflinePaymentDetailDTO paymentDetailDTO = memberOfflinePaymentMapper.queryBaMOPDetail(detailDTO.getOrderCode());
            // 线下转账流程状态码
            // 状态码： 0、线下支付待审核 1、已支付 2、已失效 3、线下支付已拒绝、4、待支付
            if (null != paymentDetailDTO) {
                if (paymentDetailDTO.getConfirmStatus() == MemberOfflinePayStatusEnum.待审核.flag
                        || paymentDetailDTO.getConfirmStatus() == MemberOfflinePayStatusEnum.业务审核通过.flag) {
                    detailDTO.setStatus("0");
                }
                if (paymentDetailDTO.getConfirmStatus() == MemberOfflinePayStatusEnum.财务审核通过.flag) {
                    detailDTO.setStatus("1");
                }
                if (paymentDetailDTO.getConfirmStatus() == MemberOfflinePayStatusEnum.已取消.flag) {
                    detailDTO.setStatus("2");
                    detailDTO.setRemark(paymentDetailDTO.getRefusalReason());
                }
                if (paymentDetailDTO.getConfirmStatus() == MemberOfflinePayStatusEnum.拒绝.flag) {
                    detailDTO.setStatus("3");
                    detailDTO.setRemark(paymentDetailDTO.getRefusalReason());
                }
            } else {
                // 非线下转账流程状态码
                detailDTO.setStatus("1");
            }
        });
        return new BaseResponseModel().warpSuccess()
                .setContent(new PageResult(page.getSize(), page.getTotal()).setData(page.getRecords()));
    }
}
