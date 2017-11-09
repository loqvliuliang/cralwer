//package com.example.demo.security;
//
//
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.example.demo.domain.User;
//import com.example.demo.domain.UserRole;
//import com.example.demo.mapper.RoleMapper;
//import com.example.demo.mapper.UserMapper;
//import com.example.demo.mapper.UserRoleMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by wangl on 2017/2/10.
// */
//@Configuration
////用于@PreAuthorize的生效,基于方法的权限控制
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends GlobalAuthenticationConfigurerAdapter {
//    //   private final UserDetailsService userService;
//
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private RoleMapper roleMapper;
//    @Autowired
//    private UserRoleMapper userRoleMapper;
//
////    @Autowired
////    public WebSecurityConfig(UserDetailsService userService) {
////        this.userService = userService;
////    }
//
//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }
//
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//                // 通过用户名获取用户信息
//                User account = userMapper.selectList(new EntityWrapper<User>().eq("user_name",name)).get(0) ;
//                List<String> roles = new ArrayList<>();
//                if(account!=null){
//                    List<UserRole> userRoles =userRoleMapper.selectList(new EntityWrapper<UserRole>().eq("user_id",account.getId()));
//                    userRoles.stream().map(UserRole::getRoleId).forEach(roleId->{
//                        roles.add(roleMapper.selectById(roleId).getRole());
//                    });
//                    // 创建spring security安全用户
//                    org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(),
//                            AuthorityUtils.createAuthorityList(roles.toString()));
//                    return user;
//                } else {
//                    throw new UsernameNotFoundException("用户[" + name + "]不存在");
//                }
//            }
//        };
//
//    }
//
//}
