package com.activity.controller;

import com.activity.domain.Activity;
import com.activity.domain.Admin;
import com.activity.domain.BanDetail;
import com.activity.mapper.UserMapper;
import com.activity.service.ActivityService;
import io.swagger.annotations.ApiOperation;
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
        Admin adminLogin = userMapper.adminLogin(admin);
        if(adminLogin != null){
            return "redirect:/AdminFindAllActivity";
        }else {
            model.addAttribute("msg","账号或者密码错误");
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
