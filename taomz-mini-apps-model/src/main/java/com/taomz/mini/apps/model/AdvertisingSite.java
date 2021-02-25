package com.taomz.mini.apps.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 广告位信息表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_advertising_site")
public class AdvertisingSite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 广告位ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告位编码  详细见字典表
     */
    private String adsCode;

    /**
     * 标题
     */
    private String adsTitle;

    /**
     * 子标题 如更多
     */
    private String subTitle;

    /**
     * 是否显示标题  1显示  0不显示
     */
    private Integer titleViewFlag;

    /**
     * 类型 (0:图片,1:文字,2:flash)
     */
    private Integer adsType;

    /**
     * 最大显示数量
     */
    private Integer viewMaxNum;

    /**
     * 长度 (高度)
     */
    private Integer height;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 描述
     */
    private String adsDesc;

    /**
     * 是否有效 1有效 0无效
     */
    private Integer deleteFlag;


}
