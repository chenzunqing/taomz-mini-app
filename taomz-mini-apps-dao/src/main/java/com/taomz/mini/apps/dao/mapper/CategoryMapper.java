package com.taomz.mini.apps.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.dto.CategoryDTO;
import com.taomz.mini.apps.model.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-21
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryDTO> getAllCategory(@Param(value = "lev") Integer lev,
                                     @Param(value = "pid") Long pid,
                                     @Param(value = "publishStatus") Integer publishStatus,
                                     @Param(value = "type") int type);
}
