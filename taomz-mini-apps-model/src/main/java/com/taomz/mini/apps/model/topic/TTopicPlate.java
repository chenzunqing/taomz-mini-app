package com.taomz.mini.apps.model.topic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhaofei@taomz.com
 * @version V1.0
 * @title : TopicPlate
 * @Package : com.taomz.mini.apps.model.topic
 * @Description:
 * @date 2020/11/23 16:55
 **/
@Data
public class TTopicPlate implements Serializable {
	public static final Integer ONE = 1;
	public static final Integer TWO = 2;
	/**
	 * 专题板块主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 板块CODE
	 */
	private String plateCode;

	/**
	 * 板块名称
	 */
	private String plateName;
	/**
	 * 排版展示类型 1 通栏 2 分栏
	 */
	private Integer type;

	/**
	 * 首页展示数量
	 */
	private Integer number;

	/**
	 * 首页开关 1开 0关
	 */
	private Integer switchFlag;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 有效标识 1有效 0 无效
	 */
	private Integer deleteFlag;

	/**
	 * 专题板块类型（1-首页，2-好货推荐)
	 */
	private Integer topicPlateType;
}
