package com.taomz.mini.apps.dto.goods;

import com.taomz.mini.apps.param.PageParam;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 商品或者产品参数类
 * @Author: liaoxiaoli
 * @CreateDate: 2018/6/19 17:10
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/6/19 17:10
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Getter
@Setter
public class SpuQueryDTO extends PageParam {

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 最低毛利率（%）
     */
    private BigDecimal minGrossProfit;

    /**
     * 最大毛利率（%）
     */
    private BigDecimal maxGrossProfit;

    /**
     * 贸易方式（一般贸易，跨境贸易，国货）
     */
    private List<String> tradeTypeItem;

    /**
     * 国籍
     */
    private List<String> countryCodeItem;

    /**
     * 授权类型
     */
    private List<String> authChannelItem;

    /**
     * 类目
     */
    private List<String> categoryItems;

    /**
     * 删除标识 0：无效 1：有效
     */
    private Integer deleteFlag;
}
