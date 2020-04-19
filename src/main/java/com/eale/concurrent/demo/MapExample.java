package com.eale.concurrent.demo;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
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
public class MapExample {

    private static Map<Integer,Integer> map = Maps.newHashMap();

    private static int threadNum = 1;
    private static int clientNum = 5000;


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量
        final Semaphore semaphore = new Semaphore(threadNum);
        for (int index = 0; index <clientNum;index++){
            final int threadNum = index;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    func(threadNum);
                    semaphore.release();

                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
        log.info("size:{}",map.size());

    }

    private static void func(int threadNum) {
        map.put(threadNum,threadNum);
    }


}
