package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.domain.Accept;
import com.example.demo.mapper.AcceptMapper;
import com.example.demo.utils.ShopUtil;
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

    /*根据id删除收货地址*/
    public Boolean deleteById(String acceptId){
        int i = acceptMapper.delete(new EntityWrapper<Accept>()
            .eq("accept_id",acceptId)
        );
        return i!=0;
    }


    /*新增或修改收货地址*/
    public Accept insertOrUpdateAccept(Accept accept){
        if(accept.getAccept_id()==null){
            accept.setAccept_id(ShopUtil.createId());
            acceptMapper.insert(accept);
        }else{
            acceptMapper.update(accept,new EntityWrapper<Accept>().eq("accept_id",accept.getAccept_id()));
        }
        return acceptMapper.selectList(
                new EntityWrapper<Accept>()
                .eq("accept_id",accept.getAccept_id())
        ).get(0);
    }



}
