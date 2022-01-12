package com.bobo.service.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.entity.User;
import com.bobo.mapper.UserMapper;
import com.bobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public void getUserList(String name, Page<User> page) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (Validator.isNotEmpty(name)) {
            wrapper.like("user_name", name);
        }
        userMapper.selectPage(page, wrapper);
    }
}
