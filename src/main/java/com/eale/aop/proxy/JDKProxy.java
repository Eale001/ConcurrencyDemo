package com.eale.aop.proxy;

import com.eale.aop.service.ExInterface;
import com.eale.aop.service.impl.ExInterfaceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
public class JDKProxy implements InvocationHandler {


    /**
     * 要被代理的对象
     */
    private ExInterfaceImpl target;

    public JDKProxy(ExInterfaceImpl target) {
        this.target = target;
    }


    public ExInterface createProxy(){
        return (ExInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("execute".equals(method.getName())){
            // 调用目标方法
            Object result = method.invoke(target, args);
            return result;
        }
        //如果不需要增强直接执行原方法
        return method.invoke(target,args);
    }
}
