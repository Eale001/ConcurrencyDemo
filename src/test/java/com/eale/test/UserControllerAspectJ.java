package com.eale.test;

import com.eale.aop.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-aspect.xml")
public class UserControllerAspectJ {


    @Autowired
    UserController userController;


    @Test
    public void addUser(){
        userController.addUser();
    }

}
