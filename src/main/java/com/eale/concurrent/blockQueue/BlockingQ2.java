package com.eale.concurrent.blockQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author Administrator
 * @Date 2020/5/11
 * @Description //通过实现简单的阻塞队列来学习并发知识
 * @Version 1.0
 **/
public class BlockingQ2 {

    final BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(10);

    private Object notEmpty = new Object();

    private Object notNull = new Object();

    private Queue<Object> linkedList = new LinkedList<>();

    private int maxLength = 10;


    // 未取得锁就直接执行wait,notify, notifyAll会抛异常

    public Object take() throws InterruptedException {

        // 分别需要对notEmpty notNull 加锁
        synchronized (notEmpty){
            if (linkedList.size() == 0){
                notEmpty.wait();
            }
            synchronized (notNull){
                if (linkedList.size() == maxLength){
                    notNull.notifyAll();
                }

                return linkedList.poll();
            }
        }


    }

    public void offer(Object object) throws InterruptedException {
        synchronized (notEmpty){
            if (linkedList.size() ==0){
                // 要执行notify和notifyAll操作,都必须先取得该对象的锁
                notEmpty.notifyAll();
            }
            synchronized (notNull){
                if (linkedList.size() == maxLength){
                    notNull.wait();
                }
                linkedList.add(object);
            }

        }

    }


}
