package com.example.demo.security;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;

/**
 * Created by 刘亮 on 2017/9/14.
 */
public class MyUserDetail extends User {//一个用户涉及多个角色

    private List<Role> roles;

    public MyUserDetail(User user,List<Role> roles){
        super(user);//
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles == null || roles.size() <1){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        StringBuilder commaBuilder = new StringBuilder();
        for(Role role : roles){
            commaBuilder.append(role.getRole()).append(",");
        }
        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
        //将一串角色，按照“admin,user”此类方式传入这个方法
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

    }

}
