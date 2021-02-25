package com.taomz.mini.apps.dao.mapper.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.dto.DictDataDTO;
import com.taomz.mini.apps.model.dict.DictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-25
 */
public interface DictDataMapper extends BaseMapper<DictData> {
    /**
     * 查询指定类型的信息列表
     *
     * @param dictType
     * @return
     */
    List<DictDataDTO> listDictByType(@Param("dictType") String dictType);

    /**
     * 查询指定类型的信息列表
     *
     * @param parentId
     * @return
     */
    List<DictDataDTO> listDictByPid(@Param("parentId") Long parentId);
}
