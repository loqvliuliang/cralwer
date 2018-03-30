package com.example.demo.controller;

import com.example.demo.feign.HelloFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘亮 on 2018/3/28.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private HelloFeign helloFeign;
    @GetMapping("/to/hello")
    public String hello(@RequestParam String name){
        return helloFeign.getHello(name);
    }

}
