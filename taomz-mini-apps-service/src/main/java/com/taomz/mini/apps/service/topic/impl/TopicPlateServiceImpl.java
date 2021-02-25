package com.taomz.mini.apps.service.topic.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.topic.TopicPlateMapper;
import com.taomz.mini.apps.model.topic.TTopicPlate;
import com.taomz.mini.apps.service.topic.TopicPlateService;
import org.springframework.stereotype.Service;

/**
 * @author zhaofei@taomz.com
 * @version V1.0
 * @title : TopicPlateImpl
 * @Package : com.taomz.mini.apps.service.topic.impl
 * @Description:
 * @date 2020/11/23 17:07
 **/
@Service
public class TopicPlateServiceImpl extends ServiceImpl<TopicPlateMapper, TTopicPlate> implements TopicPlateService {
}
