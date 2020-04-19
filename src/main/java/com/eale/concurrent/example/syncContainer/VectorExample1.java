package com.eale.concurrent.example.syncContainer;

import com.eale.concurrent.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class VectorExample1 {


    // 请求总数
    public static int clintTotal  = 5000;
    // 同时并发执行的线程数
    public static int threadTotal  = 50;

    private static Vector<Integer> list = new Vector<>();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clintTotal);
        for (int i = 0; i<clintTotal;i++){
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();

                }catch (Exception e){
                    log.error("execption",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("size :{}",list.size());
        executorService.shutdown();

    }

    public static void add(int i){
        list.add(i);
    }

}
