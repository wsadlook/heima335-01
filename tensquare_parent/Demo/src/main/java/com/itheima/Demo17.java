package com.itheima;

/**
 * @author YangZhen
 * @date 2019-05-05 15:48
 */
public class Demo17 {
    private static int a = 0;
    public    synchronized  void add(){
        ++a;
    }
    public  synchronized  void dec(){
        --a;
    }
    public static void main(String[] args) {

        for(int a=0;a<2;a++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Demo17().add();
                }
            }).start();
        }
        System.out.println(a);
        for(int a=0;a<2;a++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Demo17().dec();
                }
            }).start();
        }
        System.out.println(a);

    }
}
