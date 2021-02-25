package com.taomz.mini.apps.util.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  防止重复提交标记注解
 * @Author: liaoxiaoli
 * @CreateDate: 2018/10/23 14:53
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/10/23 14:53
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Target(ElementType.METHOD) // 作用到方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
@Documented
@Inherited
public @interface NoRepeatSubmit {
    /**
     * redis 锁key的前缀
     *
     * @return redis 锁key的前缀
     */
    String prefix() default "";

    /**
     * 过期秒数,默认为5秒
     *
     * @return 轮询锁的时间
     */
    long expire() default 5;

    /**
     * 超时时间单位
     *
     * @return 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * <p>Key的分隔符（默认 :）</p>
     * <p>生成的Key：N:SO1008:500</p>
     *
     * @return String
     */
    String delimiter() default ":";

    /**
     * 请求渠道
     * @return
     */
    String channel() default "app";
}
