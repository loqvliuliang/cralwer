package com.example.demo.security;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
//开启权限认证
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }



    /**
     * CORS过滤器，填加CORS头
     * @return CORS过滤器，供Spring使用。
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**/pppp/**", config);
        return new CorsFilter(source);
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }


    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
                // 通过用户名获取用户信息
                User account = userMapper.selectList(new EntityWrapper<User>().eq("user_name",name)).get(0) ;
                List<String> roles = new ArrayList<>();
                if(account!=null){
                    List<UserRole> userRoles =userRoleMapper.selectList(new EntityWrapper<UserRole>().eq("user_id",account.getId()));
                    userRoles.stream().map(UserRole::getRoleId).forEach(roleId->{
                        roles.add(roleMapper.selectById(roleId).getRole());
                    });
                    // 创建spring security安全用户
                    org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(),
                            AuthorityUtils.createAuthorityList(StringUtils.strip(roles.toString(),"[]")));
                    return user;
                } else {
                    throw new UsernameNotFoundException("用户[" + name + "]不存在");
                }
            }
        };

    }

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        AuthenticationManager manager = super.authenticationManagerBean();
//        return manager;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().disable();
        http
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/*").permitAll()
                ;

        // @formatter:on
    }


}
