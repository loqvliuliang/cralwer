package com.example.demo.utils;

import com.example.demo.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by 刘亮 on 2017/12/28.
 */
public class LoginInfo {

    public static User  getCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        authentication.getPrincipal();
        return null;
    }

}
