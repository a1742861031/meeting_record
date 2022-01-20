package com.bobo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.entity.User;
import com.bobo.mapper.TypeMapper;
import com.bobo.mapper.UserMapper;
import com.bobo.service.UserService;
import com.bobo.utils.JwtTokenUtils;
import com.bobo.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Value("${default.avatar}")
    private String defaultAvatar;

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
            throw new MyException(new R<>("201", "账号或密码错误"));
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

    @Override
    public boolean register(User user) {
        if (user.getUserAvatar() == null) {
            user.setUserAvatar(defaultAvatar);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", user.getUsername());
        List<User> users = userMapper.selectList(wrapper);
        if (users.size() != 0) {
            throw new MyException(new R("201", "当前用户已存在"));
        }
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setIsAdmin(0);
        int insert = userMapper.insert(user);
        return insert >= 0;
    }

    @Override
    public List<UserVo> getAllUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("user_type");
        List<User> users = userMapper.selectList(wrapper);
        List<UserVo> userVos = new ArrayList<>();
        for (User user : users) {
            UserVo userVo = new UserVo();
            BeanUtil.copyProperties(user, userVo);
            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    public boolean updateAvatar(ChangeAvatarVo changeAvatarVo) {
        int i = userMapper.uploadAvatar(changeAvatarVo.getUserAvatar(), changeAvatarVo.getUserName());
        return i >= 1;
    }

    @Override
    public void updateProfile(ProfileChangeVo profileChangeVo) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", profileChangeVo.getUserName());
        User user = new User();
        user.setUserEmail(profileChangeVo.getUserEmail());
        user.setUserMobile(profileChangeVo.getUserMobile());
        userMapper.update(user, wrapper);
    }

    @Override
    public void editPassByName(EditPassVo1 editPassVo1) {
        String encode = passwordEncoder.encode(editPassVo1.getPassword1());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", editPassVo1.getUserName());
        User user = userMapper.selectOne(wrapper);
        user.setUserPassword(encode);
        userMapper.updateById(user);
    }
}
