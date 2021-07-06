package com.eale.aop;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
public class HelloWorld {

    public void sayHello(){
        System.out.println("hello world!");
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }

}
