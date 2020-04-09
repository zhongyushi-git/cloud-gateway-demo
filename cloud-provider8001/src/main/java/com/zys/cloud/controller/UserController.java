package com.zys.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    private String port;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eureka;

    @GetMapping("/get")
    public String get(){
        return "server port is \t"+port+".\t\t\t\t time："+new Date();
    }

    @GetMapping("/get2")
    public String get2(){
        return "eureka server is \t"+eureka+".\t\t\t\t time："+new Date();
    }


}
