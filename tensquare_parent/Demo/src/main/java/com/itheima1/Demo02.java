package com.itheima1;

import java.util.*;

/**
 * @author YangZhen
 * @date 2019-05-07 13:21
 */
public class Demo02 {
    public static void main(String[] args) {
        int sub = 0;
        String i = "";

        String str = "yangzhenyuanyunfengyulingwei";
        HashMap<String, Integer> map = new HashMap<>();

        for (String s : str.split("")) {
            map.put(s, map.containsKey(s) ? map.get(s) + 1 : 1);
            if (map.get(s) > sub) {
                i = s;
                sub=map.get(s);
            }
//            sub = map.get(s) > sub ? map.get(s) : sub;

        }
        System.out.println(i + "_____" + sub);
        System.out.println(map);


    }
}
