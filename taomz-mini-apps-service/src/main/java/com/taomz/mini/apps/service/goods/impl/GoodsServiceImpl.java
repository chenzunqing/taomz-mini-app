package com.taomz.mini.apps.service.goods.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taomz.mini.apps.dao.mapper.goods.SpuExtendMapper;
import com.taomz.mini.apps.dao.mapper.goods.SpuMapper;
import com.taomz.mini.apps.model.goods.Spu;
import com.taomz.mini.apps.dto.goods.SpuQueryDTO;
import com.taomz.mini.apps.service.CategoryService;
import com.taomz.mini.apps.service.comm.DictService;
import com.taomz.mini.apps.service.goods.GoodsCacheService;
import com.taomz.mini.apps.service.goods.GoodsService;
import com.taomz.mini.apps.util.enums.DeleteEnum;
import com.taomz.mini.apps.util.enums.activity.PublishEnum;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.mini.apps.util.reslut.PageResult;
import com.taomz.sha.util.response.BaseResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Description: 商品接口实现
 * @Author: liaoxiaoli
 * @CreateDate: 2018/6/22 11:26
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/6/22 11:26
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuExtendMapper spuExtendMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsCacheService goodsCacheService;

    @Autowired
    private DictService dictService;


    @Override
    public com.taomz.mini.apps.dto.goods.SpuDTO getSpuInfoBySpuId(Long spuId, Integer deleteFlag) throws ExceptionWrapper {
        if (spuId == null) {
            throw new ExceptionWrapper("商品ID为空");
        }
        com.taomz.mini.apps.dto.goods.SpuDTO spuBasicInfo = goodsCacheService.getGoodsInfoFromCache(spuId);
        if (spuBasicInfo != null) {
            return spuBasicInfo;
        }
        spuBasicInfo = new com.taomz.mini.apps.dto.goods.SpuDTO();
        Spu spuBasic = spuMapper.selectOne(
                Wrappers.lambdaQuery(Spu.class)
                        .eq(Spu::getId, spuId)
                        .eq(Objects.nonNull(deleteFlag), Spu::getDeleteFlag, deleteFlag));
        if (spuBasic != null) {
            BeanUtils.copyProperties(spuBasic, spuBasicInfo);
            spuBasicInfo.setSpuId(spuId);
            spuBasicInfo.setIsSample(spuBasic.getIsSample());
            // 查询分类
            spuBasicInfo.setCategoryName(categoryService.getCategoryNameById(spuBasic.getCategoryId(),
                    PublishEnum.UP.getIntVlue(), DeleteEnum.EFFECTIVE.getIntValue()) + " ");
            goodsCacheService.setGoodsInfoToCache(spuBasicInfo);
        } else {
            log.warn("商品:{} 信息不存在", spuId);
            throw new ExceptionWrapper("商品不存在");
        }
        return spuBasicInfo;
    }

    @Override
    public BaseResponseModel qryWholesaleList(SpuQueryDTO param) {
        Page<com.taomz.mini.apps.dto.goods.SpuDTO> page = new Page<>(param.getPageNum(), param.getPageSize());
        Page<com.taomz.mini.apps.dto.goods.SpuDTO> spuDTOPage = spuMapper.qryWholesaleList(page, param);
        spuDTOPage.getRecords().stream().parallel().forEach(spuDTO -> {
            if (StrUtil.isNotBlank(spuDTO.getFromPlaceBrand())) {
                spuDTO.setFromPlaceBrand(dictService.dictNameByCode(spuDTO.getFromPlace()));
            }
            // 字典 贸易方式
            spuDTO.setTradeType(dictService.dictNameByCode(spuDTO.getTradeType()));
            // 新上, 最近三个月新上品牌
            spuDTO.setSpuExtend(spuExtendMapper.getSpuExtendBySpuId(spuDTO.getSpuId()));
        });
        return new BaseResponseModel().warpSuccess().setContent(new PageResult(page.getSize(), spuDTOPage.getTotal()).setData(spuDTOPage.getRecords()));
    }
}
