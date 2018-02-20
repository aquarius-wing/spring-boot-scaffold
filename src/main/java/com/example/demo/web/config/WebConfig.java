package com.example.demo.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    /**
     * 配置访问静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/demo/uploadImg/**").addResourceLocations("classpath:/uploadImg/");
        super.addResourceHandlers(registry);
    }

    /**
     * 配置跨域访问,允许所有来源所有请求头所有方法
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
            .allowedHeaders("*")
            .allowedMethods("*")
            .allowedOrigins("*");
    }

}
