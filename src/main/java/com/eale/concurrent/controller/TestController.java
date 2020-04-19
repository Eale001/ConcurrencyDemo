package com.eale.concurrent.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@RestController
@Slf4j
public class TestController {


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test";
    }

}
