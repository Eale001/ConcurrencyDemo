package com.eale.aop.proxy;

import com.eale.aop.service.ExInterface;
import com.eale.aop.service.impl.ExInterfaceImpl;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
public class ProxyTest {

    public static void main(String[] args) {
        ExInterfaceImpl a = new ExInterfaceImpl();
        //创建JDK代理
        JDKProxy jdkProxy=new JDKProxy(a);
        //创建代理对象
        ExInterface proxy=jdkProxy.createProxy();
        //执行代理对象方法
        proxy.execute();
        proxy.execute2();

        A a1 = new A();
        CGLibProxy cgLibProxy = new CGLibProxy(a1);
        A proxy1 = cgLibProxy.createProxy();
        proxy1.execute();
    }


}
