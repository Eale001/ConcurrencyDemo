package com.eale.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author HLD
 * @Date 2021/7/12 0012
 **/
public class MyApplicationEvent extends ApplicationEvent {

    private String message;


    public MyApplicationEvent(Object source,final String message) {
        super(source);
        System.out.println("get a message from applicationEvent " + message);
    }


}
