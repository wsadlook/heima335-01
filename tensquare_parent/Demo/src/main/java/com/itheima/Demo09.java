package com.itheima;

import java.util.ArrayList;

public class Demo09 {
    public static void main(String[] args) {
        Demo08 demo08 = new Demo08();

        int[][] result =new int [3][3];

        result[0][0] =003;
        result[0][1] =002;
        result[0][2] =001;

        result[1][0] =033;
        result[1][1] =022;
        result[1][2] =011;

        result[2][0] =333;
        result[2][1] =222;
        result[2][2] =111;
        System.out.println(result[2][0]);
        ArrayList<Integer> integers = demo08.printMatrix(result);
        System.out.println(integers);


    }
}
