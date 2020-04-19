package com.eale.concurrent.example.atomic;

import com.eale.concurrent.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class AtomicExample6 {


    public static AtomicBoolean isHappened = new AtomicBoolean();

    // 请求总数
    public static int clintTotal  = 5000;
    // 同时并发执行的线程数
    public static int threadTotal  = 50;


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clintTotal);
        for (int i = 0; i< clintTotal;i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();

                }catch (Exception e){
                    log.error("execption",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappened:{}",isHappened.get());
    }

    private static void test() {
        if (isHappened.compareAndSet(false,true)){
            log.info("executed");
        }

    }


}
