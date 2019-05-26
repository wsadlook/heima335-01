package com.itheima1;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author YangZhen
 * @date 2019-05-09 01:36
 */
public class Demo6 {
    public static void main(String[] args) {
        int[] arr = {8,1,6,4,2,10,5,3,7,9};
        for(int a=0;a<arr.length -1;a++){
            for(int b =0;b<arr.length -1 -a;b++){
                if(arr[b] > arr[b+1]){
                    int temp =arr[b];
                    arr[b] = arr[b+1];
                    arr[b+1] = temp;
                }
            }
        }
        String s = Arrays.toString(arr);
        System.out.println(s);
    }
}
