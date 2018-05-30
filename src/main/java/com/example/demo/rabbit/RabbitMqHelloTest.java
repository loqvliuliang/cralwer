package com.example.demo.rabbit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/rabbit")
@RestController
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;

    @GetMapping("/hello")
    public void hello() throws Exception {
        helloSender.send();
    }

}