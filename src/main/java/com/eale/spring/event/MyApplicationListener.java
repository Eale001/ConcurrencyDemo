package com.eale.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听事件
 * @Author HLD
 * @Date 2021/7/12 0012
 **/
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {



    @Override
    public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {
        // hand event

    }

}
