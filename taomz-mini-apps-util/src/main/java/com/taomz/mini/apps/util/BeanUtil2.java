package com.taomz.mini.apps.util;


import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.beanutils.BeanMap;

import java.util.List;
import java.util.Map;

/**
 * @Description: 实现对象相互转换
 * @Author: liaoxiaoli
 * @CreateDate: 2018/6/1 10:07
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/6/1 10:07
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class BeanUtil2 {
    /**
     * 拷贝实体集合，sourceList
     * 只支持自定义实体集合拷贝
     * 应用场景：DTO <=> DO 等
     */
    public static void copyPropertiesList(List sourceList, List targetList, Class clazz) throws IllegalAccessException, InstantiationException {
        for (Object items : sourceList) {
            Object target = clazz.newInstance();
            BeanUtil.copyProperties(items, target);
            targetList.add(target);
        }
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new BeanMap(obj);
    }
}
