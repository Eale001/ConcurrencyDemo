package com.eale.concurrent.example.singleton;

import com.eale.concurrent.annoations.ThreadSafe;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //饿汉模式  单例实例在类装载使用时进行创建
 * @Version 1.0
 **/
@ThreadSafe
public class SingletonExample6 {

    // 私有化构造方法
    private SingletonExample6(){

    }

    // 单利对象
    private static SingletonExample6 instance = null;


    static {
        instance = new SingletonExample6();
    }

    // 静态的工厂方法
    public static SingletonExample6 getInstance(){
        return instance;
    }


    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());

    }

}
