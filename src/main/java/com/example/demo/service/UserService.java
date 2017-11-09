package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * Created by 刘亮 on 2017/9/13.
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    private final  UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean login(User user){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("user_name",user.getUsername());
        String u = user.getUsername();
        wrapper.eq("password",user.getPassword());
        if(userMapper.selectList(wrapper).size()!=0){
            return true;
        }
        return false;
    }

}
