package com.activity.mapper;

import com.activity.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:04
 */
@Component
public interface UserMapper {
    /**
     * 用户注册
     * @param user
     * @return
     */
    @Insert("insert into user values (null,#{username},#{userpassword},#{usersex},#{userphone})")
    void userRegist(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Select("select * from user where username = #{username} and userpassword = #{userpassword}")
    User userLogin(User user);


}
