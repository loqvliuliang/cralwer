package com.example.demo.security;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.invoke.empty.Empty;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Created by 刘亮 on 2017/9/14.
 */
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private  UserMapper userMapper;
    @Autowired
    private  RoleMapper roleMapper;
    @Autowired
    private  UserRoleMapper userRoleMapper;





    @Override
    public MyUserDetail loadUserByUsername(String s) throws UsernameNotFoundException {
        EntityWrapper<User> wrapper = new EntityWrapper();
        wrapper.eq("user_name",s);
        List<User> list = userMapper.selectList(wrapper);//只会查出来一个数据
        List<Role> roles = null;
        if(list.size()==0){
            throw new UsernameNotFoundException("用户名不存在");
        }
        EntityWrapper<UserRole> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("user_id",list.get(0).getId());
        List<UserRole> list1 = userRoleMapper.selectList(wrapper1);
        if(list1.size()==0){
            throw new RuntimeException("用户对应角色不存在！请先分配此用户角色！");
        }
        for(UserRole userRole:list1){
            Role role = roleMapper.selectById(userRole.getRoleId());
            roles.add(role);
        }
        System.out.println("用户角色toString："+roles.toString());
        return new MyUserDetail(list.get(0),roles);
    }
}
