package com.tensquare.friend.config;

import com.tensquare.friend.intercept.MyIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private MyIntercept myIntercept;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myIntercept)
                .addPathPatterns("/**")//所有请求都被拦截
                .excludePathPatterns("/**/login");//登陆请求不拦截
    }
}
