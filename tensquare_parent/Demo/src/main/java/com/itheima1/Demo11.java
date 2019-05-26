package com.itheima1;

import java.util.Arrays;

/**
 * @author YangZhen
 * @date 2019-05-15 12:19
 */
public class Demo11 {
    public static void main(String[] args) {
        int[] arr = {11, 123, 2};
        for (int a = 0; a < arr.length - 1; a++) {

            for (int b = 0; b < arr.length - 1 - a; b++) {
                if (arr[b] > arr[b + 1]) {
                    int temp = arr[b];
                    arr[b] = arr[b + 1];
                    arr[b + 1] = temp;
                }
            }
        }
        String s = Arrays.toString(arr);
        System.out.println(s);

        int a = 533435;
        int b = 4;
        int c = 453453;

        if(a<b){
            a = a^b;
            b = a^b;
            a = a^b;
        }
        if(a<c){
            a = a^c;
            c = a^c;
            a = a^c;
        }
        if(b<c){
            b = b^c;
            c = b^c;
            b = b^c;
        }

        int aa = 10;
        int bb = 20;

         aa = aa ^ bb;
         bb = aa ^ bb;
         aa = aa ^ bb;
        System.out.println(aa+","+bb);
        System.out.println(c +","+b+","+a);
    }

}
