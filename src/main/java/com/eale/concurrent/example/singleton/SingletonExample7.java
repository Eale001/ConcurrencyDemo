package com.eale.concurrent.example.singleton;

import com.eale.concurrent.annoations.Recommend;
import com.eale.concurrent.annoations.ThreadSafe;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //枚举模式 --> 最安全的
 * @Version 1.0
 **/
@ThreadSafe
@Recommend
public class   SingletonExample7 {

    // 私有构造函数
    private SingletonExample7(){
    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;
        private SingletonExample7 singleton;

        // JVM 会禁止发生指令重排的
        Singleton(){
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getInstance(){
            return singleton;
        }


    }

}
