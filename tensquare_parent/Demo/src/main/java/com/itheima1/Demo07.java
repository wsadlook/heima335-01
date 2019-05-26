package com.itheima1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YangZhen
 * @date 2019-05-10 23:51
 */
public class Demo07 {
    public static void main(String[] args) {
        String str = "";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String s : str.split("")) {
            map.put(s, map.containsKey(s) ? map.get(s) + 1 : 1);
        }

        int a = 10;
        int b = 20;

        int temp = a;
        a = b;
        b = temp;
        System.out.println(a);
        System.out.println(b);


        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }
}
