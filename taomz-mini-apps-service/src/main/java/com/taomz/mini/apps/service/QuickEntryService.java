package com.taomz.mini.apps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.quickentry.QuickEntry;
import com.taomz.mini.apps.service.dto.QuickEntryQueryDTO;
import com.taomz.sha.util.response.BaseResponseModel;

/**
 * @author : wangling
 * @version V1.0
 * @title : QuickEntryService
 * @Package : com.taomz.mini.apps.service
 * @Description: 快捷入口
 * @date 2020/11/18 9:07
 **/
public interface QuickEntryService extends IService<QuickEntry> {

    BaseResponseModel homeList(QuickEntryQueryDTO QuickEntryQueryDTO);
}
