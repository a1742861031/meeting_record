package com.bobo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bobo.entity.User;
import com.bobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Description 重写UserDetailsService 方法
 * @Date 2022/1/13 11:40 AM
 * @Created by bobo
 */
//@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_mobile", mobile);
        User user = userService.getOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Integer userType = user.getUserType();
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(("ROLE_" + userType)));
        return new org.springframework.security.core.userdetails.User(user.getUserMobile(),
                passwordEncoder.encode(user.getUserPassword()),
                authorities
        );
    }
}
