package com.taomz.mini.apps.service.spu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.TSpuRecommend;
import com.taomz.mini.apps.service.dto.spu.HotRecommendQueryDTO;
import com.taomz.sha.util.response.BaseResponseModel;

import java.util.List;

/**
 * <p>
 * 产品推荐主表 服务类
 * </p>
 *
 * @author liaobing
 * @since 2020-11-17
 */
public interface TSpuRecommendService extends IService<TSpuRecommend> {
    /**
     * 获取爆款推荐
     * 
     * @return
     */
    BaseResponseModel getHotSpu(HotRecommendQueryDTO dto);

    BaseResponseModel getHotRecommend();

}
