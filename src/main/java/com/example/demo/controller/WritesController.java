package com.example.demo.controller;

import com.example.demo.controller.dto.GoodWritesDTO;
import com.example.demo.domain.Writes;
import com.example.demo.service.WritesService;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 刘亮 on 2017/12/21.
 */
@RestController
@RequestMapping("/api/writes")
public class WritesController {

    private final WritesService writesService;

    public WritesController(WritesService writesService) {
        this.writesService = writesService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Writes writes){
        return ResponseEntity.ok(writesService.insertWrites(writes));
    }

    @GetMapping("/getWritesByGoodId")
    public ResponseEntity<List<GoodWritesDTO>> getWritesByGoodId(@RequestParam String goodId){
        return ResponseEntity.ok(writesService.getWritesByGoodId(goodId));
    }

}
