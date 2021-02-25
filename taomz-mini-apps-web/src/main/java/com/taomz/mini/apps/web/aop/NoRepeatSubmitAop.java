package com.taomz.mini.apps.web.aop;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.service.redis.RedisLockHelper;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.mini.apps.util.ComUtil;
import com.taomz.mini.apps.util.JWTUtil;
import com.taomz.mini.apps.util.annotation.CacheParam;
import com.taomz.mini.apps.util.annotation.NoRepeatSubmit;
import com.taomz.mini.apps.util.enums.ErrorMessageEnum;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.sha.util.response.BaseResponseModelForTaomz;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: aop解析注解
 * @Author: liaoxiaoli
 * @CreateDate: 2018/10/23 15:31
 * @UpdateUser: liaoxiaoli
 * @UpdateDate: 2018/10/23 15:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class NoRepeatSubmitAop {

    @Autowired
    private RedisLockHelper redisLockHelper;

    @Around("@annotation(com.taomz.mini.apps.util.annotation.NoRepeatSubmit)")
    public Object arround(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        NoRepeatSubmit lock = method.getAnnotation(NoRepeatSubmit.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key don't null...");
        }
        final String lockKey = getLockKey(pjp);
        String value = UUID.randomUUID().toString();
        // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
        final boolean success = redisLockHelper.lock(lockKey, value, lock.expire(), lock.timeUnit());
        if (!success) {
            log.error("重复提交");
            return JSON.toJSONString(BaseResponseModelForTaomz.fail(String.valueOf(ErrorMessageEnum.OPERATE_FREQUENTLY.getCode()), ErrorMessageEnum.OPERATE_FREQUENTLY.getMessage(), null));
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            log.error("系统异常", throwable);
            if (throwable instanceof ExceptionWrapper) {
                Map map = BeanUtil.beanToMap(throwable);
                return JSON.toJSONString(BaseResponseModelForTaomz.fail(String.valueOf(map.get("errorCode")), ((ExceptionWrapper) throwable).getCustomErrMsg(), null));
            }
            return JSON.toJSONString(BaseResponseModelForTaomz.fail(ErrorMessageEnum.EXCEPTION.getMessage()));
        }
    }

    public String getLockKey(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        NoRepeatSubmit lockAnnotation = method.getAnnotation(NoRepeatSubmit.class);
        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        // TODO 默认解析方法里面带 CacheParam 注解的属性,如果没有尝试着解析实体对象中的
        for (int i = 0; i < parameters.length; i++) {
            final CacheParam annotation = parameters[i].getAnnotation(CacheParam.class);
            if (annotation == null) {
                continue;
            }
            builder.append(lockAnnotation.delimiter()).append(args[i]);
        }
        if (StringUtils.isEmpty(builder.toString())) {
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object object = args[i];
                if (object == null) {
                    continue;
                }
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    final CacheParam annotation = field.getAnnotation(CacheParam.class);
                    if (annotation == null) {
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(lockAnnotation.delimiter()).append(ReflectionUtils.getField(field, object));
                }
            }
        }
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        StringBuffer sbKey = new StringBuffer(RedisRootNamespace.CACHE_ROOT);
        sbKey.append(RedisRootNamespace.NAMESPACE_SEPARATOR).append(RedisRootNamespace.NO_REPEAT);
        sbKey.append(RedisRootNamespace.NAMESPACE_SEPARATOR).append(lockAnnotation.prefix());
        sbKey.append(RedisRootNamespace.NAMESPACE_SEPARATOR).append(lockAnnotation.channel());
        if (StringUtils.isNotEmpty(lockAnnotation.channel())) {
            if ("ba".equals(lockAnnotation.channel())) {
                String authorization = request.getHeader("Authorization");
                if (!ComUtil.isEmpty(authorization)) {
                    String userNo = JWTUtil.getUserNo(authorization);
                    sbKey.append(userNo).append(RedisRootNamespace.NAMESPACE_SEPARATOR);
                }
            } else {
                Integer userId = (Integer) request.getAttribute("userId");
                if (userId != null) {
                    sbKey.append(userId).append(RedisRootNamespace.NAMESPACE_SEPARATOR);
                }
            }
        }
        sbKey.append(builder.toString());
        return sbKey.toString();
    }

}
