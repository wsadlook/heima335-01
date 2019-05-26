package com.itheima;

import java.util.Scanner;

/**
 * @author YangZhen
 * @date 2019-05-05 16:28
 */
public class Demo20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            double a = sc.nextDouble();
        String s = a+"";
        if(s.contains("\\.")){
            System.out.println(" 89158165  ");
        }
        String[] split = s.split("\\.");
        if(split[1].length() >2){
            split[1]  = split[1].substring(0, 2);
        }
        a = Double.valueOf((split[0] +"."+split[1]));
        System.out.println(a);
        }

    }
}
