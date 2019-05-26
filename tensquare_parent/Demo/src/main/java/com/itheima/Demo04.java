package com.itheima;

public class Demo04 {
    static int x = 10;
        static{x+=5;}
    public static void main(String[] args) {
        System.out.println(x);
    }
    static{x/=3;}
}
