package com.itheima;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Demo10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("您要从哪里出发?");
        String next = sc.next();
        System.out.println("您要去哪里？");
        String next1 = sc.next();
        String[] str = new String[20];

        str[0]  = "北京";
        List<String> strings = Arrays.asList(str);
        boolean contains = strings.contains(next);
    }
}
