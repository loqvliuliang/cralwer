package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * Created by 刘亮 on 2017/9/13.
 */
@RestController
@RequestMapping("/user")
public class userController {
    private final UserService userService;

    public userController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User user){
        System.out.println(user.toString());
        return ResponseEntity.ok(userService.login(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/text/ll")
    public ResponseEntity<Boolean> text1(){
        return ResponseEntity.ok(true);
    }

    @PostMapping("/insertOrUpdate")
    public ResponseEntity<User> insertOrUpdateUser(@RequestBody @NotNull User user){
        return null;
    }


//    /**
//     * 邮箱认证接口
//     * @param mail
//     * @return
//     */
//    @GetMapping("/authMail")
//    public ResponseEntity<String> authMail(@RequestParam("mail") String mail){
//        return ResponseEntity.ok(userService.AuthMail(mail));
//    }
//
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
//
//


}
