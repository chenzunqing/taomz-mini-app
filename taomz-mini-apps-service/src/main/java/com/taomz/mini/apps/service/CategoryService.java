package com.taomz.mini.apps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.CategoryDTO;
import com.taomz.mini.apps.dto.CategoryQueryDTO;
import com.taomz.mini.apps.model.Category;
import com.taomz.sha.util.response.BaseResponseModel;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-21
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询分类列表   级联查询也可以用此接口
     * @param categoryQueryDTO          类型  1资讯 2品牌 3商品
     * @param publishStatus 上下架状态
     * @return
     */
    BaseResponseModel getAllCategory(CategoryQueryDTO categoryQueryDTO, Integer publishStatus);

    /**
     * 查询
     * @param type
     * @return
     */
    List<CategoryDTO> getCategory(Integer type);

    /**
     * 根据类目ID查询类目名称
     * @param categoryId
     * @param publishStatus
     * @param deleteFlag
     * @return
     */
    String getCategoryNameById(Long categoryId, Integer publishStatus, Integer deleteFlag);

}
