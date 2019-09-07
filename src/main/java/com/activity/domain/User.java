package com.activity.domain;

import java.io.Serializable;

/**
 * @author 高铭
 * @date 2019/9/2 - 22:03
 */
public class User implements Serializable {
    private static final long serialVersionUID = 8809400125899786229L;
    private Integer uid;
    private String username;
    private String userpassword;
    /**
     * 1代表男 2 代表女
     */
    private String usersex;
    private String userphone;

    public User() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", usersex='" + usersex + '\'' +
                ", userphone='" + userphone + '\'' +
                '}';
    }
}
