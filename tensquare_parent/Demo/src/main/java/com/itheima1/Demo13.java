package com.itheima1;

import java.util.*;

/**
 * @author YangZhen
 * @date 2019-05-23 12:40
 */
public class Demo13 {
    public static void main(String[] args) {
        List  list = new ArrayList();
        list.add("11");
        list.add("22");
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");

        Set set = new HashSet();
        for(int a =0;a<list.size();a++){
            System.out.println(list.get(a));
            set.add(list.get(a));
        }





    }
}
