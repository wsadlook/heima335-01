package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //返回pre表示在进行微服务路由(转发,调用)之前,执行过滤器
        return "pre";
        //pre ：可以在请求被路由之前调用
        //route ：在路由请求时候被调用
        //post ：在route和error过滤器之后被调用
        //error ：处理请求时发生错误时被调用
    }

    @Override
    public int filterOrder() {
        //返回int型,设置过滤器的执行优先级
        //如果为0,表示当前的过滤器是最先执行的
        //数字越大,优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //当前网关是否执行当前的过滤器
        //返回true表示使用当前过滤器,false表示不使用
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("Zuul过滤器执行了，啊哈哈哈");
        RequestContext
                .getCurrentContext()
                .addZuulRequestHeader("Authorization",

                        RequestContext
                                .getCurrentContext()
                                .getRequest()
                                .getHeader("Authorization"));
        return null;
    }
}
