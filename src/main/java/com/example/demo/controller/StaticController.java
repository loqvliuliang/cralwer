package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 刘亮 on 2017/11/12.
 */
@Controller
@RequestMapping("/static")
public class StaticController {

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

}
