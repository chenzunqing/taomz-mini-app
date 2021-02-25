package com.taomz.mini.apps.service.spu.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.TSpuRecommendMapper;
import com.taomz.mini.apps.ext.BrandBasicIfoExt;
import com.taomz.mini.apps.model.SpuHotRecommend;
import com.taomz.mini.apps.model.TSpuRecommend;
import com.taomz.mini.apps.service.dto.spu.HotRecommendQueryDTO;
import com.taomz.mini.apps.service.spu.TSpuRecommendService;
import com.taomz.mini.apps.util.reslut.PageResult;
import com.taomz.sha.util.response.BaseResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 产品推荐主表 服务实现类
 * </p>
 *
 * @author liaobing
 * @since 2020-11-17
 */
@Service
public class TSpuRecommendServiceImpl extends ServiceImpl<TSpuRecommendMapper, TSpuRecommend>
        implements TSpuRecommendService {

    @Autowired
    private TSpuRecommendMapper spuRecommendMapper;

    /**
     * 获取爆款推荐列表
     * 
     * @param dto
     * @return
     */
    @Override
    public BaseResponseModel getHotSpu(HotRecommendQueryDTO dto) {
        Page<SpuHotRecommend> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        IPage<SpuHotRecommend> list = spuRecommendMapper.getSpuHotRecommend(page,dto.getSpuRecommendId());
        PageResult result = new PageResult(dto.getPageSize(), page.getTotal()).setData(list.getRecords());
        return new BaseResponseModel().warpSuccess().setContent(result);
    }

    /**
     * 获取爆款推荐的产品类别
     * 
     * @return
     */
    @Override
    public BaseResponseModel getHotRecommend() {
        return new BaseResponseModel().warpSuccess().setContent(spuRecommendMapper.getSpuHotList());
    }
}
