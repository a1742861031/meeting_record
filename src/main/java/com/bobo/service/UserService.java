package com.bobo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bobo.vo.*;

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

   void editPass(EditPassVo editPassVo);

    boolean register(User user);

    List<UserVo> getAllUser();

    boolean updateAvatar(ChangeAvatarVo changeAvatarVo);

    void updateProfile(ProfileChangeVo profileChangeVo);

    void editPassByName(EditPassVo1 editPassVo1);
}
