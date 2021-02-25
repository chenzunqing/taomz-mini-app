package com.taomz.mini.apps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.taomz.mini.apps.dao.mapper.CategoryMapper;
import com.taomz.mini.apps.dto.CategoryDTO;
import com.taomz.mini.apps.dto.CategoryQueryDTO;
import com.taomz.mini.apps.model.Category;
import com.taomz.mini.apps.service.CategoryService;
import com.taomz.mini.apps.util.enums.activity.PublishEnum;
import com.taomz.sha.util.response.BaseResponseModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-21
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public BaseResponseModel getAllCategory(CategoryQueryDTO categoryQueryDTO, Integer publishStatus) {
        BaseResponseModel response = new BaseResponseModel();
        List<CategoryDTO> categoryList = getBaseMapper().getAllCategory(
                categoryQueryDTO.getLev(),
                categoryQueryDTO.getPid(),
                publishStatus,
                categoryQueryDTO.getType());
        return response.warpSuccess()
                .setContent(CollectionUtils.isEmpty(categoryList) ? Lists.newArrayList() : categoryList);
    }

    @Override
    public List<CategoryDTO> getCategory(Integer type) {
        List<CategoryDTO> categoryList = getBaseMapper().getAllCategory(1, null, PublishEnum.UP.getIntVlue(), type);
        getBuildCategoryTree(categoryList);
        return categoryList;
    }

    @Override
    public String getCategoryNameById(Long categoryId, Integer publishStatus, Integer deleteFlag) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Category::getId, categoryId)
                .eq(null != deleteFlag, Category::getDeleteFlag, deleteFlag)
                .eq(null != publishStatus, Category::getPublishStatus, publishStatus);
        Category category = this.getOne(queryWrapper);
        if (category != null) {
            return category.getName();
        }
        return StringUtils.EMPTY;
    }

    public void getBuildCategoryTree(List<CategoryDTO> categoryList) {
        if (CollectionUtils.isNotEmpty(categoryList)) {
            for (CategoryDTO category : categoryList) {
                List<CategoryDTO> childList = getBaseMapper().getAllCategory(null, category.getCategoryId(), null,
                        category.getType());
                getBuildCategoryTree(childList);
                category.setChildList(childList);
            }
        }
    }
}
