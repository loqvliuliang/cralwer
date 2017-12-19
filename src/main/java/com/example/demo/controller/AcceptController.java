package com.example.demo.controller;

import com.example.demo.domain.Accept;
import com.example.demo.service.AcceptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 刘亮 on 2017/12/19.
 */
@RestController
@RequestMapping("/api/accept")
public class AcceptController {

    private final AcceptService acceptService;

    public AcceptController(AcceptService acceptService) {
        this.acceptService = acceptService;
    }


    @GetMapping("/getByUserId")
    public ResponseEntity<List<Accept>> selectById(@RequestParam Long userId){
        return ResponseEntity.ok(acceptService.selectByUserId(userId));
    }

}
