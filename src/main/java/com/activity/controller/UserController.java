package com.activity.controller;

import com.activity.domain.Activity;
import com.activity.domain.User;
import com.activity.service.ActivityService;
import com.activity.service.UserService;
import com.activity.utils.MD5Utils;
import com.activity.utils.ShiroEncryption;
import com.google.common.io.ByteSource;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:05
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @ApiOperation(value = "用户登录")
    @ResponseBody
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public Map<String,Object> userLogin(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();

        //使用ShiroEncryption加密工具类进行加密
        String pwd = ShiroEncryption.shiroEncryptionPwd(user.getUserpassword(), user.getUsername());
        user.setUserpassword(pwd);

        String username = userService.findByUserName(user.getUsername());
        User exitUser ;
        //当code = 0 ,说明账号不存在
        //当code = 1 ，说明账号或者密码错误
        //当code = 2，说明用户登录成功
        if(username != null ){
            exitUser = userService.userLogin(user);
            if(exitUser != null){
                //顺带把用户创建的活动以及用户参加的活动返回过去
                List<Activity> userJoinedActivity = activityService.userJoinedActivity(exitUser.getUid());
                //用户创建的活动
                List<Activity> findActivityCreatedByUser = activityService.findActivityCreatedByUser(exitUser.getUid());
                map.put("code",2);
                map.put("msg","用户登录成功");
                map.put("user",exitUser);
                map.put("userJoinedActivity",userJoinedActivity);
                map.put("findActivityCreatedByUser",findActivityCreatedByUser);
                return map;
            }else{
                map.put("code",1);
                map.put("msg","账号或者密码错误");
                return map;
            }
        }else{
            map.put("code",0);
            map.put("msg","账号不存在");
            return map;
        }
    }

    /**
     * 根据用户id查询所有发布过的活动
     */
    @ApiOperation(value = "根据用户id查询所有发布过的活动")
    @ResponseBody
    @RequestMapping(value = "/findActivityCreatedByUser",method = RequestMethod.GET)
    public List<Activity> findActivityCreatedByUser(@RequestParam Integer uid) {
        List<Activity> findActivityCreatedByUser = activityService.findActivityCreatedByUser(uid);
        return findActivityCreatedByUser;
    }


    /**
     * 用户注册
     * @param user
     * @return
     */
    @ApiOperation(value = "用户注册")
    @ResponseBody
    @RequestMapping(value = "/userRegist",method = RequestMethod.POST)
    public Map<String,Object> userRegist(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        //拿表单输入的用户账号去对比数据库
        String username = userService.findByUserName(user.getUsername());


        if(username == null){
            map.put("code",0);
            map.put("msg","用户账号可注册");
            //用ShiroEncryption工具类加密
            user.setUserpassword(ShiroEncryption.shiroEncryptionPwd(user.getUsername(),user.getUserpassword()));
            userService.userRegist(user);
            return map;
        }else{
            map.put("code",1);
            map.put("msg","用户账号已存在，不可注册");
            return map;
        }
    }
}
