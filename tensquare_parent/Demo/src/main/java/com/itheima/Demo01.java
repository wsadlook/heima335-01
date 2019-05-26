package com.itheima;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Demo01 {
    List<Demo01> list = new ArrayList<Demo01>();


    public Demo01(List<Demo01> list) {
        this.list = list;
    }

    public Demo01(int a, int b){}
    public Demo01(){
        this(1,2);
    }



    public static void main(String[] args){

        int a =10;
        int b = 20;
        a = a^b; //  a ^ b
        b = a^b;//  a ^ b ^ b

        System.out.println( a );
        System.out.println( b);
        System.out.println("----------------");
























        int c = 10;
        c =c++;
        System.out.println(c);
        Map<String,String> map = new HashMap<String, String>();
        Set<Map.Entry<String, String>> entries = map.entrySet();

        Iterator<Map.Entry<String, String>> iterator = entries.iterator();

        ConcurrentHashMap<String,String>  concurrentHashMap = new ConcurrentHashMap();

        new ThreadLocal<String>();


        /**
         * 1111
         *     0     1    2    3    4    5    6    7   13
         *    {22=, 23=, 24=, 25=, 26=, 27=, 28=, 29=, 30=,
         *
         *    14   15   15    0    0    1    1    2    2
         *    31=, 10=, 32=, 11=, 33=, 12=, 34=, 13=, 35=,
         *
         *     3    3    4    4    5    5    6    7    8
         *    14=, 36=, 15=, 37=, 16=, 38=, 17=, 18=, 19=,
         *
         *    0   1   2   3   4   5   6   7   8   9   14   15
         *    0=, 1=, 2=, 3=, 4=, 5=, 6=, 7=, 8=, 9=, 20=, 21=}
         */
   /*     String a22 = "33";
        int i = a22.hashCode();
        System.out.println(i);
        System.out.println(31&i);*/

        // 22 == 1600
        //0 == 48
        //11 = 1568
        //33 == 1632


        //1001
        System.out.println(30&10);
        //1001 1100 1110 0111 1001


        System.out.println("______________________________");


        int n = 9;
        n |= n >>>1;
        System.out.println(n);

        n |= n >>>2;
        System.out.println(n);

        n |= n >>>4;
        System.out.println(n);

        n |= n >>>8;
        System.out.println(n);

        n |= n >>>16;
        System.out.println(n);




    }

    /**
     * 这是个方法，有用的方法
     * @param str   这是个字符串
     * @param aa    这是个Num
     * @return      返回是游泳东西
     */
    public String  hehe(String str,int aa){
        return null;
    }
}
