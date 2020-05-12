package com.eale.concurrent.blockQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Administrator
 * @Date 2020/5/11
 * @Description //通过实现简单的阻塞队列来学习并发知识
 * @Version 1.0
 **/
public class BlockingQ1 {

    private Object notEmpty = new Object();

    private Queue<Object> linkedList = new LinkedList<>();

    // 未取得锁就直接执行wait,notify, notifyAll会抛异常

    public Object take() throws InterruptedException {

        synchronized (notEmpty){
            if (linkedList.size() == 0){
                // 要执行wait操作.必须先取得该对象的锁,
                // 执行wait操作后,锁会释放
                // 被唤醒之前,需要先活得锁
                notEmpty.wait();
            }
            return linkedList.poll();
        }
    }

    public void offer(Object object){
        synchronized (notEmpty){
            if (linkedList.size() ==0){
                // 要执行notify和notifyAll操作,都必须先取得该对象的锁
                notEmpty.notifyAll();
            }
            linkedList.add(object);
        }

    }


}
