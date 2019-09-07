package com.activity.service.impl;

import com.activity.domain.User;
import com.activity.mapper.UserMapper;
import com.activity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserviceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(User user) {
        return userMapper.userLogin(user);
    }

    @Override
    public void userRegist(User user) {
        userMapper.userRegist(user);
    }

    @Override
    public String findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
