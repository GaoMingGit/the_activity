package com.activity.controller;

import com.activity.domain.Activity;
import com.activity.domain.User;
import com.activity.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Map<String,Object> findActivityById(Integer aid){
        Map<String,Object> map = new HashMap<>();
        Activity activity = activityService.findActivityById(aid);
        String result = activityService.userIsJoined(aid,activity.getUid());
        if(result == null){
            //如果为空，说明，用户还没参加活动 ,返回前端 code=0
            map.put("code",0);
        }else{
            map.put("code",1);
        }
        map.put("activity",activity);
        return map;
    }

    /**
     * 用户作为活动创建者创建活动
     * @param activity
     * @return
     */
    @ApiOperation(value = "用户作为活动创建者创建活动")
    @ResponseBody
    @RequestMapping(value = "/addActivity",method = RequestMethod.POST)
    public String addActivity(@RequestBody Activity activity, Integer uid ){
        activity.setUid(uid);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        activity.setActivitycreatetime(sdf.format(new Date()));
        activityService.addActivity(activity);
        return "创建活动成功";
    }

    /**
     * 更新活动
     * @param activity
     * @return
     */
    @ApiOperation(value = "创建者对所创建的活动进行更新")
    @RequestMapping(value = "/updateActivity",method = RequestMethod.PUT)
    public String updateActivity(Activity activity){
        activityService.updateActivity(activity);
        return "更新活动成功";
    }

    /**
     * 根据活动的aid查找出参加活动的所有用户
     * @param aid
     * @return
     */
    @ApiOperation(value = "根据活动的aid查找出参加活动的所有用户")
    @ResponseBody
    @RequestMapping(value = "/activityJoinByUser",method = RequestMethod.GET)
    public List<User> activityJoinByUser(Integer aid){
        List<User> activityJoinByUser = activityService.activityJoinByUser(aid);
        return activityJoinByUser;
    }

    /**
     * 根据用户id查询用户所参加的所以活动
     * @param uid
     * @return
     */
    @ApiOperation(value = "根据用户id查询用户所参加的所以活动")
    @ResponseBody
    @RequestMapping(value = "/userJoinedActivity",method = RequestMethod.GET)
    public List<Activity> userJoinedActivity(Integer uid){
        List<Activity> userJoinedActivity = activityService.userJoinedActivity(uid);
        for (Activity activity : userJoinedActivity) {
            activity.setUid(uid);
        }
        return userJoinedActivity;
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
    public String userJoinActivity(Integer aid,Integer uid){
        activityService.userJoinActivity(aid,uid);
        return "添加活动成功";
    }
    /**
     * 根据活动分类id查询同类活动
     * @param activityType
     * @return
     */
    @ApiOperation(value = "根据活动分类id查询同类活动")
    @ResponseBody
    @RequestMapping(value = "/findActivityByType",method = RequestMethod.GET)
    public List<Activity> findActivityByType(String activityType){
        List<Activity> list = activityService.findActivityByType(activityType);
        return list;
    }

}
