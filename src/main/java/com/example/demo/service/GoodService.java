package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.domain.Good;
import com.example.demo.mapper.GoodMapper;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘亮 on 2017/12/17.
 */
@Service
public class GoodService extends BaseService<GoodMapper,Good> {

    private final GoodMapper goodMapper ;

    public GoodService(GoodMapper goodMapper) {
        this.goodMapper = goodMapper;
    }

    public Page<Good> loadGoods(Page page){
        page.getRecords();
        List<Good> list = goodMapper.selectPage(page,new EntityWrapper<>());
        if (CollectionUtils.isNotEmpty(list)){
            page.setRecords(list);
        }
        return page ;
    }


}
