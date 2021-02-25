package com.taomz.mini.apps.dao.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.dto.MemberOfflinePaymentDetailDTO;
import com.taomz.mini.apps.model.user.MemberOfflinePayment;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员线下支付审核表 Mapper 接口
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-25
 */
public interface MemberOfflinePaymentMapper extends BaseMapper<MemberOfflinePayment> {

    /**
     * 查询会员线下缴费明细
     * @param orderCode
     * @return
     */
    MemberOfflinePaymentDetailDTO queryBaMOPDetail(@Param("orderCode") String orderCode);
}
