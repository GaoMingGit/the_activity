package com.activity.service.impl;

import com.activity.domain.Activity;
import com.activity.domain.BanDetail;
import com.activity.domain.User;
import com.activity.mapper.ActivityMapper;
import com.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> findAllActivity() {
        return activityMapper.findAllActivity();
    }

    @Override
    public Map<String,Object> findActivityById(Integer aid) {
        Map<String,Object> map = new HashMap<>();
        Activity activity = activityMapper.findActivityById(aid);
        List<User> activityJoinByUser = activityMapper.activityJoinByUser(aid);
        map.put("activity",activity);
        if(activityJoinByUser.size() == 0){
            map.put("code",0);
            map.put("msg","当前活动还没人参加");
            return map;
        }else{
            map.put("code",1);
            map.put("msg","当前活动有人参加，参加人的信息如下：");
            map.put("activityJoinByUser",activityJoinByUser);
            return map;
        }
    }

    @Override
    public void addActivity(Activity activity) {
        activityMapper.addActivity(activity);
    }

    @Override
    public void updateActivity(Activity activity) {
        activityMapper.updateActivity(activity);
    }

    @Override
    public List<User> activityJoinByUser(Integer aid) {
        return activityMapper.activityJoinByUser(aid);
    }

    @Override
    public List<Activity> userJoinedActivity(Integer uid) {
        return activityMapper.userJoinedActivity(uid);
    }

    @Override
    public void userJoinActivity(Integer aid, Integer uid) {
        activityMapper.userJoinActivity(aid,uid);
    }

    @Override
    public String userIsJoined(Integer aid, Integer uid) {
        return activityMapper.userIsJoined(aid,uid);
    }

    @Override
    public List<Activity> findActivityByType(String activityType) {
        return activityMapper.findActivityByType(activityType);
    }

    @Override
    public List<Activity> findActivityCreatedByUser(Integer uid) {
        return activityMapper.findActivityCreatedByUser(uid);
    }

    @Override
    public int getMaxAid() {
        return activityMapper.getMaxAid();
    }

    @Override
    public void deleteUserJoinActivity(Integer aid, Integer uid) {
        activityMapper.deleteUserJoinActivity(aid,uid);
    }

    @Override
    public int deleteActivity(Integer aid) {
        //删除活动
        int result = activityMapper.deleteActivity(aid);
        //删除活动的人数参与表的数据
        activityMapper.deleteActivityFromUserActivity(aid);
        return result;
    }

    @Override
    public void addBanDetail(BanDetail banDetail) {
        activityMapper.addBanDetail(banDetail);
    }

    @Override
    public List<BanDetail> findBanDetailByAid(Integer aid) {
        return activityMapper.findBanDetailByAid(aid);
    }

    @Override
    public List<Activity> findAllActivityOrderByBan() {
        return activityMapper.findAllActivityOrderByBan();
    }
}
