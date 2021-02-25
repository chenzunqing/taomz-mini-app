package com.taomz.mini.apps.service.order.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.taomz.mini.apps.dao.mapper.OrderItemMapper;
import com.taomz.mini.apps.dao.mapper.OrderMapper;
import com.taomz.mini.apps.dao.mapper.TUserAuthsMapper;
import com.taomz.mini.apps.dao.mapper.TUserMapper;
import com.taomz.mini.apps.dao.mapper.user.UserPayDetailMapper;
import com.taomz.mini.apps.dto.goods.SkuDTO;
import com.taomz.mini.apps.dto.goods.SpuDTO;
import com.taomz.mini.apps.dto.user.PayMemberDetailDTO;
import com.taomz.mini.apps.dto.user.UserPayDetailFactory;
import com.taomz.mini.apps.model.Order;
import com.taomz.mini.apps.model.OrderItem;
import com.taomz.mini.apps.model.login.TUser;
import com.taomz.mini.apps.model.login.TUserAuths;
import com.taomz.mini.apps.model.user.UserCategory;
import com.taomz.mini.apps.model.user.UserPayDetail;
import com.taomz.mini.apps.param.OrderItemParam;
import com.taomz.mini.apps.param.goods.GoodsProviderParam;
import com.taomz.mini.apps.service.goods.GoodsInfoProvider;
import com.taomz.mini.apps.service.order.MemberOrderService;
import com.taomz.mini.apps.service.order.OrderTraceService;
import com.taomz.mini.apps.service.pay.wx.TMZWxPayService;
import com.taomz.mini.apps.service.user.ShopInfoService;
import com.taomz.mini.apps.service.user.UserCategoryService;
import com.taomz.mini.apps.util.DateUtil;
import com.taomz.mini.apps.util.StringUtil;
import com.taomz.mini.apps.util.enums.CategoryTypeEnum;
import com.taomz.mini.apps.util.enums.OrderStatusEnum;
import com.taomz.mini.apps.util.enums.OrderTraceEnum;
import com.taomz.mini.apps.util.enums.OrderTypeEnum;
import com.taomz.mini.apps.util.enums.PayStatusEnum;
import com.taomz.mini.apps.util.enums.PayTypeEnum;
import com.taomz.mini.apps.util.enums.UserCategoryEnum;
import com.taomz.mini.apps.util.enums.UserPayDetailEnum;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.pay.PayOrder;
import com.taomz.mini.apps.util.pay.WxPayEnum;
import com.taomz.mini.apps.util.pay.WxPayUtil;
import com.taomz.sha.util.response.BaseResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : MemberOrderServiceImpl
 * @Package : com.taomz.mini.apps.service.order.impl
 * @Description: 会员订单服务
 * @date 2020/11/23 15:30
 **/
@Service
@Slf4j
public class MemberOrderServiceImpl implements MemberOrderService {

    @Value("${wx.pay.notifyUrl}")
    private String notifyUrl;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderTraceService orderTraceService;

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private UserPayDetailMapper userPayDetailMapper;

    @Autowired
    private TUserAuthsMapper userAuthsMapper;

    @Autowired
    private UserCategoryService userCategoryService;

    @Autowired
    private ShopInfoService shopInfoService;

    @Autowired
    private GoodsInfoProvider goodsInfoProvider;

    @Autowired
    @Qualifier(value = "tmzWxPayService")
    private TMZWxPayService tmzWxPayService;

