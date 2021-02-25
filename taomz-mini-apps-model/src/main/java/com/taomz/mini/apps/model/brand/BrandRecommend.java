package com.taomz.mini.apps.model.brand;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_brand_recommend")
public class BrandRecommend {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 类型
     */
    private String type;
    /**
     * 总数量
     */
    private Integer number;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 开关（0 - 开，1 - 关）
     */

    private Short isSwitch;
    /**
     * 创建时间
     */
    private Date createTime;

}