package com.example.demo.service;

import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.domain.SysValueItem;
import com.example.demo.mapper.SysValueItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysValueItemService extends BaseService<SysValueItemMapper,SysValueItem> {

    //根据值列表大类查询值列表小类
    public List<SysValueItem> getItemsByValueId(Long Id){

        return null;
    }



}