    @Override
    public BaseResponseModel createMemberOrder(Integer currentUserId, OrderItemParam orderItemParam) throws ExceptionWrapper, ParseException {
        // 判断是否存在会员线下续费未结束订单
        Order order  = orderMapper.selectOne(
                Wrappers.lambdaQuery(Order.class)
                        .eq(Order::getPayType, PayTypeEnum.OFFLINE.getCode())
                        .eq(Order::getPayStatus, PayStatusEnum.WAIT.getCode())
                        .eq(Order::getOrderType, OrderTypeEnum.MEMBER.getCode())
                        .eq(Order::getOrderStatus, OrderStatusEnum.NEW.getCode())
                        .eq(Order::getUserId, currentUserId));
        if (order != null) {
            return new BaseResponseModel().warpSuccess().setContent(order.getOrderCode());
        }
        Date startTime = DateUtil.startOneDay(new Date());
        Date endTime = DateUtil.endOneDay(new Date());
        UserCategory category = userCategoryService.findByCategoryType(currentUserId,
                CategoryTypeEnum.商会会员.getStringValue(), null, null);
        Assert.isTrue(Objects.nonNull(category), "请去合作入驻页提交会员入驻申请！");
        int status = category.getStatus().intValue();

        if (status != UserCategoryEnum.审核通过已付费.getFlag() && status != UserCategoryEnum.审核通过待付费.getFlag()
                && status != UserCategoryEnum.已过期.getFlag()) {
            throw new ExceptionWrapper("会员状态不合法");
        }
        // 1 、取消今天以前未支付的会员续费订单
        orderMapper.modifyOrderStatus(
                currentUserId,
                OrderTypeEnum.MEMBER.getCode(),
                startTime,
                OrderStatusEnum.NEW.getCode(),
                OrderStatusEnum.CANCEL.getCode());

        // 2 、查询当前用户今天是否存在会员,未缴费订单
        List<String> orderCodeItems = orderMapper.queryUserNoPaidOrderCode(
                currentUserId,
                startTime,
                endTime,
                OrderTypeEnum.MEMBER.getCode(),
                OrderStatusEnum.NEW.getCode());

        if (CollectionUtil.isNotEmpty(orderCodeItems)) {
            String orderCode = orderCodeItems.get(0);
            // 存在订单,返回当天订单编号
            return new BaseResponseModel().warpSuccess().setContent(orderCode);
        }

        orderItemParam.setNum(1);
        orderItemParam.setDiscountAmount(BigDecimal.ZERO);
        if (category.getStatus().intValue() == UserCategoryEnum.审核通过已付费.getFlag()) {
            startTime = DateUtil.daysAfter(DateUtil.startOneDay(category.getExpiryTime()), 1);
            endTime = DateUtil.yearsAfter(DateUtil.endOneDay(category.getExpiryTime()), 1);
        } else {
            startTime = DateUtil.startOneDay(DateUtil.now());
            endTime = DateUtil.yearsAfter(DateUtil.endOneDay(DateUtil.now()), 1);
        }

        // 5、创建新订单
        order = createOrder(currentUserId, OrderTypeEnum.MEMBER.getCode(), orderItemParam);
        String title = "会员缴费" + orderItemParam.getNum() + "年";
        // 6、封装实体类
        UserPayDetail payDetail = UserPayDetailFactory.factory(null, startTime, endTime, order.getOrderCode(),
                OrderTypeEnum.MEMBER.getCode(), null, UserPayDetailEnum.NEW.getCode(), currentUserId,
                order.getTotalFee().add(order.getDiscountAmount()), null, title,
                shopInfoService.mainShopId(currentUserId, null), getIsNewAgreement(currentUserId));
        // 7、保存明细
        userPayDetailMapper.insert(payDetail);
        return new BaseResponseModel().warpSuccess().setContent(order.getOrderCode());
    }

