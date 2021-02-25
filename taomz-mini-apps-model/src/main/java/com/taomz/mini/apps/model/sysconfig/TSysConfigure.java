package com.taomz.mini.apps.model.sysconfig;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统配置表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sys_configure")
public class TSysConfigure implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图片服务地址
     */
    private String imageService;

    /**
     * 发票开具天数(订单完成后多少天开具：0立即)
     */
    private Integer invoiceOpenDate;

    /**
     * 开票员
     */
    private String clerk;

    /**
     * 会员续费（0-半年，1-一年）
     */
    private Integer memberFee;

    /**
     * 品牌子账号人数设置
     */
    private Integer subBrand;

    /**
     * 会员子账号人数设置
     */
    private Integer subMember;

    /**
     * 邀请码链接
     */
    private String inviteUrl;

    /**
     * 限制im用户联系品牌次数
     */
    private Integer memImCount;

    /**
     * 版块热门搜索
     */
    private String bsHotSearch;


}
