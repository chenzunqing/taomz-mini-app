package com.taomz.mini.apps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.AdvertisingMapper;
import com.taomz.mini.apps.dto.AdvertisingDTO;
import com.taomz.mini.apps.model.Advertising;
import com.taomz.mini.apps.service.AdvertisingService;
import com.taomz.mini.apps.service.cache.AdvertisingCacheService;
import com.taomz.mini.apps.util.DateUtil;
import com.taomz.mini.apps.util.StringUtil;
import com.taomz.sha.util.response.BaseResponseModel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 广告信息 服务实现类
 * </p>
 *
 * @author liaobing
 * @since 2020-11-24
 */
@Service
public class AdvertisingServiceImpl extends ServiceImpl<AdvertisingMapper, Advertising> implements AdvertisingService {
    @Autowired
    private AdvertisingCacheService advertisingCacheService;

    @Override
    public BaseResponseModel findByCode(String adsCode) {
        BaseResponseModel response = new BaseResponseModel();
        List<AdvertisingDTO> result = new ArrayList<>();
        List<AdvertisingDTO> cacheList = advertisingCacheService.getAdvertisingListFromCache(adsCode);
        if (CollectionUtils.isNotEmpty(cacheList)) {
            Boolean flag = Boolean.TRUE;
            //移除时间过期的广告
            for (AdvertisingDTO advertisingDTO : cacheList) {
                if (DateUtil.isEffectiveDate(new Date(), advertisingDTO.getStartTime(), advertisingDTO.getEndTime())) {
                    result.add(advertisingDTO);
                } else {
                    flag = Boolean.FALSE;
                    advertisingCacheService.removeAdvertisingListFromCache(adsCode);
                    break;
                }
            }
            if (flag) {
                return response.warpSuccess().setContent(result);
            }
        }
        result = this.baseMapper.getAdListByAdsCode(adsCode);
        if (CollectionUtils.isNotEmpty(result)) {
            for (AdvertisingDTO advertising : result) {
                advertising.setAdLinkUrl(StringUtil.getUrlByParam(advertising.getJumpUrl(),advertising.getJumpParam()));
                advertising.setAdLinkType(advertising.getJumpColumnValue() == null ? null : String.valueOf(advertising.getJumpColumnValue()));
            }
            advertisingCacheService.setAdvertisingListToCache(adsCode, result);
        }
        return response.warpSuccess().setContent(result);
    }
}
