package com.shuzhi.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebMvcConfig extends WebMvcConfigurerAdapter {
    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true).allowedMethods(ORIGINS);
    }
}
