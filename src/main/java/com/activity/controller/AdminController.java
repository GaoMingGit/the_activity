package com.activity.controller;

import com.activity.domain.Activity;
import com.activity.domain.Admin;
import com.activity.domain.BanDetail;
import com.activity.mapper.UserMapper;
import com.activity.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 高铭
 * @date 2019/9/10 - 21:45
 */
@Controller
public class AdminController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 管理员去登录页面
     * @return
     */
    @RequestMapping("/toLoginUI")
    public String toLoginUI(){
        return "userLogin";
    }

    /**
     * 管理员登录
     * @param admin
     * @param model
     * @return
     */
    @RequestMapping("/adminLogin")
    public String adminLogin(Admin admin, Model model){

        /**
         * 使用shiro编写验证登录
         */
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getAdminname(),admin.getAdminpassword());
        try {
            //执行登录方法---->默认会去到userRealm这个类走doGetAuthenticationInfo这个方法
            subject.login(token);
            //登录成功,跳转到首页
            model.addAttribute("msg","欢迎"+admin.getAdminname()+"你前登录");
            return "redirect:/AdminFindAllActivity";
        }catch (UnknownAccountException e){
            //登录失败，用户名不存在
            model.addAttribute("msg","用户名不存在");
            //返回登录页面,有携带有信息，所以不能通过/user/toLoginUI这样的controller层跳转重定向到新的页面，只能是直接跳转具体页面
            return "userLogin";
        }catch (IncorrectCredentialsException e){
            //登录失败，用户名不存在
            model.addAttribute("msg","密码错误");
            //返回登录页面
            return "userLogin";
        }
    }

    /**
     * 查找全部已经创建好的活动
     * @return
     */
    @ApiOperation(value = "查找全部已经创建好的活动")
    @RequestMapping(value = "/AdminFindAllActivity",method = RequestMethod.GET)
    public String findAllActivity(Map<String,Object> map){
        List<Activity> list = activityService.findAllActivityOrderByBan();
        map.put("list",list);
        return "list";
    }
    /**
     * 管理员根据aid删除活动
     * @param aid
     * @return
     */
    @RequestMapping("/AdminDeleteActiviy")
    public String deleteActivity(Integer aid){
        System.out.println("=============要删除活动的id:"+aid);
        Map<String,Object> map = new HashMap<>();
        int result = activityService.deleteActivity(aid);
        return "redirect:/AdminFindAllActivity";
    }

    @RequestMapping("/findBanDetailByAid")
    public String findBanDetailByAid(Integer aid,Model model){
       List<BanDetail> list = activityService.findBanDetailByAid(aid);
        model.addAttribute("list",list);
        model.addAttribute("aid",aid);
        return "bandetail";
    }
}
