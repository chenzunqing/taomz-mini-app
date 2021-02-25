package com.taomz.mini.apps.model.user;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 店铺信息
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_shop_info")
public class ShopInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 平台 code （天猫，淘宝，京东、等）
     */
    private String platformCode;

    /**
     * 第三方店铺ID
     */
    private String thirdShopId;

    /**
     * 第三方店铺名称
     */
    private String thirdShopName;

    /**
     * 第三方店铺链接
     */
    private String thirdShopUrl;

    /**
     * 月成交额
     */
    private BigDecimal monthlyTurnover;

    /**
     * 月销售截图
     */
    private String monthlySalesImages;

    /**
     * 主营类目（护肤/彩妆/洗护/香水）
     */
    private String mainCategory;

    /**
     * 主营品牌（综合/日韩/欧美/国货/东南亚/澳洲）
     */
    private String mainBrand;

    /**
     * 店铺类型（品牌旗舰店or专营\综合店\红人店\小众店\细分品类店）
     */
    private String shopType;

    /**
     * 客单价
     */
    private BigDecimal unitPrice;

    /**
     * 店铺经营特色
     */
    private String shopDescribe;

    /**
     * 物流方式（国内/保税仓/直邮）
     */
    private String logisticsMethods;

    /**
     * 品牌logo图片
     */
    private String logoImages;

    /**
     * 虚拟关注人数
     */
    private Integer followerNum;

    /**
     * 虚拟浏览量
     */
    private Integer pageView;

    /**
     * 虚拟咨询量
     */
    private Integer advisoryNum;

    /**
     * 实际浏览量
     */
    private Integer actualPageView;

    /**
     * 实际咨询量
     */
    private Integer actualAdvisoryNum;

    /**
     * 实际人数量
     */
    private Integer actualFollowerNum;

    /**
     * 店铺首字母
     */
    private String initials;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 店铺审核状态（0-初始化，1-审核中，2-已通过待付费，3-已通过已付费，4-已拒绝，5-已过期，6-暂存）
     */
    private Integer status;

    /**
     * 是否是主店铺（0,- 否，1- 是）
     */
    private Integer isMainShop;

    /**
     * 是否删除（0-未删除，1-已删除）
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 子账号Id
     */
    private Integer subUserId;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 货品情况
     */
    private String tradeAbout;

    /**
     * 客户经理userId
     */
    private String userNo;

    /**
     * 商家故事
     */
    private String bussStory;

    /**
     * 店铺类别(网店、实体店)
     */
    private String shopCategory;

    /**
     * 省份
     */
    private Integer province;

    /**
     * 市
     */
    private Integer city;

    /**
     * 区
     */
    private Integer district;

    /**
     * 店铺地址
     */
    private String address;

    /**
     * 店铺经营特色类别
     */
    private String shopDescribeType;

    /**
     * 0-未触发修改，1-触发修改
     */
    private Integer isUpdate;

    /**
     * 临时会员店铺（0-否，1-是）
     */
    private Integer isTemp;

    /**
     * 业务结构
     */
    private String businessType;

    /**
     * 收货地址
     */
    private String receiverAddress;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人手机号
     */
    private String receiverMobile;


}
