package com.eale.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
@RestController
public class UserController {

    @GetMapping("/addUser")
    public void addUser(){
        System.out.println("--------添加用户-----");
    }


}
