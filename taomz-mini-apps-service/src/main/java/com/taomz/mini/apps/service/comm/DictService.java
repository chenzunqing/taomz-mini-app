package com.taomz.mini.apps.service.comm;

import com.taomz.mini.apps.dto.DictDataDTO;
import com.taomz.sha.util.response.BaseResponseModel;

import java.util.List;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title: DictService
 * @Package: com.taomz.live.portal.service
 * @Description: 字典查询
 * @date 2020/7/17 16:42
 **/
public interface DictService {

    /**
     * 根据字典CODE 查询名称
     *
     * @param dictCode
     * @return
     */
    String dictNameByCode(String dictCode);
    /**
     * 查询指定类型的信息列表
     *
     * @param dictType
     * @return
     */
    BaseResponseModel listDictByType(String dictType);
    /**
     * 递归组装
     *
     * @param allData
     * @param targetList
     */
    void getRecursiveDict(List<DictDataDTO> allData, List<DictDataDTO> targetList);
}
