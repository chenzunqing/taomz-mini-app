package com.taomz.mini.apps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.QuickEntryMapper;
import com.taomz.mini.apps.model.quickentry.QuickEntry;
import com.taomz.mini.apps.service.QuickEntryService;
import com.taomz.mini.apps.service.dto.QuickEntryQueryDTO;
import com.taomz.mini.apps.util.StringUtil;
import com.taomz.sha.util.response.BaseResponseModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author : wangling
 * @version V1.0
 * @title : QuickEntryServiceImpl
 * @Package : com.taomz.mini.apps.service.impl
 * @Description: 快捷入口
 * @date 2020/11/18 9:08
 **/
@Service
public class QuickEntryServiceImpl extends ServiceImpl<QuickEntryMapper, QuickEntry> implements QuickEntryService {
    @Override
    public BaseResponseModel homeList(QuickEntryQueryDTO dto) {
        BaseResponseModel response = new BaseResponseModel();
        QueryWrapper<QuickEntry> queryWrapper = new QueryWrapper<>();
        Date date = new Date();
        queryWrapper.eq(QuickEntry.FIELD_TYPE, dto.getType()).eq(QuickEntry.FIELD_SHELF, 0)
                .eq(QuickEntry.FIELD_STATUS, 0).le(QuickEntry.FIELD_START_TIME, date)
                .ge(QuickEntry.FIELD_END_TIME, date).orderByAsc(QuickEntry.FIELD_SORT)
                .orderByDesc(QuickEntry.FIELD_UPDATE_TIME).orderByDesc(QuickEntry.FIELD_CREATE_TIME).last("limit 0,5");
        List<QuickEntry> list = this.list(queryWrapper);
        list.forEach(quick -> {
            quick.setJumpUrl(StringUtil.getUrlByParam(quick.getJumpUrl(), quick.getJumpParam()));
        });
        return response.warpSuccess().setContent(list);
    }
}
