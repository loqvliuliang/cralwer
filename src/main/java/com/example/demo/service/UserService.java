package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘亮 on 2017/9/13.
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User> implements UserDetailsService {
    private final  UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean login(User user){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("user_name",user.getUsername());
        wrapper.eq("password",user.getPassword());
        List<User> userList = userMapper.selectList(wrapper);
        if(userList.size()!=0){
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
