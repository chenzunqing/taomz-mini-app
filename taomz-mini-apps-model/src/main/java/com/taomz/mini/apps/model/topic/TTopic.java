package com.taomz.mini.apps.model.topic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhaofei@taomz.com
 * @version V1.0
 * @title : Topic
 * @Package : com.taomz.mini.apps.model.topic
 * @Description:
 * @date 2020/11/23 16:34
 **/
@Data
public class TTopic implements Serializable {
	/**
	 * 专题主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 专题标题
	 */
	private String topicName;

	/**
	 * 板块ID
	 */
	private Integer plateId;

	/**
	 * 主图
	 */
	private String mainImage;

	/**
	 * 专题类型 1自定义 2定制化
	 */
	private Integer type;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 上下架标识 1上架 0下架
	 */
	private Integer publishStatus;

	/**
	 * 发布人编号
	 */
	private String adminNo;

	/**
	 * 图文详情内容
	 */
	private String content;

	/**
	 * 有效标识 1有效 0 无效
	 */
	private Integer deleteFlag;

	/**
	 * 打开分享
	 */
	private Short isShare;

	/**
	 * 分享标题
	 */
	private String shareTitle;

	/**
	 * 分享副标题
	 */
	private String shareSubtitle;

	/**
	 * 分享图片
	 */
	private String shareImage;

	/**
	 * 跳转类型 0 H5   1原生
	 */
	private Integer jumpType;
	/**
	 * 跳转栏目值
	 */
	private Integer jumpColumnValue;
	/**
	 * 跳转地址
	 */
	private String jumpUrl;
	/**
	 * 跳转参数
	 */
	private String jumpParam;
}
