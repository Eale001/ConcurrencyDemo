package com.eale.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleSafeTest {

    public SingleSafe singleSafe;

    @Before
    public void before(){
        singleSafe = SingleSafe.getInstance();
    }


    @Test
    public void test(){
        for (int i = 0; i <10; i++) {
            SingleSafe singleSafe1 = SingleSafe.getInstance();
            Assert.assertEquals(singleSafe,singleSafe1);
        }
    }

    @Test
    public void test1(){
        ExecutorService executors = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executors.execute(() -> {
                SingleSafe singleSafe2 = SingleSafe.getInstance();
                Assert.assertEquals(singleSafe,singleSafe2);
            });
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SingleSafe singleSafe3 = SingleSafe.getInstance();
                    Assert.assertEquals(singleSafe,singleSafe3);
                }
            }).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}