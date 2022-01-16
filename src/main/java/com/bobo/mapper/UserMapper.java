package com.bobo.mapper;

import com.bobo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bobo
 * @since 2022-01-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("update  user set user_avatar=#{userAvatar} where user_name = #{userName}")
    int uploadAvatar(String userAvatar, String userName);
}
