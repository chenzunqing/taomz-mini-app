package com.taomz.mini.apps.service.goods;

import com.taomz.mini.apps.dto.goods.SpuQueryDTO;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.sha.util.response.BaseResponseModel;

/**
 * @Description: 商品接口
 * @Author: liaoxiaoli
 * @CreateDate: 2018/6/22 11:23
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/6/22 11:23
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface GoodsService {

    /**
     * 根据商品ID查询商品基本信息 （不包含商品扩展，即只有主表基本数据）
     *
     * @param spuId      商品ID
     * @param deleteFlag
     * @return
     * @throws ExceptionWrapper
     */
    com.taomz.mini.apps.dto.goods.SpuDTO getSpuInfoBySpuId(Long spuId, Integer deleteFlag) throws ExceptionWrapper;

    /**
     * 明星单品
     * @param param
     * @return
     */
    BaseResponseModel qryWholesaleList(SpuQueryDTO param);
}
