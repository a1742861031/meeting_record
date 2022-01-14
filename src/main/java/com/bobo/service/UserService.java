package com.bobo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bobo
 * @since 2022-01-12
 */
public interface UserService extends IService<User> {

    void getUserList(String name, Page<User> page);

    User getUserByUsername(String username);

    String login(String mobile, String password);

}
