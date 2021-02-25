package com.taomz.mini.apps.model.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("m_activity_address")
public class ActivityAddress {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 地址所属省
     */
    private String province;

    /**
     * 地址所属市
     */
    private String city;

    /**
     * 地址所属区县
     */
    private String country;

    /**
     * 详细地址
     */
    private String address;
}
