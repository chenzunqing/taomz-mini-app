package com.taomz.mini.apps.web.config;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @Title: DruidFilter.java
 * @Package com.taomz.live.portal.web.config
 * @Description: TODO(类的描述)
 * @author wuque.hua
 * @date 2020年5月27日 下午5:26:22
 * @version V1.0
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*") // 忽略资源
})
public class DruidFilter extends WebStatFilter {

}
