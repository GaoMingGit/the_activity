package com.activity.service;


import com.activity.domain.User;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:03
 */

public interface UserService {

    /**
     * 用户注册
     * @param user
     * @return
     */
    User userLogin(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    void userRegist(User user);
}
