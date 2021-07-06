package com.eale.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.eale.aop.service.UserDao.addUser(..))")
    public void myPointcut(){

    }

    @Before("execution(* com.eale.aop.service.UserDao.addUser(..))")
    public void before(){
        System.out.println("--------前置通知----------");
    }

    @AfterReturning(value = "execution(* com.eale.aop.service.UserDao.addUser(..))",returning = "returnVal")
    public void after(Object returnVal){
        System.out.println("--------后置通知-----------"+returnVal);
    }

    @Around("execution(* com.eale.aop.service.UserDao.addUser(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-----环绕通知前-----------");
        Object proceed = joinPoint.proceed();
        System.out.println("-----环绕通知后-----------");
        return proceed;
    }

    @AfterThrowing(value = "execution(* com.eale.aop.service.UserDao.addUser(..))",throwing = "e")
    public void throwException(Throwable e){
        System.out.println("-----出现异常：message = "+ e.getMessage());
    }

    @After(value = "myPointcut()")
    public void after(){
        System.out.println("----------最终通知-------");
    }

}
