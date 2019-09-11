package com.activity.domain;

import java.io.Serializable;

/**
 * @author 高铭
 * @date 2019/9/10 - 21:43
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 2871973383934486239L;
    private Integer id;
    private String adminname;
    private String adminpassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }
}
