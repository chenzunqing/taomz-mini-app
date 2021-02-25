package com.taomz.mini.apps.model.brand;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订货政策明细表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_brand_order_policy_detail")
public class BrandOrderPolicyDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订货政策Id
     */
    private Integer policyId;

    /**
     * 业务ID
     */
    private Integer businessId;

    /**
     * 业务名称
     */
    private String businessName;


}
