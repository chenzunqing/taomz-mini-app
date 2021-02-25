package com.taomz.mini.apps.service.topic.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.topic.TopicMapper;
import com.taomz.mini.apps.dto.topic.TopicPlateDTO;
import com.taomz.mini.apps.model.topic.TTopic;
import com.taomz.mini.apps.model.topic.TTopicPlate;
import com.taomz.mini.apps.service.topic.TopicPlateService;
import com.taomz.mini.apps.service.topic.TopicService;

import com.taomz.mini.apps.util.reslut.PageResult;
import com.taomz.sha.util.response.BaseResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhaofei@taomz.com
 * @version V1.0
 * @title : TopicServiceImpl
 * @Package : com.taomz.mini.apps.service.topic.impl
 * @Description:
 * @date 2020/11/23 16:48
 **/
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, TTopic> implements TopicService {
	@Autowired
	private TopicPlateService topicPlateService;
	@Autowired
	private TopicMapper topicMapper;
	/**
	 * 好货推荐类型展示
	 *
	 * @return
	 */
	@Override
	public BaseResponseModel findList() {
		List<TTopicPlate> topicPlates = topicPlateService.list(Wrappers.<TTopicPlate>lambdaQuery()
				.eq(TTopicPlate::getDeleteFlag, TTopicPlate.ONE)
				.eq(TTopicPlate::getSwitchFlag, TTopicPlate.ONE)
				.eq(TTopicPlate::getTopicPlateType, TTopicPlate.TWO)
				.orderByAsc(TTopicPlate::getSort));
		return new BaseResponseModel().warpSuccess().setContent(topicPlates);
	}

	/**
	 * 好货推荐列表
	 * @param topicPlateDTO
	 * @return
	 */
	@Override
	public BaseResponseModel findGoodWaresRecommendParticulars(TopicPlateDTO topicPlateDTO) {
		TTopicPlate tTopicPlate = topicPlateService.getOne(Wrappers.<TTopicPlate>lambdaQuery()
				.eq(TTopicPlate::getPlateCode, topicPlateDTO.getPlateCode())
				.eq(TTopicPlate::getDeleteFlag, TTopicPlate.ONE)
				.eq(TTopicPlate::getSwitchFlag, TTopicPlate.ONE)
				.eq(TTopicPlate::getTopicPlateType, TTopicPlate.TWO)
				.orderByAsc(TTopicPlate::getSort),false);
		Page<TTopic> page = new Page<>(topicPlateDTO.getPageNum(), topicPlateDTO.getPageSize());
		List<TTopic> tTopics =new ArrayList<>();
		if (null != tTopicPlate){
			List<TTopic> list = topicMapper.findList(tTopicPlate.getId(),page);
			tTopics.addAll(list);
		}
		return new BaseResponseModel().setContent(new PageResult( page.getSize(),page.getTotal()).setData(tTopics)).warpSuccess();
	}

}