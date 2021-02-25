package com.taomz.mini.apps.model.brand;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 品牌基本信息
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_brand_basic_info")
public class BrandBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 入驻来源（数据字典）
     */
    private String enterSource;

    /**
     * 品牌池Id
     */
    private Integer brandPoolId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 英文名称
     */
    private String brandNameEn;

    /**
     * 贸易种类（一般贸易，跨境贸易，国货）（数据字典）
     */
    private String tradeTypes;

    /**
     * 品牌国籍
     */
    private String countryCode;

    /**
     * 产地
     */
    private String productionAddress;

    /**
     * 天猫国际海外旗舰店（0 - 没有，1 - 有）
     */
    private Integer tmallOverseasShopFlag;

    /**
     * 天猫旗舰店（0 - 没有，1 - 有）
     */
    private Integer tmallShopFlag;

    /**
     * 主营类目（存JSON串）
     */
    private String mainCategory;

    /**
     * 授权渠道
     */
    private String authChannel;

    /**
     * 渠道类型（品牌方/代理商）
     */
    private String channelType;

    /**
     * 渠道公司名称
     */
    private String channelCompanyName;

    /**
     * 品牌授权开始时间
     */
    private Date brandAuthStartTime;

    /**
     * 品牌授权过期时间
     */
    private Date brandAuthExpireTime;

    /**
     * 全球品牌市场销售概况-线上
     */
    private BigDecimal salesAmountOnLine;

    /**
     * 全球品牌市场销售概况-线下
     */
    private BigDecimal salesAmountUnderLine;

    /**
     * 品牌介绍
     */
    private String brandIdea;

    /**
     * 品牌Logo
     */
    private String brandLogo;

    /**
     * 状态（0 - 已过期， 1- 正常，2 - 已作废）
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 品牌名字首字母
     */
    private String initials;

    /**
     * 品牌入驻费用
     */
    private BigDecimal initiationAmount;

    /**
     * 产品图（多图）
     */
    private String productImages;

    /**
     * 明星单品（多图）
     */
    private String terminalImages;

    /**
     * 产品价格最小值
     */
    private BigDecimal intervalMin;

    /**
     * 产品价格最大值
     */
    private BigDecimal intervalMax;

    /**
     * 虚拟浏览量
     */
    private Integer pageView;

    /**
     * 虚拟关注人数量
     */
    private Integer followerNum;

    /**
     * 虚拟咨询量
     */
    private Integer advisoryNum;

    /**
     * 实际浏览量
     */
    private Integer actualPageView;

    /**
     * 关注人数量
     */
    private Integer actualFollowerNum;

    /**
     * 实际咨询量
     */
    private Integer actualAdvisoryNum;

    /**
     * 删除标识（0-未删除，1-已删除）
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户Id
     */
    private Integer createUserId;

    /**
     * 提交申请入驻时间
     */
    private Date submitTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 品牌方备注
     */
    private String remark1;

    /**
     * 运营备注
     */
    private String remark2;

    /**
     * 子账号Id
     */
    private Integer subUserId;

    /**
     * 最低毛利润
     */
    private BigDecimal minimumProfitRatio;

    /**
     * 最高利润比
     */
    private BigDecimal highestProfitRatio;

    /**
     * 突出亮点
     */
    private String highlight;

    /**
     * 是否控价说明（0-否，1-是）
     */
    private Integer isPriceControl;

    /**
     * 控价说明
     */
    private String priceControlExplain;

    /**
     * 0-未触发修改，1-触发修改
     */
    private Integer isUpdate;

    /**
     * 是否样品：1是，0否
     */
    private Boolean isSample;

    /**
     * 生产许可证
     */
    private String productionLicense;

    /**
     * 过期时间
     */
    private Date expiryTime;

    /**
     * 搜索浏览数量
     */
    private Integer searchNum;


}
