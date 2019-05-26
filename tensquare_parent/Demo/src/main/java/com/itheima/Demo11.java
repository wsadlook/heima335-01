package com.itheima;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

public class Demo11 {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        if (a == b) {

        }
        String str = "";

        // 标准
        //不标准
        URLDecoder.decode(Integer.valueOf(a).toString());

        String[] arrr = {};
        List<String> strings = Arrays.asList(arrr);
    }
}
