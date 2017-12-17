package com.example.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.domain.Good;
import com.example.demo.service.GoodService;
import com.example.demo.utils.MyBatisPageUtil;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘亮 on 2017/12/17.
 */
@RestController
@RequestMapping("/api/good")
public class GoodController {

    private final GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }


    @GetMapping("/getAllGoods")
    public ResponseEntity<List<Good>> getAllGoods(Pageable pageable){
        Page page = MyBatisPageUtil.getPage(pageable);
        Page<Good> result = goodService.loadGoods(page);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + result.getTotal());
        headers.add("Link","/api/good/getAllGoods");
        return  new ResponseEntity<>(result.getRecords(), headers, HttpStatus.OK);
    }

    @GetMapping("/getGoodById")
    public ResponseEntity<Good> getGoodById(@RequestParam String id){
        return ResponseEntity.ok(goodService.selectOne(new EntityWrapper<Good>().eq("good_id",id)));
    }


}
