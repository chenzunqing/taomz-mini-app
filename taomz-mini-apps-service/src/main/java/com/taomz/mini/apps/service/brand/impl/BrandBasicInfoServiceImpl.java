package com.taomz.mini.apps.service.brand.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.taomz.mini.apps.dao.mapper.BrandStewardMapper;
import com.taomz.mini.apps.dao.mapper.TUserMapper;
import com.taomz.mini.apps.dao.mapper.brand.BrandBasicInfoMapper;
import com.taomz.mini.apps.dao.mapper.goods.SpuMapper;
import com.taomz.mini.apps.dto.CategoryDTO;
import com.taomz.mini.apps.dto.brand.BrandBasicInfoQueryDTO;
import com.taomz.mini.apps.dto.brand.BrandRecommendDTO;
import com.taomz.mini.apps.dto.brand.GMBrandInfoDTO;
import com.taomz.mini.apps.ext.BrandBasicIfoExt;
import com.taomz.mini.apps.model.BrandSteward;
import com.taomz.mini.apps.model.brand.BrandBasicInfo;
import com.taomz.mini.apps.model.brand.BrandRecommend;
import com.taomz.mini.apps.model.login.TUser;
import com.taomz.mini.apps.service.CategoryService;
import com.taomz.mini.apps.service.brand.BrandBasicInfoService;
import com.taomz.mini.apps.service.comm.DictService;
import com.taomz.mini.apps.service.dto.brand.BrandQueryHotListDTO;
import com.taomz.mini.apps.util.DateUtil;
import com.taomz.mini.apps.util.enums.CategoryTypeEnum;
import com.taomz.mini.apps.util.reslut.PageResult;
import com.taomz.sha.util.response.BaseResponseModel;
import jodd.util.ArraysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 品牌基本信息 服务实现类
 * </p>
 *
 * @author liaobing
 * @since 2020-11-21
 */
