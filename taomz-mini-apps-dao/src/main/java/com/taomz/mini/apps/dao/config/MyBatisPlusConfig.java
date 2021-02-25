package com.taomz.mini.apps.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoluban@taomz.com
 * @version V1.0
 * @title : MyBatisPlusConfig
 * @Package : com.taomz.mini.apps.dao.mybatis
 * @Description: mybatis 配置
 * @date 2020/6/12 12:48
 **/
@Configuration
@Slf4j
public class MyBatisPlusConfig {

    /**
     * 配置分页插件
     * 
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        log.debug("注册分页插件");
        return new PaginationInterceptor();
    }
}
