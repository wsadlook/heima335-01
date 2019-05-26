package com.itheima;

import java.util.Scanner;

/**
 * @author YangZhen
 * @date 2019-05-01 16:40
 */
public class Demo13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入您的手机号");
        while (true) {
            String next = sc.next();

            if (next == null || "".equals(next.trim())) {
                System.out.println("手机号不能为空");
                continue;
            }

            if (next.length() > 11 || next.length() < 11) {
                System.out.println("请您输入正确的手机号");
                continue;
            }
            String start = next.substring(0,3);
            System.out.println(start);
            String end = next.substring(7,next.length());
            String iphone = start+"****"+end;
            System.out.println(iphone);
            System.out.println("请输入您的手机号");
        }
    }
}
