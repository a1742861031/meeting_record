package com.bobo.vo;

import lombok.Data;

/**
 * @Description 登陆参数
 * @Date 2022/1/13 5:27 PM
 * @Created by bobo
 */
@Data
public class LoginParams {
    private String username;
    private String password;
    private String code;
}
