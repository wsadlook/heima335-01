package com.itheima1;

import java.util.Arrays;

/**
 * @author YangZhen
 * @date 2019-05-07 16:23
 */
public class Demo04 {
    public static void main(String[] args) {
        int[] arr = {8,1,5,9,10,4,6,3,7,2};
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }
    public static void sort(int[] arr){
        for(int a=0;a<arr.length - 1;a++){
            for(int b=0;b<arr.length -1 -a;b++){
                if(arr[b] > arr[b+1]){
                    int temp;
                    temp = arr[b];
                    arr[b] = arr[b+1];
                    arr[b+1] = temp;
                }
            }
        }
    }
}
