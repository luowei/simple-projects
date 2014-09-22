package com.other.user;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by luowei on 2014/9/4.
 */
public class UserInfo implements Serializable{
    private Integer userId;
    private String username;
    private String passWord;
    private Integer parentId;

    private Integer trial;
    private String qymc;
    private Date endTime;

    public UserInfo() {
    }

    public UserInfo(Integer trial, String qymc,Date endTime) {
        this.trial = trial;
        this.qymc = qymc;
        this.endTime = endTime;
    }

    public UserInfo(Integer userId, String username, String passWord, Integer parentId) {
        this.userId = userId;
        this.username = username;
        this.passWord = passWord;
        this.parentId = parentId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getTrial() {
        return trial;
    }

    public void setTrial(Integer trial) {
        this.trial = trial;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
