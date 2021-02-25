package com.taomz.mini.apps.model.activity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 大会直播表
 * </p>
 *
 * @author liaobing
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("live_meeting")
public class LiveMeeting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 直播名称
     */
    private String name;

    /**
     * 房间号ID
     */
    private String roomId;

    /**
     * 推流地址
     */
    private String pushUrl;
    /**
     * 拉流地址
     */
    private String pullUrl;

    /**
     * 开播时间
     */
    private Date showTime;

    /**
     * 开播时长
     */
    private Integer showDuration;

    /**
     * 访问用户数量
     */
    private Integer accessUserNumber;

    /**
     * 点赞数量
     */
    private Integer thumbUpNumber;

    /**
     * 0-直播中,1-已结束
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 封面地址
     */
    private String coverUrl;

    /**
     * 是否全员禁言
     */
    private Short isForbidden;


}
