package com.eale.concurrent.consumer;

import java.util.Queue;
import java.util.Random;

/**
 * @Author Admin
 * @Date 2020/7/6
 * @Description
 * @Version 1.0
 **/
public class Producer implements Runnable {

    private Queue<Integer> queue;

    private int maxSize;

    public Producer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {

        while (true){
            synchronized (queue){
                while (queue.size() == maxSize){
                    try {
                        System.out.println(" queue 已经满了，请等会儿再装");
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Random random = new Random();
                int value = random.nextInt();
                System.out.println(Thread.currentThread().getName() + "producer name : "+ value);
                queue.add(value);
                queue.notifyAll();
            }
        }

    }
}
