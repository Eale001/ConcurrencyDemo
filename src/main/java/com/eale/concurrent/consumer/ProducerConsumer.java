package com.eale.concurrent.consumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Admin
 * @Date 2020/7/6
 * @Description
 * @Version 1.0
 **/
public class ProducerConsumer {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize =5;

        new Thread(new Producer(queue,maxSize ),"producer1").start();
        new Thread(new Consumer(queue,maxSize ),"consumer1").start();
        new Thread(new Consumer(queue,maxSize ),"consumer2").start();
    }



}
