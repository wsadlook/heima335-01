package com.tensquare.user.config;

import com.tensquare.user.intercept.MyIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private MyIntercept myIntercept;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myIntercept)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }
}
