package com.eale.aop.service.impl;

import com.eale.aop.service.ExInterface;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
public class ExInterfaceImpl implements ExInterface {
    @Override
    public void execute() {
        System.out.println("执行A的execute方法...");
    }

    @Override
    public void execute2() {
        System.out.println("执行ExInterface的execute2--2--方法...");
    }
}
