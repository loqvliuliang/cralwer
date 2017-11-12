package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 刘亮 on 2017/11/10.
 */
@RestController
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
    public ResponseEntity<String> authMail(@RequestParam("mail") String mail){
        return ResponseEntity.ok(userService.AuthMail(mail));
    }

    /**
     * 发送邮件验证码
     * @param code
     * @param mail
     * @return
     */
    @GetMapping("/authMail/sendAuth")
    public ResponseEntity<Boolean> authMailSendAuth(@RequestParam("code") String code,@RequestParam("mail") String mail){
        return ResponseEntity.ok(userService.AuthMailCode(mail,code));
    }


}
