package com.eale.concurrent.consumer;

import java.util.Queue;

/**
 * @Author Admin
 * @Date 2020/7/6
 * @Description
 * @Version 1.0
 **/
public class Consumer implements Runnable {

    private Queue<Integer> queue;

    private int maxSize;

    public Consumer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true){
            synchronized (queue){
                while (queue.isEmpty()){
                    try {
                        System.out.println(" queue 当前空了，请等会儿再来拿 ");
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
                System.out.println(Thread.currentThread().getName() + " consumer value : "+ queue.remove());
                queue.notifyAll();
            }

        }


    }
}
