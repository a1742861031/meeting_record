package com.bobo.vo;

import lombok.Data;

/**
 * @Description 修改密码
 * @Date 2022/1/14 20:12
 * @Created by bobo
 */
@Data
public class EditPassVo {
    private Integer userId;
    private String password1;
    private String password2;
}
