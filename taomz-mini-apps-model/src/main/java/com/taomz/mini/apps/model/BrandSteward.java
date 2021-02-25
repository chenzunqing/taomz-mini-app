package com.taomz.mini.apps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_brand_steward")
public class BrandSteward implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账号名称
     */
    private String accountName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 国家
     */
    private String country;
    /**
     * 国家编码
     */
    private String countryCode;
    /**
     * 国家线名称
     */
    private String nationalLine;
}
