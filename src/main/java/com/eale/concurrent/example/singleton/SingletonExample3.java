package com.eale.concurrent.example.singleton;

import com.eale.concurrent.annoations.NotRecommend;
import com.eale.concurrent.annoations.NotThreadSafe;
import com.eale.concurrent.annoations.ThreadSafe;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //懒汉模式 单例实例在第一次使用时进行创建
 * @Version 1.0
 **/
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    // 私有化构造方法
    private SingletonExample3(){

    }

    // 单利对象
    private static SingletonExample3 instance = null;

    // 静态的工厂方法
    public static synchronized SingletonExample3 getInstance(){
        if (null == instance){
            instance = new SingletonExample3();
        }
        return instance;
    }

}
