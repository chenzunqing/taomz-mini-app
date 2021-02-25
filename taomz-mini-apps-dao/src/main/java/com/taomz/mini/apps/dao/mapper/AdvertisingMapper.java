package com.taomz.mini.apps.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.dto.AdvertisingDTO;
import com.taomz.mini.apps.model.Advertising;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 广告信息 Mapper 接口
 * </p>
 *
 * @author liaobing
 * @since 2020-11-24
 */
public interface AdvertisingMapper extends BaseMapper<Advertising> {

    List<AdvertisingDTO> getAdListByAdsCode(@Param(value = "adsCode")String adsCode);
}
