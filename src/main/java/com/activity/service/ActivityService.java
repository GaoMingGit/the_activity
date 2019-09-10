package com.activity.service;


import com.activity.domain.Activity;
import com.activity.domain.User;
import org.apache.ibatis.annotations.Insert;

import java.util.List;
import java.util.Map;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:20
 */
public interface ActivityService {

    /**
     * 查看所有已经创建的活动
     * @return
     */
    List<Activity> findAllActivity();

    /**
     * 根据id查找活动
     * @param aid
     * @return
     */
    Map<String,Object> findActivityById(Integer aid);

    /**
     *  创建活动
     * @param activity
     */
    void addActivity(Activity activity);

    /**
     * 活动创建者修改活动
     * @param activity
     */
    void updateActivity(Activity activity);

    /**
     * 根据活动的aid查找出参加活动的所所有用户
     * @param aid
     * @return
     */
    List<User> activityJoinByUser(Integer aid);

    /**
     * 根据用户id查询用户所参加的所以活动
     * @param uid
     * @return
     */
    List<Activity> userJoinedActivity(Integer uid);

    /**
     * 用户参与活动
     * @param aid
     * @param uid
     */
    void userJoinActivity(Integer aid, Integer uid);

    /**
     * 判断用户是否参见活动
     * @param aid
     * @param uid
     * @return
     */
    String userIsJoined(Integer aid, Integer uid);

    /**
     * 根据活动分类id查询同类活动
     * @param activityType
     * @return
     */
    List<Activity> findActivityByType(String activityType);

    List<Activity> findActivityCreatedByUser(Integer uid);

    int getMaxAid();

    /**
     * 用户取消报名参加活动
     * @param aid
     * @param uid
     */
    void deleteUserJoinActivity(Integer aid, Integer uid);

    /**
     * 根据aid删除活动
     * @param aid
     * @return
     */
    int deleteActivity(Integer aid);
}
