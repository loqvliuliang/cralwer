package com.example.demo.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 刘亮 on 2018/3/28.
 */
@FeignClient(name = "crawler-producer")
public interface HelloFeign {

    @RequestMapping(value = "/api/test/hello")
    public String getHello(@RequestParam(value = "name") String name);
}
