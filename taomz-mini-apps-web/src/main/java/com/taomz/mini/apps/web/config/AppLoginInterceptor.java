package com.taomz.mini.apps.web.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.taomz.mini.apps.model.login.TUser;
import com.taomz.mini.apps.service.redis.RedisRootNamespace;
import com.taomz.mini.apps.service.redis.RedisService;
import com.taomz.sha.util.response.BaseCodeResultForTaomz;
import com.taomz.sha.util.response.BaseResponseModelForTaomz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录拦截器
 *
 * @author Wei.Guang
 * @create 2018-06-22 9:49
 **/
@Component
@Slf4j
public class AppLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    /**
     * 在请求被处理之前调用
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader("login_token");
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            if (StrUtil.isBlank(token)) {
                //token不存在
                log.error("mini apps 登录失效，请登录！");
                PrintWriter pw = response.getWriter();
                pw.write(JSON.toJSONString(BaseResponseModelForTaomz.fail(BaseCodeResultForTaomz.LOGIN_FAIL.getCode(), BaseCodeResultForTaomz.LOGIN_FAIL.getMessage(), null)));
                return false;
            }
            // 获取redis中保存的用户的token信息
            String key = redisService.generateCacheKey(RedisRootNamespace.TOKEN, token);
            Object user =  redisService.get(key);

            if (null == user) {
                log.error("mini apps 登录失效，请登录！");
                PrintWriter pw = response.getWriter();
                pw.write(JSON.toJSONString(BaseResponseModelForTaomz.fail(BaseCodeResultForTaomz.LOGIN_FAIL.getCode(), BaseCodeResultForTaomz.LOGIN_FAIL.getMessage(), null)));
                return Boolean.FALSE;
            }
            TUser tuser = JSONUtil.toBean(user.toString(), TUser.class);
            request.setAttribute("userId", tuser.getId());
            request.setAttribute("phone", tuser.getPhone());
            request.setAttribute("isSubaccount", Boolean.FALSE);
            return Boolean.TRUE;
            /*if (Objects.nonNull(subUserId)) {
                request.setAttribute("userId", userId);
                request.setAttribute("phone", phone);
                request.setAttribute("subUserId", subUserId);
                request.setAttribute("isSubaccount", Boolean.TRUE);
                return Boolean.TRUE;
            } else {
                request.setAttribute("userId", userId);
                request.setAttribute("phone", phone);
                request.setAttribute("isSubaccount", Boolean.FALSE);
                return Boolean.TRUE;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter pw = response.getWriter();
            pw.write(JSON.toJSONString(BaseResponseModelForTaomz.fail(BaseCodeResultForTaomz.LOGIN_FAIL.getCode(), BaseCodeResultForTaomz.LOGIN_FAIL.getMessage(), null)));
        }
        return Boolean.FALSE;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

