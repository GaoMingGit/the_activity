package com.activity.controller;

import com.activity.domain.User;
import com.activity.service.UserService;
import com.activity.utils.MD5Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:05
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @ApiOperation(value = "用户登录")
    @ResponseBody
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public User userLogin(User user){
        //用户MD5加密
        user.setUserpassword(MD5Utils.md5(user.getUserpassword()));
        User exitUser = userService.userLogin(user);
        return exitUser;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @ApiOperation(value = "用户注册")
    @ResponseBody
    @RequestMapping(value = "/userRegist",method = RequestMethod.POST)
    public String userRegist(User user){
        //用户MD5加密
        user.setUserpassword(MD5Utils.md5(user.getUserpassword()));
        userService.userRegist(user);
        return "注册成功";
    }
}
