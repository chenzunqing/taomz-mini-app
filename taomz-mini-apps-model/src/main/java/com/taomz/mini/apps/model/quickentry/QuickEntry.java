package com.taomz.mini.apps.model.quickentry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : wangling
 * @version V1.0
 * @title : QuickEntry
 * @Package : com.taomz.mini.apps.model.quickentry
 * @Description: 快捷入口
 * @date 2020/11/18 8:57
 **/
@Data
public class QuickEntry implements Serializable {

    public static final String FIELD_TYPE = "type";
    public static final String FIELD_SHELF = "shelf";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_START_TIME = "start_time";
    public static final String FIELD_END_TIME = "end_time";
    public static final String FIELD_SORT = "sort";
    public static final String FIELD_UPDATE_TIME = "update_time";
    public static final String FIELD_CREATE_TIME = "create_time";

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 快捷入口名称
     */
    private String name;

    /**
     * 快捷入口图标连接地址
     */
    private String iconUrl;

    /**
     * 生效开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 生效结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 0-有效 1-失效
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 上下架 0-上架 1-下架
     */
    private Integer shelf;
    /**
     * 页面跳转方式 1原生 0 H5
     */
    private Integer jumpType;

    /**
     * 跳转链接
     */
    private String jumpUrl;

    /**
     * 跳转栏目类型  见LinkTypeEnum
     */
    private Integer jumpColumnValue;

    /**
     * 快捷入口类型 0-商学院 1-好货页
     */
    private Integer type;

    /**
     * 跳转参数
     */
    private String jumpParam;
}
