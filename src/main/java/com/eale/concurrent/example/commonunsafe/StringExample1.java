package com.eale.concurrent.example.commonunsafe;

import com.eale.concurrent.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

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
@NotThreadSafe
public class StringExample1 {

    // 请求总数
    public static int clintTotal  = 5000;
    // 同时并发执行的线程数
    public static int threadTotal  = 50;

    public static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clintTotal);
        for (int i = 0; i<clintTotal;i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();

                }catch (Exception e){
                    log.error("execption",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}",stringBuilder.length());

    }

    public static void update(){
        stringBuilder.append("1");
    }

}
