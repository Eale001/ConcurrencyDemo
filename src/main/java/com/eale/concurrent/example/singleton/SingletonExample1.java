package com.eale.concurrent.example.singleton;

import com.eale.concurrent.annoations.NotThreadSafe;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //懒汉模式 单例实例在第一次使用时进行创建
 * @Version 1.0
 **/
@NotThreadSafe
public class SingletonExample1 {

    // 私有化构造方法
    private SingletonExample1(){

    }

    // 单利对象
    private static SingletonExample1 instance = null;

    // 静态的工厂方法
    public static SingletonExample1 getInstance(){
        if (null == instance){
            instance = new SingletonExample1();
        }
        return instance;
    }

}