    /**
     * 新老协议
     *
     * @param userId
     * @return
     */
    @Override
    public Integer getIsNewAgreement(Integer userId) {
        TUser user = userMapper.selectOne(
                Wrappers.lambdaQuery(TUser.class)
                        .eq(TUser::getId, userId)
                        .select(TUser::getId, TUser::getCodeIdentityLetter));
        // 判断是否是会员
        if (StringUtils.defaultString(user.getCodeIdentityLetter()).contains(CategoryTypeEnum.商会会员.getLetter())) {
            List<Integer> statusItems = Arrays.asList(UserCategoryEnum.审核通过已付费.getFlag(),
                    UserCategoryEnum.线下续费待确认.getFlag(), UserCategoryEnum.线下续费已拒绝.getFlag());
            UserCategory userCategory = userCategoryService.findByCategoryType(
                    userId,
                    CategoryTypeEnum.商会会员.getStringValue(),
                    null,
                    statusItems);
            if (userCategory != null) {
                if (DateUtil.getDate("2020-07-01 00:00:00", DateUtil.DATATIMEF_STR).getTime() >= System
                        .currentTimeMillis()) {
                    if (userCategory.getLastInitiateTime().getTime() < DateUtil
                            .getDate("2020-05-01 00:00:00", DateUtil.DATATIMEF_STR).getTime()) {
                        int count = userPayDetailMapper.selectCount(
                                Wrappers.lambdaQuery(UserPayDetail.class)
                                        .eq(UserPayDetail::getUserId, userId)
                                        .eq(UserPayDetail::getStatus, 1)
                                        .ge(UserPayDetail::getCreateTime, "2020-05-01 00:00:00"));
                        if (count == 0) {
                            return 0;
                        }
                    }
                }
            }
        } else {
            List<Integer> statusItems = Arrays.asList(UserCategoryEnum.已过期.getFlag());
            UserCategory userCategory = userCategoryService.findByCategoryType(
                    userId,
                    CategoryTypeEnum.商会会员.getStringValue(),
                    null,
                    statusItems);
            if (userCategory != null && DateUtil.getDate("2020-07-01 00:00:00", DateUtil.DATATIMEF_STR)
                    .getTime() >= System.currentTimeMillis()) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public BaseResponseModel toPay(PayOrder payOrder, Integer userId) throws ExceptionWrapper {
        Order order = orderMapper.selectOne(
                Wrappers.lambdaQuery(Order.class)
                        .eq(Order::getOrderCode, payOrder.getOutTradeNo())
                        .eq(Order::getUserId, userId));
        Assert.isTrue(Objects.nonNull(order), "订单不存在！");
        // 判断订单状态
        Assert.isTrue(order.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()), "订单支付状态错误！");
        List<TUserAuths> userAuths = userAuthsMapper.selectList(
                Wrappers.lambdaQuery(TUserAuths.class)
                        .eq(TUserAuths::getUserId, userId)
                        .eq(TUserAuths::getIdentifyType, "miniApp"));
        Assert.isTrue(CollectionUtil.isNotEmpty(userAuths), "获取用户OpenId失败！");
        // 附加参数
        Map<String, Object> passbackParams = MapUtil.newHashMap();
        passbackParams.put("orderType", order.getOrderType());
        passbackParams.put("orderCode", order.getOrderCode());
        payOrder.setAddition(WxPayUtil.coverMap2String(passbackParams));
        // 订单号
        if (StrUtil.isNotBlank(order.getPayCode())) {
            payOrder.setOutTradeNo(order.getPayCode());
        }
        // 支付信息组转
        payOrder.setSubject("会员缴费");
        payOrder.setPrice(order.getTotalFee());
        payOrder.setType(WxPayEnum.MINI);
        payOrder.setExpirationTime(order.getOverTime());
        payOrder.setOpenid(userAuths.get(0).getWxLiteappOpenid());
        payOrder.setNotifyUrl(notifyUrl);
        // 获取支付结果
        JSONObject response = tmzWxPayService.toPay(payOrder);
        order.setPayCode(response.getString("orderCode"));
        orderMapper.updateById(order);
        return new BaseResponseModel().warpSuccess().setContent(response);
    }

    @Override
    public BaseResponseModel payMemberDetail(Long shopSpuId, Long shopSkuId, int currentUserId) throws ExceptionWrapper {
        Integer mainShopId = shopInfoService.mainShopId(currentUserId, null);
        if (null == mainShopId || mainShopId == 0) {
            throw new ExceptionWrapper("主店铺不存在");
        }
        PayMemberDetailDTO detailDTO = new PayMemberDetailDTO();
        // 计算当前用户是否是第一次缴费
        UserCategory category = userCategoryService.findByCategoryType(currentUserId, CategoryTypeEnum.商会会员.getStringValue(), null, null);
        int number = 1;
        if (category.getStatus().intValue() == UserCategoryEnum.审核通过已付费.getFlag()) {
            detailDTO.setEndTime(DateUtil.yearsAfter(DateUtil.endOneDay(category.getExpiryTime()), 1));
            detailDTO.setStartTime(DateUtil.daysAfter(DateUtil.startOneDay(category.getExpiryTime()), 1));
        } else {
            detailDTO.setStartTime(DateUtil.startOneDay(DateUtil.now()));
            detailDTO.setEndTime(DateUtil.yearsAfter(DateUtil.endOneDay(DateUtil.now()), 1));
        }
        // 优惠金额
        detailDTO.setDiscountAmount(BigDecimal.ZERO);
        detailDTO.setPaymentUnit("年缴");

        SpuDTO spuDTO = goodsInfoProvider.getGoodsSpuInfoBySpuId(shopSpuId, false);
        // 验证商品信息
        SkuDTO skuDTO = spuDTO.getSkuList().get(0);
        if (skuDTO.getSkuId().compareTo(shopSkuId) == 0) {
            detailDTO.setAmount(skuDTO.getSkuPrice().multiply(new BigDecimal(number)));
        }
        return new BaseResponseModel().warpSuccess().setContent(detailDTO);
    }

    private Order createOrder(Integer userId, Short type, OrderItemParam orderItemDTO) throws ExceptionWrapper, ParseException {
        // 生成订单号 (1:生成订单)
        String orderCode = StringUtil.generateOrderNo(String.valueOf(type));
        // 校验商品信息
        GoodsProviderParam goodsProviderParam = new GoodsProviderParam();
        SpuDTO spuDTO = goodsInfoProvider.getGoodsSpuInfoBySpuId(orderItemDTO.getSpuId(), false);
        List<SkuDTO> skuDTOList = spuDTO.getSkuList();
        SkuDTO skuDTO = null;
        for (SkuDTO s : skuDTOList) {
            // 验证商品信息
            if (s.getSkuId().equals(orderItemDTO.getSkuId())) {
                skuDTO = s;
                break;
            }
        }
        if (Objects.isNull(skuDTO)) {
            throw new ExceptionWrapper("未找到匹配的SKU");
        }
        // 品牌编码
        OrderItem orderItem = new OrderItem().setBrandId(spuDTO.getBrandId()).setOrderCode(orderCode)
                .setSkuName(skuDTO.getSkuName()).setActPrice(skuDTO.getSkuPrice()).setPrice(skuDTO.getSkuPrice())
                .setNum(orderItemDTO.getNum()).setSkuId(orderItemDTO.getSkuId()).setSpuId(orderItemDTO.getSpuId());
        // 商品存在，计算总价
        BigDecimal orderAmount = skuDTO.getSkuPrice().multiply(new BigDecimal(orderItem.getNum()))
                .subtract(orderItemDTO.getDiscountAmount());
        // 扣库存
        goodsProviderParam.setChangeNum(orderItem.getNum());
        goodsProviderParam.setSpuId(orderItem.getSpuId());
        goodsProviderParam.setSkuId(orderItem.getSkuId());
        List<GoodsProviderParam> skuList = Lists.newArrayList(goodsProviderParam);
        goodsInfoProvider.stockChange(skuList, 1);
        // 创建订单
        Order order =
                Order.builder()
                        .orderCode(orderCode)
                        .totalFee(orderAmount)
                        .discountAmount(orderItemDTO.getDiscountAmount())
                        .invoiceAmount(orderAmount)
                        .userId(userId)
                        .orderType(type)
                        .createTime(DateUtil.now()).build();
        if (OrderTypeEnum.MEMBER.getCode().equals(type)) {
            order.setOverTime(new SimpleDateFormat(DateUtil.DATATIMEF_STR_SEC)
                    .parse(DateUtil.dateToDateString(DateUtil.now(), DateUtil.DATAFORMAT_STR4) + "235959"));
        } else {
            order.setOverTime(new SimpleDateFormat(DateUtil.DATATIMEF_STR_SEC)
                    .parse(DateUtil.dateToDateString(DateUtil.daysAfter(DateUtil.now(), 15), DateUtil.DATAFORMAT_STR4)
                            + "235959"));
        }
        try {
            orderMapper.insert(order);
            orderItemMapper.insert(orderItem);
            // 保存订单轨迹
            orderTraceService.saveTrace(Integer.toString(userId), orderCode, OrderTraceEnum.CREATE.getCode(), null);
            return order;
        } catch (Exception e) {
            log.error("保存订单失败：{}", JSON.toJSONString(orderItemDTO), e);
            throw new ExceptionWrapper("保存订单信息失败");
        }
    }
}
