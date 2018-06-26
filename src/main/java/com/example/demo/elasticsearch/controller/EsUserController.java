//package com.example.demo.elasticsearch.controller;
//
//import com.example.demo.elasticsearch.service.EsUserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by liang.liu04@hand-china.com
// * on 2018/6/22
// */
//@RestController
//@RequestMapping("/es/user")
//public class EsUserController {
//
//    @Autowired
//    private EsUserServiceImpl esUserService;
//
//    @GetMapping("/init")
//    public Boolean initEsUser(){
//        Boolean aBoolean = esUserService.initUserToEs();
//        return aBoolean;
//    }
//
//}
