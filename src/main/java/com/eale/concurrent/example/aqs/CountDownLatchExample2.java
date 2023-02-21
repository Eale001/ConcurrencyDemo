package com.eale.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.ClassWriter;

import java.util.concurrent.*;

/**
 * @Author Administrator
 * @Date 2020/4/20
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
public class CountDownLatchExample2 {

    private final static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("");
                thread.setDaemon(true);
                thread.setPriority(-1);
                return thread;
            }
        };

        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);
        for (int i =0 ; i< threadTotal; i++){
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception:",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        executorService.shutdown();


    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(9);
        log.info("{}",threadNum);
        Thread.sleep(9);

        ClassWriter classWriter = new ClassWriter(12);

    }


}
