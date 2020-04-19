package com.eale.concurrent.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
public class CountExample {


    private static int threadTotal = 1;
    private static int clientTotal = 5000;

    private static volatile long count = 0;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = new ScheduledThreadPoolExecutor(5);

        // 信号量
        final Semaphore semaphore = new Semaphore(1);
        for (int index = 0; index <clientTotal;index++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
        log.info("count:{}",count);

    }

    private static void add() {
        count++;
    }


}
