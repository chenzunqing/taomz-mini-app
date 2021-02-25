package com.taomz.mini.apps.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Title: Valid
 * @Package com.taomz.live.portal.util.annotation
 * @Description: 自定义注解(验证使用)
 * @author wuque.hua
 * @date 2020年5月26日 上午10:58:41
 * @version V1.0
 *
 */
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Valid {
    /** 是否必填 默认为false */
    boolean required() default false;

    /** 数值类型支持 最大值 */
    double maxValue() default Double.MAX_VALUE;

    /** 数值类型支持 最小值 */
    double minValue() default Double.MIN_VALUE;

    /** String类型支持 正则匹配 */
    String regex() default "";

    /** String类型支持 特定长度 */
    long length() default -1;

    /** String类型支持 最大长度 */
    long maxLength() default -1;

    /** String类型支持 最小长度 */
    long minLength() default -1;

    /** 验证参数名 */
    String name();
}
