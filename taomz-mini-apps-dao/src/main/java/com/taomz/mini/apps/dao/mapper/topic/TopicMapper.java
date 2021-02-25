package com.taomz.mini.apps.dao.mapper.topic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taomz.mini.apps.model.topic.TTopic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaofei@taomz.com
 * @version V1.0
 * @title : TopicServiceMapper
 * @Package : com.taomz.mini.apps.dao.mapper.topic
 * @Description:
 * @date 2020/11/23 16:50
 **/
public interface TopicMapper extends BaseMapper<TTopic> {
	List<TTopic> findList(@Param(value = "id") Integer id,@Param("page") Page<TTopic> page);
}
