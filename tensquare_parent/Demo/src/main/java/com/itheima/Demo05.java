package com.itheima;

import java.io.PrintStream;

public class Demo05 {
    private static int x = 100;
    public static void main(String[] args) {
        Demo05 demo05 = new Demo05();
        demo05.x++;
        Demo05 demo051 = new Demo05();
        demo051.x++;
        System.out.println(x);
    }
}
