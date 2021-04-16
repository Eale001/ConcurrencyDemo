package com.eale.test;

/**
 * @Author Administrator
 * @Date 2021/4/6
 * @Description // 单例安全
 * @Version 1.0
 **/
public class SingleSafe {

    private SingleSafe() {
    }

    public static SingleSafe getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingleSafe singleSafe;

        Singleton(){
            singleSafe = new SingleSafe();
        }

        public SingleSafe getInstance(){
            return singleSafe;
        }

    }

}
