package com.taomz.mini.apps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.Advertising;
import com.taomz.sha.util.response.BaseResponseModel;


/**
 * <p>
 * 广告信息 服务类
 * </p>
 *
 * @author liaobing
 * @since 2020-11-24
 */
public interface AdvertisingService extends IService<Advertising> {
    /**
     * 根据广告位编码查询该广告位所有广告
     * @param adsCode 	广告位编码
     * @return 广告集合
     */
    BaseResponseModel findByCode(String adsCode);
}
