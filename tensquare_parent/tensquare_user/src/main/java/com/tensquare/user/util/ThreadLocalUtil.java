package com.tensquare.user.util;

import io.jsonwebtoken.Claims;

public class ThreadLocalUtil {
    private static ThreadLocal<Claims> threadLocal = new ThreadLocal<Claims>();

    public static void set(Claims claims){
        threadLocal.set(claims);
    }

    public static Claims get(){
        Claims claims = threadLocal.get();
        return claims;
    }

    public static void clear(){
        threadLocal.remove();
    }
}
