package com.eale.concurrent.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Administrator
 * @Date 2020/4/20
 * @Description //TODO
 * @Version 1.0
 **/
public class CountDownLatchExample1 {

    private final static int threadTotal = 200;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);
        for (int i =0 ; i< threadTotal; i++){
            final int threadNum = i;
            executorService.execute(() -> {



            });


        }




    }


}
