package com.taomz.mini.apps.util.annotation;

import java.lang.annotation.*;

/**
 * @Description:   锁的参数
 * @Author: liaoxiaoli
 * @CreateDate: 2018/10/23 14:53
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/10/23 14:53
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {
    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}
