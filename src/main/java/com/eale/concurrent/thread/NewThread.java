package com.eale.concurrent.thread;

import org.apache.tomcat.util.threads.TaskThread;

/**
 * @Author Administrator
 * @Date 2020/5/11
 * @Description //启动线程的注意事项
 * @Version 1.0
 **/
public class NewThread {

    // 无论何种方式,启动一个线程,就要给它一个名字! 这对排错诊断系统监控有帮助,否则诊断问题时,无法直观知道某个线程的用途
    private Thread thread = new Thread("thread Name"){
        public void run(){
            // do something
            System.out.println(thread.getName());
        }
    };


    class MyThread extends Thread{

        public MyThread(){
            super("thread name");
        }
        public void run(){
            // do something
            System.out.println();

        }
    }

    // 无论何种方式,启动一个线程,就要给它一个名字! 这对排错诊断系统监控有帮助,否则诊断问题时,无法直观知道某个线程的用途
    private Thread thread2 = new Thread(){
        public void run(){
            // do something
            System.out.println(thread2.getName());
        }
    };


//    Thread thread3 = new Thread(task); // 传入任务

//    Thread thread4 = new Thread(task,"thread Name 4"); // 传入任务,构造方法传入线程名称

    public void createThread() {

        // 1
        thread.start();

        // 2
        MyThread myThread = new MyThread();
        myThread.start();

        // 3
        thread2.setName("thread name 2");
        thread2.start();

        //4
//        thread3.setName("thread name 3");

        // 5
//        thread4.start();

    }



}
