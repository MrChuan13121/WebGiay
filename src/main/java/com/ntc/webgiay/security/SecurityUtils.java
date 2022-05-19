package com.ntc.webgiay.security;

import com.ntc.webgiay.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static User getPrincipal() {
        return (User) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }
}
