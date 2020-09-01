package com.eale.concurrent.example.join;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Admin
 * @Date 2020/9/1
 * @Description
 * @Version 1.0
 **/
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {

//        ExecutorService executors = Executors.newFixedThreadPool(3);

        Employee employee = new Employee("商户1 ", 3000);
        Employee employee2 = new Employee("商户2 ", 3000);
        Employee employee3 = new Employee("商户3 ", 3000);
        employee2.start();
        employee3.start();
        employee2.join();
        employee3.join();
        System.out.println("商户2,3 准备完成");
        employee.start();


    }
}


class Employee extends Thread{

    private String employeeName;

    private long time;


    public Employee(String employeeName,long time){
        this.employeeName = employeeName;
        this.time = time;
    }

    @Override
    public void run() {
        try {
            System.out.println(employeeName+ "开始准备");
            Thread.sleep(time);
            System.out.println(employeeName+" 准备完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}