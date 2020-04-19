package com.eale.concurrent.example.singleton;

import com.eale.concurrent.annoations.NotThreadSafe;
import com.eale.concurrent.annoations.ThreadSafe;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //饿汉模式  单例实例在类装载使用时进行创建
 * @Version 1.0
 **/
@ThreadSafe
public class SingletonExample2 {

    // 私有化构造方法
    private SingletonExample2(){

    }

    // 单利对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态的工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }

}
