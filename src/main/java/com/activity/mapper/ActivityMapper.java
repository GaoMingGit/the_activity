package com.activity.mapper;

import com.activity.domain.Activity;
import com.activity.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:36
 */
@Component
public interface ActivityMapper {
    /**
     * 查看所有已经创建的活动
     * @return
     */
    @Select("select * from activitytable order by aid desc")
    List<Activity> findAllActivity();
    /**
     *  创建活动
     * @param activity
     */
    @Insert("insert into activitytable values (null,#{uid},#{activitypeople},#{activitytitle}," +
            "#{activitycontent},#{activityendtime},#{activitystatus},#{activitytype},#{activitycreatetime},#{activityaddress})")
    void addActivity(Activity activity);

    /**
     * 根据id查找活动
     * @param aid
     * @return
     */
    @Select("select * from activitytable where aid = #{aid}")
    Activity findActivityById(Integer aid);

    /**
     * 活动创建者修改活动
     * @param activity
     */
    @Update("update activitytable set activitypeople = #{activitypeople},activitycontent = #{activitycontent}," +
            "activitystatus = #{activitystatus},activitytype = #{activitytype}，activityaddress = #{activityaddress} where aid = #{aid}")
    void updateActivity(Activity activity);

    /**
     * 根据活动的aid查找出参加活动的所所有用户
     * @param aid
     * @return
     */
    @Select("select * from user where uid in ( select uid from user_activity where aid = #{aid})")
    List<User> activityJoinByUser(Integer aid);

    /**
     * 根据用户id查询用户所参加的所以活动
     * @param uid
     * @return
     */
    @Select("select aid,activitypeople,activitytitle,activitycontent,activityendtime,activitystatus,activitytype,activitycreatetime,activityaddress" +
            " from activitytable where aid in ( select aid from user_activity where uid = #{uid})")
    List<Activity> userJoinedActivity(Integer uid);

    /**
     * 用户参与活动
     * @param aid
     * @param uid
     */
    @Insert("insert into user_activity values (null,#{aid},#{uid})")
    void userJoinActivity(@Param("aid") Integer aid,@Param("uid") Integer uid);

    /**
     * 判断用户是否成功参加运动
     * @param aid
     * @param uid
     * @return
     */
    @Select("select id from user_activity where aid = #{aid} and uid = #{uid}")
    String userIsJoined(@Param("aid") Integer aid,@Param("uid") Integer uid);

    /**
     * 根据类型查找活动
     * @param activityType
     * @return
     */
    @Select("select * from activitytable where activityType = #{activityType}")
    List<Activity> findActivityByType(String activityType);
}
