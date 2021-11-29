package com.eale.jvm;

/**
 * @Author HLD
 * @Date 2021/11/17 0017
 **/
public class HeapAlloc {

    public static void main(String[] args) {

        System.out.print("maxMemory = ");
        System.out.println(Runtime.getRuntime().maxMemory() + " bytes");
        System.out.print("freeMemory = ");
        System.out.println(Runtime.getRuntime().freeMemory() + " bytes");
        System.out.print("totalMemory = ");
        System.out.println(Runtime.getRuntime().totalMemory() + " bytes");

        byte[] bytes = new byte[1 * 1024*1024];
        System.out.println("分配了 1 M 空间给数组 ");

        System.out.print("maxMemory = ");
        System.out.println(Runtime.getRuntime().maxMemory() + " bytes");
        System.out.print("freeMemory = ");
        System.out.println(Runtime.getRuntime().freeMemory() + " bytes");
        System.out.print("totalMemory = ");
        System.out.println(Runtime.getRuntime().totalMemory() + " bytes");

        byte[] bytes4 = new byte[4 * 1024*1024];
        System.out.println("分配了 4 M 空间给数组 ");

        System.out.print("maxMemory = ");
        System.out.println(Runtime.getRuntime().maxMemory() + " bytes");
        System.out.print("freeMemory = ");
        System.out.println(Runtime.getRuntime().freeMemory() + " bytes");
        System.out.print("totalMemory = ");
        System.out.println(Runtime.getRuntime().totalMemory() + " bytes");

    }



}
