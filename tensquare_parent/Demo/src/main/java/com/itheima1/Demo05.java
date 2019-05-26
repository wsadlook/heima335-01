package com.itheima1;

import java.util.HashMap;

/**
 * @author YangZhen
 * @date 2019-05-08 17:01
 */
public class Demo05 {
    public static void main(String[] args) {
        String str ="yangzhenyulingweiyuanyunfeng";
        int sub = 0;
        String i = "";
        HashMap<String,Integer> map = new HashMap<>();
        for (String s : str.split("")) {
            map.put(s,map.containsKey(s)?map.get(s)+1:1);
            if(map.get(s)>sub){
                sub = map.get(s);
                i = s;
            }
        }

        System.out.println(i+"出现的次数最多，一共出现了"+sub+"次");
        System.out.println(map);
    }
}
