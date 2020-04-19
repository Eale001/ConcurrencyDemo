package com.eale.concurrent.example.singleton;

import com.eale.concurrent.annoations.NotThreadSafe;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //懒汉模式  -> 双重检测机制  单例实例在第一次使用时进行创建
 * @Version 1.0
 **/
@NotThreadSafe
public class SingletonExample4 {

    // 私有化构造方法
    private SingletonExample4(){

    }

    // 1, memory  = allocate() 分配对象的内存空间
    // 2, ctorInstance() 初始化对象
    // 3, instance= memory 设置instance指向刚分配的内存

    // JVM优化和CPU优化,发生了指令重排

    // 1, memory  = allocate() 分配对象的内存空间
    // 3, instance= memory 设置instance指向刚分配的内存
    // 2, ctorInstance() 初始化对象


    // 单利对象
    private static SingletonExample4 instance = null;

    // 静态的工厂方法
    public static SingletonExample4 getInstance(){
        if (null == instance){ // 双重检测机制  // B
            synchronized (SingletonExample4.class){ // 同步锁
                if (null == instance){
                    instance = new SingletonExample4(); // A-3
                }
            }
        }
        return instance;
    }

}
