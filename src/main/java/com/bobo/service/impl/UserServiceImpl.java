package com.bobo.service.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.entity.User;
import com.bobo.mapper.TypeMapper;
import com.bobo.mapper.UserMapper;
import com.bobo.service.UserService;
import com.bobo.utils.JwtTokenUtils;
import com.bobo.vo.EditPassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bobo
 * @since 2022-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public void getUserList(String name, Page<User> page) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (Validator.isNotEmpty(name)) {
            wrapper.like("user_name", name);
        }
        wrapper.orderByAsc("user_type");
        userMapper.selectPage(page, wrapper);
        List<User> records = page.getRecords();
        for (User record : records) {
            record.setUserPassword(null);
            String typeName = typeMapper.selectById(record.getUserType()).getTypeName();
            if (typeName != null && Validator.isNotEmpty(typeName)) {
                record.setTypeName(typeName);
            }
        }
    }

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = jwtTokenUtils.generateToken(userDetails);
        return token;
    }

    @Override
    public void editPass(EditPassVo editPassVo) {
        String encode = passwordEncoder.encode(editPassVo.getPassword1());
        User user = new User();
        user.setUserPassword(encode);
        user.setUserId(editPassVo.getUserId());
        userMapper.updateById(user);
    }
}
