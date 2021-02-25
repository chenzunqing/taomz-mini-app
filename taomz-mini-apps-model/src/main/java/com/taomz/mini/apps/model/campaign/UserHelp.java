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
 * 用户助力值表
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("user_help")
public class UserHelp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动ID
     */
    private Integer actProdId;

    /**
     * 助力值
     */
    private Long helpNum;

    /**
     * 已使用助力值
     */
    private Long useredHelpNum;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 分享父用户ID
     */
    private Integer pUserId;

    /**
     * 是否是新用户 1是 0否
     */
    private Integer isNewUser;

    /**
     * 登录时间
     */
    private Date loginTime;


}
