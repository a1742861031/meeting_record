package com.bobo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.entity.User;
import com.bobo.service.UserService;
import com.bobo.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/list/{current}/{limit}")
    public R getUserList(@Nullable String name, @PathVariable Integer current, @PathVariable Integer limit) {
        Page<User> page = new Page<>(current, limit);
        userService.getUserList(name, page);
        return new R(page);
    }
}

