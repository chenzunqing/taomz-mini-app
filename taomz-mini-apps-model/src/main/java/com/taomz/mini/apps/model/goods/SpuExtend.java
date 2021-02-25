package com.taomz.mini.apps.model.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品扩展信息
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_spu_extend")
public class SpuExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品规格
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品ID
     */
    private Integer spuId;

    /**
     * 图文详情
     */
    private String detailDesc;

    /**
     * 产品参数 不同品类产品参数不同存 json串
     */
    private String spuParam;

    /**
     * 分销政策
     */
    private String distributionPolicy;

    /**
     * 备注
     */
    private String remark;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 新增时间
     */
    private Date createTime;


}
