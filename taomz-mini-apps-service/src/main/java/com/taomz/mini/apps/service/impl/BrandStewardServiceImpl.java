package com.taomz.mini.apps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.taomz.mini.apps.dao.mapper.BrandStewardMapper;
import com.taomz.mini.apps.dto.brand.AppBrandNationalDTO;
import com.taomz.mini.apps.model.BrandSteward;
import com.taomz.mini.apps.service.BrandStewardService;
import com.taomz.sha.util.response.BaseResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class BrandStewardServiceImpl implements BrandStewardService {

    @Autowired
    private BrandStewardMapper brandStewardMapper;

    @Override
    public BaseResponseModel getNationalLineList() {
        List<BrandSteward> brandStewardList = brandStewardMapper.selectList(new QueryWrapper<>());
        List<AppBrandNationalDTO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(brandStewardList)) {
            brandStewardList.stream().forEach(m -> {
                AppBrandNationalDTO nationalDTO = new AppBrandNationalDTO();
                nationalDTO.setId(m.getId());
                nationalDTO.setNationalLine(m.getNationalLine());
                list.add(nationalDTO);
            });
        }
        return new BaseResponseModel().warpSuccess().setContent(list);
    }
}
