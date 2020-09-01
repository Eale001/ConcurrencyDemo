package com.eale.concurrent.example.count;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Admin
 * @Date 2020/9/1
 * @Description
 * @Version 1.0
 **/
public class CountdownLatchTest1 {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            pool.execute(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName()+" thread counts :"+ latch.getCount());
                    latch.await();
                    System.out.println(Thread.currentThread().getName() + " concurrency counts = "+ (100 - latch.getCount()));

                }
            });
        }
        pool.shutdown();
    }
}

class CountRunnable implements Runnable {
    private CountDownLatch countDownLatch;
    public CountRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        try {
            /*** 每次减少一个容量*/
            countDownLatch.countDown();
            System.out.println("thread counts = " + (countDownLatch.getCount()));
            countDownLatch.await();
            System.out.println("concurrency counts = " + (100 - countDownLatch.getCount()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
