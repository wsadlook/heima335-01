package com.itheima;


public final class Demo07  {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        boolean flag = true;
        //true
        if(flag) flag = true;
        if(flag) flag = false;
        if(flag) flag = true;

        for(;;){
            if(!true) continue;
            if(!false) continue;
            if(!true) continue;
        }




    }
}
