package com.bobo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.entity.User;
import com.bobo.service.UserService;
import com.bobo.vo.ErrorCode;
import com.bobo.vo.LoginParams;
import com.bobo.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("分页查询用户")
    @PreAuthorize("hasAuthority('1')")
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
    @DeleteMapping("{id}")
    public R deleteUser(@PathVariable Integer id) {
        boolean remove = userService.removeById(id);
        if (remove) {
            return new R();
        }
        return new R(ErrorCode.Operation_error.getCode(), "修改失败");
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
    public R login(@RequestBody LoginParams loginParams){
       String token =  userService.login(loginParams.getUsername(),loginParams.getPassword());
       if(token == null){
           return new R(ErrorCode.Operation_error.getCode(), "账号或密码错误");
       }
       return new R(token);
    }
}

