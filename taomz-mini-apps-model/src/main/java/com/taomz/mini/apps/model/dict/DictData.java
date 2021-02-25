package com.taomz.mini.apps.model.dict;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_dict_data")
public class DictData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 字典code
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 是否有子集 0无 1有
     */
    private Integer hasChild;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否有效 1有效 0无效
     */
    private Integer deleteFlag;


}
