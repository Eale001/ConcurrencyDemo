package com.eale.concurrent.unsafe;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * -XX:+UseBiasedLocking
 * -XX:BiasedLockingStartupDelay=0
 *
 * @Author HLD
 * @Date 2021/12/14 0014
 **/
public class Biased {


    public static List<Integer> numberList = new Vector<>();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int count = 0;
        int startNum = 0;
        while (count < 10000000){
            numberList.add(startNum);
            startNum +=2;
            count++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time : " + (endTime - startTime));

        Map<String,Object> map = new ConcurrentHashMap<>();
    }

}
