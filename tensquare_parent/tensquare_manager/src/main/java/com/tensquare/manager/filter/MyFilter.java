package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

public class MyFilter extends ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {

        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        //获取请求中的头信息
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();


        //判断请求的方法是否为OPTIONS，这个请求方法是ZUUL网关使用的，不要拦截Zuul网关自己的调用
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }
        String url = request.getRequestURI().toUpperCase();
        if(url.indexOf("/admin/login")>0){
            System.out.println("登陆页面"+url);
            return null;
        }

        String header = request.getHeader("Authorization");


        if(header != null && !"".equals(header) && header.startsWith("Bearer ")){
            String token = header.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if(claims != null){
                String  roles = (String) claims.get("roles");
                if("admin".equals(roles)){

                    //把获取到的头部信息，设置到新的请求头中
                    requestContext
                            .addZuulRequestHeader("Authorization",header);
                    return null;
                }
            }
        }
        //设置网关不会进行路由，相当于拦截
        requestContext.setSendZuulResponse(false);
        //设置响应状态码
        requestContext.setResponseStatusCode(401);
        //设置响应提示信息
        requestContext.setResponseBody("用户不是管理员身份，不能访问后台");
        //设置响应数据类型以及编码
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");

        return null;
    }
}
