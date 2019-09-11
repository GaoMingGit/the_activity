package com.activity.domain;

import java.io.Serializable;

/**
 * @author 高铭
 * @date 2019/9/11 - 10:30
 */
public class BanDetail implements Serializable {
    private static final long serialVersionUID = 1015453579631288278L;
    private Integer bid;
    private Integer aid;
    private Integer uid;
    private String detail;
    private String bantime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBantime() {
        return bantime;
    }

    public void setBantime(String bantime) {
        this.bantime = bantime;
    }

    @Override
    public String toString() {
        return "BanDetail{" +
                "bid=" + bid +
                ", aid=" + aid +
                ", uid=" + uid +
                ", detail='" + detail + '\'' +
                ", bantime='" + bantime + '\'' +
                '}';
    }
}
