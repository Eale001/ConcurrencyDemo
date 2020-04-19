package com.eale.concurrent.example.singleton;

import com.eale.concurrent.annoations.ThreadSafe;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //懒汉模式  -> 双重检测机制  单例实例在第一次使用时进行创建
 * @Version 1.0
 **/
@ThreadSafe
public class SingletonExample5 {

    // 私有化构造方法
    private SingletonExample5(){

    }

    // 1, memory  = allocate() 分配对象的内存空间
    // 2, ctorInstance() 初始化对象
    // 3, instance= memory 设置instance指向刚分配的内存

    // 单利对象  volatile + 双重检测机智  -> 禁止指令重排
    private volatile static SingletonExample5 instance = null;

    // 静态的工厂方法
    public static SingletonExample5 getInstance(){
        if (null == instance){ // 双重检测机制  // B
            synchronized (SingletonExample5.class){ // 同步锁
                if (null == instance){
                    instance = new SingletonExample5(); // A-3
                }
            }
        }
        return instance;
    }

}
