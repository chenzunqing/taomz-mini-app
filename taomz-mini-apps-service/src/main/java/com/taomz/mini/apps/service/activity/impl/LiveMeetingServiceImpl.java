package com.taomz.mini.apps.service.activity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.activity.LiveMeetingMapper;
import com.taomz.mini.apps.model.activity.LiveMeeting;
import com.taomz.mini.apps.service.activity.LiveMeetingService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 大会直播表 服务实现类
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-20
 */
@Service
public class LiveMeetingServiceImpl extends ServiceImpl<LiveMeetingMapper, LiveMeeting> implements LiveMeetingService {

}
