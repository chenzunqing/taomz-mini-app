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
 * 用户助力值记录表
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("user_help_record")
public class UserHelpRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户助力ID
     */
    private Integer helpId;

    /**
     * 助力值
     */
    private Long helpNum;

    /**
     * 类型(0-获取/1-支出)
     */
    private Integer type;

    /**
     * 助力值说明
     */
    private String instruction;

    /**
     * 操作用户ID
     */
    private Integer operateUserId;

    /**
     * 创建时间
     */
    private Date createTime;


}
