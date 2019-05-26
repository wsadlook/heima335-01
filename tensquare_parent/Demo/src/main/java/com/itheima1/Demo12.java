package com.itheima1;

/**
 * @author YangZhen
 * @date 2019-05-15 23:03
 * version 1.0
 */
public class Demo12 {
    public static void main(String[] args) {
        String str = "yangzhen杨振";
        String intern = str.intern();
        System.out.println(str);
        System.out.println(intern);
    }
    }
