package com.eale.concurrent.example.atomic;

import com.eale.concurrent.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5,100,120)){

            log.info("update success 1,{}",example5.getCount());
        }

        if (updater.compareAndSet(example5,100,200)){
            log.info("update success 2,{}",example5.getCount());
        }else {
            log.info("update failed ,{}",example5.getCount());
        }

    }


}
