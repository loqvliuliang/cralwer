package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.domain.Accept;
import com.example.demo.mapper.AcceptMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘亮 on 2017/12/19.
 */
@Service
public class AcceptService extends BaseService<AcceptMapper,Accept> {
    private final AcceptMapper acceptMapper;

    public AcceptService(AcceptMapper acceptMapper) {
        this.acceptMapper = acceptMapper;
    }

    /*根据用户id查询是否添加了收获地址*/
    public List<Accept> selectByUserId(Long userId){
        return acceptMapper.selectList(new EntityWrapper<Accept>().eq("user_id",userId));
    }



}
