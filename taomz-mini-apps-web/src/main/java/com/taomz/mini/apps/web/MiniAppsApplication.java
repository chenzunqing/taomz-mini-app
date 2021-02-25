package com.taomz.mini.apps.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @author huawuque@taomz.com
 * @ClassName: LiveApplication
 * @Description: (系统启动入口)
 * @date 2020-06-04 16:43
 */
@ServletComponentScan
@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.taomz.mini.apps")
@MapperScan("com.taomz.mini.apps.dao.mapper")
public class MiniAppsApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MiniAppsApplication.class, args);
    }

}
