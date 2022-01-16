package com.bobo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.entity.User;
import com.bobo.service.UserService;
import com.bobo.utils.JwtTokenUtils;
import com.bobo.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bobo
 * @since 2022-01-12
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private UserDetailsService userDetailsService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("分页查询用户")
//    @PreAuthorize("hasAuthority('1')")
    @GetMapping({"/list/{current}/{limit}", "/list/{current}/{limit}/{name}"})
    public R getUserList(@PathVariable(required = false) String name, @PathVariable Integer current, @PathVariable Integer limit) {
        Page<User> page = new Page<>(current, limit);
        userService.getUserList(name, page);
        return new R(page);

    }

    @ApiOperation("修改用户")
    @PutMapping("")
    public R updateUser(@RequestBody User user) {
        boolean isUpdate = userService.updateById(user);
        if (isUpdate) {
            return new R();
        } else {
            return new R(ErrorCode.Operation_error.getCode(), "修改失败");
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping("")
    public R deleteUser(Integer[] ids) {
        for (Integer id : ids) {
            userService.removeById(id);
        }
        return new R();
    }

    @ApiOperation("注册用户")
    @PostMapping("")
    public R addUser(@RequestBody User user) {
        boolean save = userService.save(user);
        if (save) {
            return new R();
        }
        return new R(ErrorCode.Operation_error.getCode(), "注册用户失败");
    }

    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public R login(@RequestBody LoginParams loginParams) {
        String token = userService.login(loginParams.getUsername(), loginParams.getPassword());
        if (token == null) {
            return new R(ErrorCode.Operation_error.getCode(), "账号或密码错误");
        }
        return new R(token);
    }

    @ApiOperation("根据token返回用户的信息")
    @PostMapping("userinfo")
    public R getUserInfo(HttpServletRequest request) {
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            //解析当前用户对应的token
            String username = jwtTokenUtils.getUserNameFromToken(authToken);
            if (username != null) {
                User user = userService.getUserByUsername(username);
                user.setUserPassword(null);
                return new R(user);
            }
        }
        throw new MyException(new R("401", "当前token无效"));
    }

    @ApiOperation("根据id获取用户信息")
    @GetMapping("{id}")
    public R getUserById(@PathVariable Integer id) {
        User user = userService.getById(id);
        user.setUserPassword("");
        return new R(user);
    }

    @ApiOperation("修改密码")
    @PutMapping("password")
    public R editPassword(@RequestBody EditPassVo editPassVo) {
        userService.editPass(editPassVo);
        return new R();
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public R register(@RequestBody User user) {
        boolean isSuccess = userService.register(user);
        if (isSuccess) {
            return new R();
        }
        throw new MyException(new R("201", "注册用户失败，请重试"));
    }

    @ApiOperation("获取所有的用户")
    @GetMapping("/allUser")
    public R getAllUser() {
        List<UserVo> userVos = userService.getAllUser();
        return new R(userVos);
    }

    @ApiOperation("更改头像")
    @PostMapping("/update/avatar")
    public R updateAvatar(@RequestBody ChangeAvatarVo changeAvatarVo) {
        boolean updateAvatar = userService.updateAvatar(changeAvatarVo);
        if (updateAvatar) {
            return new R();
        } else {
            return new R(ErrorCode.Operation_error.getCode(), "更新头像失败");
        }
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/update/profile")
    public R updateProfile(@RequestBody ProfileChangeVo profileChangeVo) {
        userService.updateProfile(profileChangeVo);
        return new R("");
    }
}

