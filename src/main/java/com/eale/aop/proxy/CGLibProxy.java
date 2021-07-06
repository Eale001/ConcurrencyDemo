package com.eale.aop.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
public class CGLibProxy implements MethodInterceptor {

    private A target;

    public CGLibProxy(A target) {
        this.target = target;
    }

    public A createProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (A) enhancer.create();

    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //过滤不需要该业务的方法
        if("execute".equals(method.getName())) {

            //调用目标对象的方法(执行A对象即被代理对象的execute方法)
            Object result = methodProxy.invokeSuper(proxy, args);

            return result;
        }else if("delete".equals(method.getName())){
            //.....
            return methodProxy.invokeSuper(proxy, args);
        }
        //如果不需要增强直接执行原方法
        return methodProxy.invokeSuper(proxy, args);
    }
}