@Service
public class BrandBasicInfoServiceImpl extends ServiceImpl<BrandBasicInfoMapper, BrandBasicInfo>
        implements BrandBasicInfoService {

    @Autowired
    private BrandBasicInfoMapper brandBasicInfoMapper;

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DictService dictService;

    @Autowired
    private BrandStewardMapper brandStewardMapper;

    @Override
    public BaseResponseModel queryRecentBrandList() {
        return new BaseResponseModel().warpSuccess().setContent(brandBasicInfoMapper.queryRecentBrandList());
    }

    @Override
    public BaseResponseModel homeRecommendList() {
        return new BaseResponseModel().warpSuccess().setContent(brandBasicInfoMapper.homeRecommendList());
    }

    @Override
    public BaseResponseModel getBrandTypeTag() {
        List<BrandRecommend> list = brandBasicInfoMapper.getBrandTypeTag();
        List<BrandRecommendDTO> categoryList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(list)) {
            categoryList = list.stream().map(n -> {
                        String type = categoryService.getCategoryNameById(Long.valueOf(n.getType()), null, null);
                        return new BrandRecommendDTO().setId(n.getId()).setType(type);
                    }
            ).collect(Collectors.toList());
        }
        return new BaseResponseModel().warpSuccess().setContent(categoryList);
    }

    @Override
    public BaseResponseModel queryGMListPage(BrandBasicInfoQueryDTO param, Integer userId) {
        Boolean priceDisplayFlag = Boolean.FALSE;
        if (Objects.nonNull(userId)) {
            TUser user = userMapper.selectById(userId);
            if (Objects.nonNull(user) && StrUtil.isNotBlank(user.getCodeIdentityLetter())) {
                priceDisplayFlag = user.getCodeIdentityLetter().contains(CategoryTypeEnum.商会会员.getLetter());
            }
        }
        if (Objects.nonNull(param.getBrandStewardId())) {
            param.setBrandStewardCountryCode(Optional.ofNullable(brandStewardMapper.selectById(param.getBrandStewardId())).map(BrandSteward::getCountryCode).orElse(StrUtil.EMPTY));
        }
        Boolean finalPriceDisplayFlag = priceDisplayFlag;
        List<CategoryDTO> categoryDTOList = categoryService.getCategory(2);
        Map<String, String> categoryMap = categoryDTOList.stream()
                .collect(Collectors.toMap(categoryDTO -> categoryDTO.getCategoryId().toString(), CategoryDTO::getName));
        Page<GMBrandInfoDTO> page = new Page<>(param.getPageNum(), param.getPageSize());
        Page<GMBrandInfoDTO> brandInfoDTOPage = getBaseMapper().queryGMListPage(page, param);
        brandInfoDTOPage.getRecords().stream().parallel().forEach(brandInfoDTO -> {
            if (CollectionUtil.isNotEmpty(categoryMap) && StrUtil.isNotBlank(brandInfoDTO.getMainCategory())) {
                List<String> categoryItems = Arrays.asList(brandInfoDTO.getMainCategory().split(",")).stream()
                        .map(item -> categoryMap.get(item)).filter(Objects::nonNull).collect(Collectors.toList());
                if (CollectionUtil.isNotEmpty(categoryItems)) {
                    brandInfoDTO.setMainCategoryItems(categoryItems);
                    brandInfoDTO.setMainCategory(String.join(" ", categoryItems));
                }
            }
            if (StrUtil.isNotBlank(brandInfoDTO.getAuthChannel())) {
                // 授权渠道
                List<String> authChannelItems = Arrays.asList(brandInfoDTO.getAuthChannel().split(",")).stream()
                        .map(str -> dictService.dictNameByCode(str)).filter(Objects::nonNull)
                        .collect(Collectors.toList());
                if (CollectionUtil.isNotEmpty(authChannelItems)) {
                    brandInfoDTO.setAuthChannel(String.join(" ", authChannelItems));
                }
            }
            // 贸易种类
            brandInfoDTO.setTradeTypes(dictService.dictNameByCode(brandInfoDTO.getTradeTypes()));
            // 产品列表
            brandInfoDTO.setSpuDTOS(spuMapper.qryGMSpuAppList(brandInfoDTO.getId()));
            brandInfoDTO.setPriceDisplayFlag(finalPriceDisplayFlag);
        });
        return new BaseResponseModel().warpSuccess().setContent(
                new PageResult(page.getSize(), brandInfoDTOPage.getTotal()).setData(brandInfoDTOPage.getRecords()));
    }

    @Override
    public BaseResponseModel addSerchNum(Integer brandId) {
        BaseResponseModel res = new BaseResponseModel();
        BrandBasicInfo brand = this.getById(brandId);
        Assert.isTrue(null != brand, "查无此品牌");
        Integer num = brand.getSearchNum() + 1;
        brand.setSearchNum(num);
        this.updateById(brand);
        return res.warpSuccess();
    }

    @Override
    public BaseResponseModel hotListByType(BrandQueryHotListDTO dto) {
        BaseResponseModel res = new BaseResponseModel();
        Page<BrandBasicIfoExt> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String monthBeforDayStr = "";
        if (0 == dto.getType()) { // 周榜
            Date sevenBeforDay = DateUtil.getInternalDateByDay(DateUtil.now(), -7);
            monthBeforDayStr = DateUtil.getFormatDateStr(sevenBeforDay, DateUtil.DATATIMEF_STR);
        } else if (1 == dto.getType()) { // 月榜
            Date monthBeforDay = DateUtil.getInternalDateByDay(DateUtil.now(), -30);
            monthBeforDayStr = DateUtil.getFormatDateStr(monthBeforDay, DateUtil.DATATIMEF_STR);
        }
        IPage<BrandBasicIfoExt> list = brandBasicInfoMapper.queryHostBrandListForType(page, monthBeforDayStr);
        List<BrandBasicIfoExt> result = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(list.getRecords())) {
            for (BrandBasicIfoExt ext : list.getRecords()) {
                List categoryList = Arrays.asList(ArraysUtil.join(ext.getMainCategory().split(",")));
                List categoryName = new ArrayList();
                if (categoryList.size() > 2) {
                    categoryList = categoryList.subList(0, 2);

                }
                categoryList.forEach(a -> categoryName
                        .add(categoryService.getCategoryNameById(Long.valueOf(a.toString()), null, null)));
                String categoryNameStr = (String) categoryName.stream().map(n -> String.valueOf(n))
                        .collect(Collectors.joining(" ", "", ""));
                ext.setMainCategory(categoryNameStr);
                ext.setBrandIdea(deleteAllHTMLTag(ext.getBrandIdea()));
                result.add(ext);
            }
        }
        PageResult resultPg = new PageResult(dto.getPageSize(), page.getTotal()).setData(result);
        return res.warpSuccess().setContent(resultPg);
    }

    public  String deleteAllHTMLTag(String source) {
        if(source == null) {
            return "";
        }
        String s = source;
        /** 删除普通标签  */
        s = s.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
        /** 删除转义字符 */
        s = s.replaceAll("&.{2,6}?;", "");
        s = s.replaceAll("\\n", "");
        return s;
    }
}
