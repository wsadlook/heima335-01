package com.itheima;

import java.util.HashMap;
import java.util.Map;

public class Demo02 {
    public static void main(String[] args) {
        String str = "电脑:1|B:2|C:6|自行车:1";
        Map<String, String> map = parseString(str);
        System.out.println(map);

    }

    public static Map<String,String> parseString(String str){
        Map<String,String> map = new HashMap<String, String>();

        String[] split = str.split("\\|");


        for(int a =0;a<split.length;a++){
            String[] result = split[a].split(":");
            if(Integer.parseInt(result[1])>5){
                map.put(result[0],result[1]);
            }
        }
        return map;
    }
}
