package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 刘亮 on 2017/9/13.
 */
@RestController
public class userController {
    private final UserService userService;

    public userController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User user){
        System.out.println("11");
        return ResponseEntity.ok(userService.login(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/text/ll")
    public ResponseEntity<Boolean> text1(){
        return ResponseEntity.ok(true);
    }

}
