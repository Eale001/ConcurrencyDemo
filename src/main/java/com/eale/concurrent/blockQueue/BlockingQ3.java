package com.eale.concurrent.blockQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Administrator
 * @Date 2020/5/11
 * @Description //通过实现简单的阻塞队列来学习并发知识
 * @Version 1.0
 **/
public class BlockingQ3 {

//    final BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(10);

    // 一个锁可以创建多个condition
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notNull = lock.newCondition();

    private Queue<Object> linkedList = new LinkedList<>();

    private int maxLength = 10;


    // 未取得锁就直接执行await,signal, signalAll会抛异常

    public Object take() throws InterruptedException {

        // 要执行await操作,必须先取得condition的锁
        // 执行await操作之后,锁会释放
        // 被唤醒之前,需要先获得锁
        lock.lock();
        try {
            if (linkedList.size() == 0){
                notEmpty.await();
            }
            if (linkedList.size() == maxLength){
                notNull.signalAll();
            }
            return linkedList.poll();
        }finally {
            lock.unlock();
        }
    }

    public void offer(Object object) throws InterruptedException {
        // 要执行 signal和signalAll操作,都必须先取得该对象的锁
        lock.lock();
        try {
            if (linkedList.size() == 0){
                notEmpty.signalAll();
            }
            if (linkedList.size() == maxLength){
                notNull.await();
            }
            linkedList.add(object);
        }finally {
            lock.unlock();
        }

    }


}
