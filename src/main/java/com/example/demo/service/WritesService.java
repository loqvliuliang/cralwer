package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.controller.dto.GoodWritesDTO;
import com.example.demo.domain.Good;
import com.example.demo.domain.User;
import com.example.demo.domain.Writes;
import com.example.demo.mapper.WritesMapper;
import com.example.demo.utils.ShopUtil;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘亮 on 2017/12/21.
 */
@Service
public class WritesService extends BaseService<WritesMapper,Writes>{
    private final WritesMapper writesMapper;
    private final GoodService goodService;
    private final UserService userService;


    public WritesService(WritesMapper writesMapper, GoodService goodService, UserService userService) {
        this.writesMapper = writesMapper;
        this.goodService = goodService;
        this.userService = userService;
    }

    public Boolean insertWrites(Writes writes){
        writes.setWrite_id(ShopUtil.createId());
        writes.setCreateTime(ZonedDateTime.now());
        int i = writesMapper.insert(writes);
        Good good = goodService.selectList(new EntityWrapper<Good>().eq("id",writes.getGood_id())).get(0);
        good.setGood_com(good.getGood_com()+1);
        goodService.update(good,new EntityWrapper<Good>().eq("id",writes.getGood_id()));
        return i!=0;
    }

    public List<GoodWritesDTO> getWritesByGoodId(String goodId){
        List<Writes> list = writesMapper.selectList(new EntityWrapper<Writes>().eq("good_id",goodId));
        List<GoodWritesDTO> dtos = new ArrayList<>();
        list.forEach(writes -> {
            GoodWritesDTO dto = new GoodWritesDTO();
            dto.setWritesName(userService.selectList(new EntityWrapper<User>().eq("id",writes.getUser_id())).get(0).getUsername());
            dto.setWritesDesc(writes.getWrite_desc());
            dto.setWritesDate(writes.getCreateTime());
            dtos.add(dto);
        });
        return dtos;
    }

}
