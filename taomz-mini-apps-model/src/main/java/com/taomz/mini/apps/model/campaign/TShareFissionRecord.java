package com.taomz.mini.apps.model.campaign;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 分享裂变记录
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("t_share_fission_record")
public class TShareFissionRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动ID
     */
    private Integer cmId;

    /**
     * 分享父用户ID
     */
    private Integer pUserId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 等级ID
     */
    private Integer gradeId;

    /**
     * 途径
     */
    private String enterChannel;

    /**
     * 是否是新用户 1是 0否
     */
    private Integer isNewUser;

    /**
     * 创建时间
     */
    private Date createTime;


}
