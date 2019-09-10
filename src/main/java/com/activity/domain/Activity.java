package com.activity.domain;

import io.swagger.models.auth.In;

import java.io.Serializable;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:03
 */
public class Activity implements Serializable {
    private static final long serialVersionUID = 795065803973114978L;
    private Integer aid;
    private Integer uid;
    private Integer activitypeople;
    private String activitytitle;
    private String activitycontent;
    private String activityendtime;
    private String activitystatus;
    private String activitytype;
    private String activitycreatetime;
    private String activityaddress;
    private Integer joinpeople;

    public Activity() {
    }

    public Activity(Integer aid, String activitytitle, Integer activitypeople, String activityendtime,
                    String activitytype, String activityaddress, String activitycontent) {
        this.aid = aid;
        this.activitytitle = activitytitle;
        this.activitypeople = activitypeople;
        this.activityendtime = activityendtime;
        this.activitytype = activitytype;
        this.activityaddress = activityaddress;
        this.activitycontent = activitycontent;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getActivitypeople() {
        return activitypeople;
    }

    public void setActivitypeople(Integer activitypeople) {
        this.activitypeople = activitypeople;
    }

    public String getActivitytitle() {
        return activitytitle;
    }

    public void setActivitytitle(String activitytitle) {
        this.activitytitle = activitytitle;
    }

    public String getActivitycontent() {
        return activitycontent;
    }

    public void setActivitycontent(String activitycontent) {
        this.activitycontent = activitycontent;
    }

    public String getActivityendtime() {
        return activityendtime;
    }

    public void setActivityendtime(String activityendtime) {
        this.activityendtime = activityendtime;
    }

    public String getActivitystatus() {
        return activitystatus;
    }

    public void setActivitystatus(String activitystatus) {
        this.activitystatus = activitystatus;
    }

    public String getActivitytype() {
        return activitytype;
    }

    public void setActivitytype(String activitytype) {
        this.activitytype = activitytype;
    }

    public String getActivitycreatetime() {
        return activitycreatetime;
    }

    public void setActivitycreatetime(String activitycreatetime) {
        this.activitycreatetime = activitycreatetime;
    }

    public String getActivityaddress() {
        return activityaddress;
    }

    public void setActivityaddress(String activityaddress) {
        this.activityaddress = activityaddress;
    }


    public Integer getJoinpeople() {
        return joinpeople;
    }

    public void setJoinpeople(Integer joinpeople) {
        this.joinpeople = joinpeople;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", activitypeople=" + activitypeople +
                ", activitytitle='" + activitytitle + '\'' +
                ", activitycontent='" + activitycontent + '\'' +
                ", activityendtime='" + activityendtime + '\'' +
                ", activitystatus='" + activitystatus + '\'' +
                ", activitytype='" + activitytype + '\'' +
                ", activitycreatetime='" + activitycreatetime + '\'' +
                ", activityaddress='" + activityaddress + '\'' +
                ", joinpeople=" + joinpeople +
                '}';
    }
}
