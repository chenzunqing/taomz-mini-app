package com.taomz.mini.apps.web.aop;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.taomz.mini.apps.util.annotation.Inject;

/**
 * @ClassName: ArgumentInjectResolver
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author huawuque@taomz.com
 * @date 2020-07-01 15:44
 * 
 */
@Component
@Aspect
public class ArgumentInjectResolver {

    @Around(value = "@annotation(com.taomz.mini.apps.util.annotation.Inject)")
    public Object jsonBody(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = getArgs(joinPoint);
        return joinPoint.proceed(args);
    }

    @SuppressWarnings("rawtypes")
    private Object[] getArgs(ProceedingJoinPoint joinPoint) {
        Object[] reqArgs = joinPoint.getArgs(); // 原有的参数
        try {
            HttpServletRequest request = getRequest();
            String jsonStr = readerInputStream(request.getInputStream());
            if (StringUtils.isBlank(jsonStr)) {
                return reqArgs;
            }
            Method currentMethod = getMethod(joinPoint);
            Inject jsonBody = currentMethod.getAnnotation(Inject.class);
            if (jsonBody.option() && jsonBody.params().length <= 0) { // 设置了指定处理，但未配置处理的参数，则直接跳过
                return reqArgs;
            }
            List<String> jsonBody_Params = Arrays.asList(jsonBody.params()); // 需要单独处理的参数
            JSONObject reqJson = JSONObject.parseObject(jsonStr);
            Set<String> reqJsonKeySet = reqJson.keySet(); // JSON数据中的所有KEY
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] params = u.getParameterNames(currentMethod); // 方法中的所有参数名称
            Parameter[] parameters = currentMethod.getParameters();
            Object[] args = new Object[params.length];
            for (int i = 0; i < params.length; i++) {
                Class<?> c = parameters[i].getType();
                Object arg = null;
                if (c.isArray()) { // 处理数组
                    arg = Array.newInstance(c.getComponentType(), 0);
                } else {
                    try {
                        arg = c.newInstance();
                    } catch (InstantiationException ie) {
                        Constructor cons = c.getConstructor(String.class);
                        cons.setAccessible(true);
                        arg = cons.newInstance("0");
                    }
                }
                arg = reqArgs[i] == null ? arg : reqArgs[i]; // 返回原数据
                if (jsonBody.option() && !jsonBody_Params.contains(params[i])) { // 指定参数处理时，若不包含则跳过，不处理

                } else if (!jsonBody.option() && jsonBody_Params.contains(params[i])) { // 例外参数处理时，若包含，则不处理

                } else {
                    if (arg.getClass().getName().contains("java.lang")) {
                        if (reqJsonKeySet.contains(params[i])) { // 参数存在
                            if (c.isArray()) {
                                arg = reqJson.getJSONArray(params[i]).toJavaObject(c);
                            } else {
                                arg = reqJson.getObject(params[i], arg.getClass());
                            }
                        }
                    } else {
                        arg = reqJson.toJavaObject(arg.getClass());
                    }
                }
                args[i] = arg;
            }
            reqArgs = args;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return reqArgs;
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        return currentMethod;
    }

    private HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

    private String readerInputStream(InputStream in) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString();
    }

}
