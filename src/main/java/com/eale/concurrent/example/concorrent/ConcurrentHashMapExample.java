package com.eale.concurrent.example.concorrent;

import com.eale.concurrent.annoations.NotThreadSafe;
import com.eale.concurrent.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class ConcurrentHashMapExample {

    private static Map<Integer,Integer> map = new ConcurrentHashMap<>();

    // 请求总数
    public static int clintTotal  = 5000;
    // 同时并发执行的线程数
    public static int threadTotal  = 50;

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
        executorService.shutdown();
        log.info("size :{}", map.size());

    }

    public static void add(int i){
        map.put(i,i);
    }

}
