package com.eale.jvm;

/**
 *
 * 对象是否分配在栈上
 * @Author HLD
 * @Date 2021/11/17 0017
 **/
public class OnStackTest {

    public static class User{
        private int id=0;
        private String name="";
    }

    public static void alloc(){
        User user = new User();
        user.id = 5;
        user.name = "gemy";
    }

    public static void main(String[] args) {

        // -server -XX:+DoEscapeAnalysis
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            alloc();
        }
        long now = System.currentTimeMillis();
        System.out.println(now - currentTimeMillis);


    }


}
