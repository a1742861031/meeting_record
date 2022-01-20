package com.bobo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 成员id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 学生的类型 研一 研二 研三 以及老师 超级管理员
     */
    private Integer userType;

    @TableField(exist = false)
    private String typeName;

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


    private Integer isLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(isAdmin.toString()));
        return list;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
