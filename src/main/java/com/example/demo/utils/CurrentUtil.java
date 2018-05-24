package com.example.demo.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUtil {


    /**
     * 获取当前操作用户
     * @return
     */
    public static Long getCurrentUserID(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
//        if (authentication != null) {
//            if (authentication.getPrincipal() instanceof Principal) {
//                return ((Principal) authentication.getPrincipal()).getId();
//
//            } else if (authentication.getPrincipal() instanceof PrincipalLite) {
//                return ((PrincipalLite) authentication.getPrincipal()).getId();
//
//            } else if (authentication.getName() != null) {
//                return Long.parseLong(authentication.getName());
//            }
//        }
//        throw new IllegalStateException("User not found!");
        return 1L;

    }

}
