package com.itheima1;

import com.sun.org.apache.xml.internal.security.utils.SignerOutputStream;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import java.util.Scanner;

/**
 * @author YangZhen
 * @date 2019-05-07 00:05
 */
public class Demo01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i){
            case 1:
                System.out.println("is 1");
                break;
            case 2:
                System.out.println("is 2");
                break;
            case 3:
                System.out.println("is 3");
                break;

        }
    }
    public static void test01(){
        Scanner sc = new Scanner(System.in);
        while(true){
            String next = sc.next();
            switch (next){
                case "":
                    System.out.println("en");
                    break;
                case " ":
                    System.out.println("a");

            }
        }
    }
}
