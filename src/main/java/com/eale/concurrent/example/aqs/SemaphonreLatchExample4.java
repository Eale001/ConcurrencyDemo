package com.eale.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * @Date 2020/4/20
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
public class SemaphonreLatchExample4 {

    private final static int threadTotal = 20;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i =0 ; i< threadTotal; i++){
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){  // 尝试获取一个许可
                        test(threadNum);
                        semaphore.release(3); // 释放一个许可
                    }
                } catch (InterruptedException e) {
                    log.error("exception:",e);
                }
            });
        }
        executorService.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }


}
