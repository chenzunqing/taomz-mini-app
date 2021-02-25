package com.taomz.mini.apps.service.comm.impl;

import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.dao.mapper.dict.DictDataMapper;
import com.taomz.mini.apps.dto.DictDataDTO;
import com.taomz.mini.apps.service.comm.DictService;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.sha.util.response.BaseResponseModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taomz.mini.apps.service.redis.RedisService;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title: DictServiceImpl
 * @Package: com.taomz.live.portal.service.impl
 * @Description: 字段查询Service
 * @date 2020/7/17 16:43
 **/
@Slf4j
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public String dictNameByCode(String dictCode) {
        if (StringUtils.isEmpty(dictCode)) {
            log.info("dictNameByCode param is null");
            return null;
        }
        String dictMapKey = redisService.generateCacheKey(RedisRootNamespace.DICT_MAP_CACHE);
        String dictName = redisService.hmGetString(dictMapKey, dictCode);
        return dictName;
    }

    @Override
    public BaseResponseModel listDictByType(String dictType) {
        BaseResponseModel response = new BaseResponseModel();
        String dictTypeKey = redisService.generateCacheKey(RedisRootNamespace.DICT_CACHE, dictType);
        List<DictDataDTO> targetList = JSON.parseArray((String) redisService.get(dictTypeKey),DictDataDTO.class);
        if (CollectionUtils.isEmpty(targetList)) {
            List<DictDataDTO> allList = dictDataMapper.listDictByType(dictType);
            getRecursiveDict(allList, targetList);
            return response.warpSuccess().setContent(allList);
        }
        return response.warpSuccess().setContent(targetList);
    }
    @Override
    public void getRecursiveDict(List<DictDataDTO> allData, List<DictDataDTO> targetList) {
        if (CollectionUtils.isNotEmpty(allData)) {
            allData.forEach(s -> {
                if (null != s.getHasChild() && s.getHasChild() != 0) {
                    List<DictDataDTO> childList = dictDataMapper.listDictByPid(s.getDictId());
                    List<DictDataDTO> dataList = new ArrayList<>();
                    getRecursiveDict(childList, dataList);
                    s.setChildList(dataList);
                }
                targetList.add(s);
            });
        }
    }
}
