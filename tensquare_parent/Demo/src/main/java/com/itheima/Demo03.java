package com.itheima;

public class Demo03 {
    public static void main(String[] args) {
        int b = 5;
        int a = test2(b);
        System.out.println(a);
    }

    public static int test2(int b){


        try {
            return b++;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(b);
        }finally {
            System.out.println(b);
            return ++b;
        }
    }
}
