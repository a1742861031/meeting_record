package com.bobo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author bobo
 * @since 2022-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class

User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成员id
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Integer userId;

    /**
     * 学生的类型 研一 研二 研三 以及老师 超级管理员
     */
    private String userType;

    /**
     * 登录密码 管理系统添加应该有一个初始密码
     */
    private String userPassword;

    /**
     * 头像
     */
    private String userAvatar;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 电话
     */
    private String userMobile;

    /**
     * 学生邮件
     */
    private String userEmail;

    /**
     * 是否是管理员
     */
    private Integer isAdmin;
}
