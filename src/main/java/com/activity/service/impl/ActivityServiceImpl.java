package com.activity.service.impl;

import com.activity.domain.Activity;
import com.activity.domain.User;
import com.activity.mapper.ActivityMapper;
import com.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:20
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> findAllActivity() {
        return activityMapper.findAllActivity();
    }

    @Override
    public Activity findActivityById(Integer aid) {
        return activityMapper.findActivityById(aid);
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
}
