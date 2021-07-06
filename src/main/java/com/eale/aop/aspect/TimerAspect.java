package com.eale.aop.aspect;

import com.eale.aop.MonitorTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
@Aspect
public class TimerAspect {


    @Pointcut("execution(* com.eale.aop.controller.*(..))")
    public void timer(){
    }

    @Around(value = "timer()")
    public Object logTimer(ProceedingJoinPoint thisJoinPoint) throws Throwable {

        MonitorTime monitorTime=new MonitorTime();
        //获取目标类名称
        String clazzName = thisJoinPoint.getTarget().getClass().getName();
        //获取目标类方法名称
        String methodName = thisJoinPoint.getSignature().getName();

        //记录类名称
        monitorTime.setClassName(clazzName);
        //记录对应方法名称
        monitorTime.setMethodName(methodName);
        //记录时间
        monitorTime.setLogTime(new Date());

        // 计时并调用目标函数
        long start = System.currentTimeMillis();
        Object result = thisJoinPoint.proceed();
        long time = System.currentTimeMillis() - start;

        //设置消耗时间
        monitorTime.setConsumeTime(time);

        return result;
    }

}
