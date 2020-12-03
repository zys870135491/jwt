package com.zys.jwt.intercept;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * addInterceptor：需要一个实现HandlerInterceptor接口的拦截器实例
         * addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
         * excludePathPatterns：用于设置不需要拦截的过滤规则
         */
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/sysuser/login");

    }

}
