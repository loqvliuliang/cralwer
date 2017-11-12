package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

import com.example.demo.utils.JsonResult;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * Created by 刘亮 on 2017/11/10.
 */
@Controller
@RequestMapping("/login")
public class loginController {
    private final UserService userService;

    public loginController(UserService userService){
        this.userService = userService;
    }


    /**
     * 邮箱认证接口
     * @param mail
     * @return
     */
    @PostMapping(value = "/authMail")
    @ResponseBody
    public JsonResult authMail(@RequestParam("mail") String mail)  {
        return  new JsonResult(userService.AuthMail(mail));
    }

//    /**
//     * 发送邮件验证码
//     * @param code
//     * @param mail
//     * @return
//     */
//    @GetMapping("/authMail/sendAuth")
//    public ResponseEntity<Boolean> authMailSendAuth(@RequestParam("code") String code,@RequestParam("mail") String mail){
//        return ResponseEntity.ok(userService.AuthMailCode(mail,code));
//    }


    @PostMapping("/insertOrUpdate")
    public ResponseEntity<User> insertOrUpdateUser(@RequestBody @NotNull User user){
        return ResponseEntity.ok(userService.insertOrUpdateUser(user));
    }


    @GetMapping("/123")
    public String a(){
        return "123";
    }

}
