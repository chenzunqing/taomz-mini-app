package com.taomz.mini.apps.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Title: Valids
 * @Package com.taomz.live.portal.util.annotation
 * @Description: 自定义注解(验证使用)
 * @author wuque.hua
 * @date 2020年5月26日 上午10:59:19
 * @version V1.0
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Valids {
    Valid[] value();
}
