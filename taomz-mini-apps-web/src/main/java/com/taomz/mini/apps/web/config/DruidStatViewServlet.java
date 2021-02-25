package com.taomz.mini.apps.web.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @Title: DruidStatViewServlet.java
 * @Package com.taomz.code.web.config
 * @Description: TODO(配置druid的访问路径，登录账号信息，黑白IP名单等信息，相当与传统WEB项目的 web.xml 中的配置方式。)
 * @author wuque.hua
 * @date 2020年5月27日 下午5:26:37
 * @version V1.0
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*", initParams = {
        @WebInitParam(name = "allow", value = "127.0.0.1,172.30.16.42,192.168.11.188"), // IP白名单(没有配置或者为空，则允许所有访问)
        @WebInitParam(name = "deny", value = "192.168.1.73"), // IP黑名单
                                                              // (存在共同时，deny优先于allow)
        @WebInitParam(name = "loginUsername", value = "admin"), // 用户名
        @WebInitParam(name = "loginPassword", value = "123456"), // 密码
        @WebInitParam(name = "resetEnable", value = "false") // 禁用HTML页面上的“Reset
        // All”功能
})
public class DruidStatViewServlet extends StatViewServlet {

}
