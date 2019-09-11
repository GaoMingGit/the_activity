package com.activity.controller;

import com.activity.domain.Activity;
import com.activity.domain.BanDetail;
import com.activity.domain.User;
import com.activity.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:45
 */
@Controller

public class ActivityController {
    @Autowired
    private ActivityService activityService;

    /**
     * 查找全部已经创建好的活动
     * @return
     */
    @ApiOperation(value = "查找全部已经创建好的活动")
    @ResponseBody
    @RequestMapping(value = "/findAllActivity",method = RequestMethod.GET)
    public List<Activity> findAllActivity(){
        List<Activity> list = activityService.findAllActivity();
        return list;
    }

    /**
     * 根据aid去查找活动
     * @param aid
     * @return
     */
    @ApiOperation(value = "根据aid去查找具体的活动")
    @ResponseBody
    @RequestMapping(value = "/findActivityById",method = RequestMethod.GET)
    public Map<String,Object> findActivityById(Integer aid,Integer uid){
        Map<String,Object> map = activityService.findActivityById(aid);
        //具体的活动
        Activity activity = (Activity)map.get("activity");
        if(activity == null){
            map.put("code1",3);
            map.put("msg","活动不存在");
            return map;
        }else{
            if(activity.getJoinpeople() >= activity.getActivitypeople()){
                //判断活动是否参加满人了，如果满人，提醒活动参加满人，code=3
                map.put("code1",1);
                map.put("msg1","活动参与人数已经满了");
            }else if(activity.getJoinpeople() < activity.getActivitypeople()){
                map.put("code1",2);
                map.put("msg1","该活动还可以参加");
            }
        }
        map.put("activity",activity);
        //判断用户是否参加活动
        String result = activityService.userIsJoined(aid,uid);
        if(result == null){
            //如果为空，说明，用户还没参加活动 ,返回前端 code=0
            map.put("code",0);
            map.put("msg","当前这个活动用户您还没参加");
            return map;
        }else{
            map.put("code",1);
            map.put("msg","当前这个活动用户您已经参加");
            return map;
        }
    }
    /**
     * 用户作为活动创建者创建活动,并且用户创建活动，就相当于参加活动了
     * @param activity
     * @return
     */
    @ApiOperation(value = "用户作为活动创建者创建活动,并且用户创建活动，就相当于参加活动了")
    @ResponseBody
    @RequestMapping(value = "/addActivity",method = RequestMethod.POST)
    public Map<String,Object> addActivity(@RequestBody Activity activity){
        //初始化参加活动的人数是 0
        activity.setJoinpeople(1);
        activity.setActivitystatus("0");
        Map<String,Object> map = new HashMap<>();
        System.out.println(activity.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        activity.setActivitycreatetime(sdf.format(new Date()));
        //用户创建活动
        activityService.addActivity(activity);
        int aid = activityService.getMaxAid();
        //用户成为活动的参与者
        activityService.userJoinActivity(aid,activity.getUid());
        map.put("code",1);
        map.put("msg","创建活动成功");
        return map;
    }

    /**
     * 更新活动
     * @param activity
     * @return
     */
    @ApiOperation(value = "创建者对所创建的活动进行更新")
    @RequestMapping(value = "/updateActivity",method = RequestMethod.POST)
    @ResponseBody
    public String updateActivity(HttpServletRequest request){
        String activityTitle = request.getParameter("activitytitle");
        String activityPeople = request.getParameter("activitypeople");
        String activityEndTime = request.getParameter("activityendtime");
        String activityType = request.getParameter("activitytype");
        String activityAddress = request.getParameter("activityaddress");
        String activityContent = request.getParameter("activitycontent");
        String aid = request.getParameter("aid");
        Activity activity = new Activity(Integer.parseInt(aid),activityTitle,Integer.parseInt(activityPeople),
                activityEndTime,activityType,activityAddress,activityContent);
        activityService.updateActivity(activity);
        return "更新活动成功";
    }

    /**
     * 根据用户id查询用户参加的所有活动
     * @param uid
     * @return
     */
    @ApiOperation(value = "根据用户id查询用户所参加的所有活动,返回null值，代表用户没有参加活动")
    @ResponseBody
    @RequestMapping(value = "/userJoinedActivity",method = RequestMethod.GET)
    public Map<String,Object> userJoinedActivity(Integer uid){
        Map<String,Object> map = new HashMap<>();
        List<Activity> userJoinedActivity = activityService.userJoinedActivity(uid);
        for (Activity activity : userJoinedActivity) {
            activity.setUid(uid);
        }
        if(userJoinedActivity.size() == 0){
            map.put("code",0);
            map.put("msg","当前用户没参加任何活动");
            return map;
        }else {
            map.put("code",1);
            map.put("msg","当前用户参加的活动信息有如下：");
            map.put("userJoinedActivity",userJoinedActivity);
            return map;
        }
    }

    /**
     * 用户参加活动
     * @param aid
     * @param uid
     * @return
     */
    @ApiOperation(value = "用户参加活动")
    @ResponseBody
    @RequestMapping(value = "/userJoinActivity",method = RequestMethod.POST)
    public Map<String,Object> userJoinActivity(@RequestParam Integer aid, @RequestParam Integer uid){
        activityService.userJoinActivity(aid,uid);
        //用户参加活动，修改活动表的参加人数
        Map<String,Object> map = activityService.findActivityById(aid);
        Map<String,Object> map1 =new HashMap<>();
        Activity activity = (Activity)map.get("activity");
        activity.setJoinpeople(activity.getJoinpeople() + 1);
        activityService.updateActivity(activity);
        map1.put("code",1);
        map1.put("msg","用户参加活动成功");
        return map1;
    }
    /**
     * 根据活动分类id查询同类活动
     * @param activityType
     * @return
     */
    @ApiOperation(value = "根据活动分类id查询同类活动")
    @ResponseBody
    @RequestMapping(value = "/findActivityByType",method = RequestMethod.GET)
    public Map<String,Object> findActivityByType(String activityType){
        Map<String,Object> map = new HashMap<>();
        List<Activity> list = activityService.findActivityByType(activityType);
        if(list.size() == 0){
            map.put("code",0);
            map.put("msg","该分类的活动不存在");
            return map;
        }else {
            map.put("code",1);
            map.put("msg","该分类的所有活动信息如下：");
            map.put("list",list);
            return map;
        }
    }

    @RequestMapping(value = "/userCancelJoinActivity",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> userCancelJoinActivity(Integer aid,Integer uid){
        Map<String,Object> map = new HashMap<>();
        //首先是查找出这条活动的信息
        Map<String, Object> map1 = activityService.findActivityById(aid);
        Activity activity = (Activity)map1.get("activity");
        //将报名人数减一
        activity.setJoinpeople(activity.getJoinpeople() - 1);
        //更新数据库
        activityService.updateActivity(activity);
        //删除活动表参加的记录
        activityService.deleteUserJoinActivity(aid,uid);

        map.put("code",0);
        return map;
    }

    /**
     * 根据aid删除活动
     * @param aid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteActiviy",method = RequestMethod.GET)
    public Map<String,Object> deleteActivity(Integer aid){
        System.out.println("=============要删除活动的id:"+aid);
        Map<String,Object> map = new HashMap<>();
        int result = activityService.deleteActivity(aid);
        if(result > 0){
            //代表删除活动成功
            map.put("code",1);
            map.put("msg","删除成功");
            return map;
        }else{
            //代表删除活动失败
            map.put("code",0);
            map.put("msg","删除失败");
            return map;
        }
    }

    @RequestMapping(value = "/addBanDetail",method = RequestMethod.POST)
    @ResponseBody
    public int addBanDetail(@RequestBody BanDetail banDetail){
        System.out.println(banDetail);
        SimpleDateFormat sdf = new SimpleDateFormat();
        banDetail.setBantime(sdf.format(new Date()));
        activityService.addBanDetail(banDetail);
        return 1;
    }
}
