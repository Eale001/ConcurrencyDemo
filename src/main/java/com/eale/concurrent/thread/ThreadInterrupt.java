package com.eale.concurrent.thread;

/**
 * @Author Administrator
 * @Date 2020/5/11
 * @Description // 要响应线程中断
 * @Version 1.0
 **/
public class ThreadInterrupt {

    Thread thread = new Thread("interrupt test"){
        public void run(){

            for (;;){
                // do something
                System.out.println(thread.getName()+getId());
                if (Thread.interrupted()){
                    break;
                }
            }
        }

    };


    public void foo() throws InterruptedException {
        if (Thread.interrupted()){
            throw new InterruptedException();
        }
    }

    Thread thread2 = new Thread("interrupt test 2"){
        public void run(){

            for (;;){
                try {
                    // do something
                    System.out.println(thread2.getName());
                    throw new InterruptedException();
                }catch (InterruptedException e){
                    break;
                }catch (Exception e){
                    // handle Exception
                }
            }
        }
    };



}
