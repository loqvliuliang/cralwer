package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebAuthConfiguration extends WebSecurityConfigurerAdapter {


    @Bean
    UserDetailsService customUserService() {
        CustomUserService customUserService =   new CustomUserService();
        return customUserService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/home","/login.do").permitAll()
                .antMatchers(new String[]{"/js/**","/css/**","/images/**","/fonts/**","/assets/**"}).permitAll()
                .antMatchers("/user/**").hasRole("ADMIN")
                .anyRequest().authenticated()     //任何请求,登录后可以访问
                .and()
                .formLogin();

    }


}