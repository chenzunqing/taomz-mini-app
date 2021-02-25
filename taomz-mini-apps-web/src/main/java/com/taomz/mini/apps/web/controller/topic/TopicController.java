package com.taomz.mini.apps.web.controller.topic;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.dto.topic.TopicPlateDTO;
import com.taomz.mini.apps.service.topic.TopicService;
import com.taomz.mini.apps.web.controller.base.BaseController;
import com.taomz.mini.apps.web.util.PropertyFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhaofei@taomz.com
 * @version V1.0
 * @title : TopicController
 * @Package : com.taomz.mini.apps.web.controller.topic
 * @Description:
 * @date 2020/11/23 16:32
 **/
@RestController
public class TopicController extends BaseController {
	@Autowired
	private TopicService topicService;

	/**
	 * 好货推荐类型展示
	 *
	 * @return
	 */
	@PostMapping("/wx/good/wares/recommend/find_list")
	public String findList() {
		String[] ignoreProps = new String[]{"topicPlateType","deleteFlag","createTime","type","number","switchFlag"};
		return JSON.toJSONString(topicService.findList(), PropertyFilterFactory.create(ignoreProps),DEFAULT_FEATURES);
	}

	/**
	 * 好货推荐列表
	 * @param topicPlateDTO
	 * @return
	 */
	@PostMapping("/wx/good/wares/recommend")
	public String findGoodWaresRecommendParticulars(@Valid @RequestBody TopicPlateDTO topicPlateDTO) {
		String[] ignoreProps = new String[]{"linkUrl","createTime","publishStatus","adminNo","deleteFlag","isShare","shareTitle","shareSubtitle","shareImage"};
		return JSON.toJSONString(topicService.findGoodWaresRecommendParticulars(topicPlateDTO), PropertyFilterFactory.create(ignoreProps),DEFAULT_FEATURES);
	}
}
