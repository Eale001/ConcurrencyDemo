package com.eale.concurrent.controller;

import com.eale.concurrent.example.threadLocal.RequestHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Administrator
 * @Date 2020/4/19
 * @Description //TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @GetMapping("/test")
    public Long test(){
        return RequestHolder.getId();
    }

}
