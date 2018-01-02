package com.example.demo.controller;

import com.example.demo.service.ShopCarService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by 刘亮 on 2017/12/18.
 */
@RestController
@RequestMapping("/api/shopCar")
public class ShopCarController {
    private final ShopCarService shopCarService;

    public ShopCarController(ShopCarService shopCarService) {
        this.shopCarService = shopCarService;
    }


    @GetMapping("/getShopCar/ByUserId")
    public ResponseEntity<Map<String,Integer>> getShopGoodsByUserId(@RequestParam Long userId){
        return ResponseEntity.ok(shopCarService.getShopGoodsByUserId(userId));
    }


    @GetMapping("/addGoodToShopCar")
    public ResponseEntity<Boolean> addGood(@RequestParam Long userId,@RequestParam String goodId){
        return ResponseEntity.ok(shopCarService.addGood(userId, goodId));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Boolean> deleteById(@RequestParam String id){
        return ResponseEntity.ok(shopCarService.deleteById(id));
    }


    @DeleteMapping("/delete/ByGoodIdAndUserId")
    public ResponseEntity<Boolean> deleteByGoodIdAndUserId(@RequestParam Long goodId,@RequestParam Long userId){
        return ResponseEntity.ok(shopCarService.deleteByGoodIdAndUserId(goodId, userId));
    }
}
