package com.taomz.mini.apps.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 登录配置
 *
 * @author Wei.Guang
 * @create 2018-06-22 10:23
 **/
@Configuration
public class AppLoginConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private AppLoginInterceptor appLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/mini/apps/home",
                        "/wx/login",
                        "/wx/get_wx_phone_num",
                        "/wx/category/get_category",
                        "/sys/config/images/url",
                        "/wx/pay/query",
                        "/wx/advertising",
                        "/wx/pay/close",
                        "/wx/dict/dic_list",
                        "/wx/country/get_country_all",
                        "/yf/pay/**",
                        "/spu/hot/hot_recommend",
                        "/spu/hot/hot_recommend_list",
                        "/wx/goods/wholesale/qry_app_spu_List",
                        "/wx/brand/good/meat/list",
                        "/wx/user/brand/recent_brand_list",
                        "/wx/brand/hot/list_by_type",
                        "/wx/brand/hot/list_by_type",
                        "/wx/activity/signing_up_list",
                        "/wx/activity/signing_up_schedule",
                        "/wx/activity/list",
                        "/wx/activity/detail",
                        "/wx/activity/detail");
    }

}
