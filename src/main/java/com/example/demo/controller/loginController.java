package com.example.demo.controller;

import com.example.demo.controller.dto.UserDTO;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;

import com.example.demo.utils.JsonResult;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * Created by 刘亮 on 2017/11/10.
 */
@RestController
@RequestMapping("/api/login")
public class loginController {
    private final UserService userService;

    public loginController(UserService userService){
        this.userService = userService;
    }



    /*用户登陆接口*/
    @PostMapping()
    public ResponseEntity<JsonResult> login(@RequestBody User user){
        System.out.println(user.toString());
        return new ResponseEntity<>(new JsonResult(userService.login(user)),HttpStatus.OK);
    }
    /**
     * 邮箱认证接口
     * @param mail
     * @return
     */
    @GetMapping(value = "/authMail")
    public  ResponseEntity<JsonResult> authMail(@RequestParam("mail") String mail)  {
        return  new ResponseEntity<>(new JsonResult(userService.AuthMail(mail)), HttpStatus.OK);
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
    public ResponseEntity<User> insertOrUpdateUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.insertOrUpdateUser(userDTO));
    }

    /**
     * 目前ajax只能发送get请求。暂时这样写
     * @param userName
     * @param password
     * @param mail
     * @param authCode
     * @return
     */
    @GetMapping("/insertOrUpdate")
    public ResponseEntity<User> insertOrUpdateUser1(@RequestParam("userName") String userName,
                                                    @RequestParam("password") String password,
                                                    @RequestParam("mail") String mail,
                                                    @RequestParam("authCode") String authCode
                                                    ){
        UserDTO userDTO = UserDTO.builder().userName(userName)
                                            .authCode(authCode)
                                            .password(password)
                                            .mail(mail).build();
        return ResponseEntity.ok(userService.insertOrUpdateUser(userDTO));
    }



    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(String path)
            throws IOException {
        String filePath =path;
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }



}
