package com.taomz.mini.apps.service.topic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.topic.TopicPlateDTO;
import com.taomz.mini.apps.model.topic.TTopic;
import com.taomz.sha.util.response.BaseResponseModel;

/**
 * @author zhaofei@taomz.com
 * @version V1.0
 * @title : TopicService
 * @Package : com.taomz.mini.apps.service.topic
 * @Description:
 * @date 2020/11/23 16:45
 **/
public interface TopicService extends IService<TTopic> {
	/**
	 * 好货推荐类型展示
	 * @return
	 */
	BaseResponseModel findList();

	/**
	 * 好货推荐列表
	 * @param topicPlateDTO
	 * @return
	 */
	BaseResponseModel findGoodWaresRecommendParticulars(TopicPlateDTO topicPlateDTO);
}
