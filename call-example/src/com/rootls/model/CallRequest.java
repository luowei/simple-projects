package com.rootls.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-16
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class CallRequest implements Serializable {

    //用户名
    String userName;
    //用户密码
    String userPassword;
    Integer id;
    String sessionID;
    String callIndex;
    //分机号码
    String extCode;
    //对方号码
    String otherCode;
    //指定时间段的起始时间
    Date startTime;
    //指定时间段的终止时间
    Date endTime;
    //1表示呼入，2表示呼出，不填或者填0表示全部
    Integer callDirect;
    //最小呼叫时长(秒)，-1不限制
    Long minCallTimeLen;
    //最大呼叫时长(秒)，-1不限制
    Long maxCallTimeLen;

    //最小通话时长(秒)，-1不限制
    Long minConnectTimeLen;
    //最大通话时长(秒)，-1不限制
    Long maxConnectTimeLen;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getCallIndex() {
        return callIndex;
    }

    public void setCallIndex(String callIndex) {
        this.callIndex = callIndex;
    }

    public String getExtCode() {
        return extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode;
    }

    public String getOtherCode() {
        return otherCode;
    }

    public void setOtherCode(String otherCode) {
        this.otherCode = otherCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCallDirect() {
        return callDirect;
    }

    public void setCallDirect(Integer callDirect) {
        this.callDirect = callDirect;
    }

    public Long getMinCallTimeLen() {
        return minCallTimeLen;
    }

    public void setMinCallTimeLen(Long minCallTimeLen) {
        this.minCallTimeLen = minCallTimeLen;
    }

    public Long getMaxCallTimeLen() {
        return maxCallTimeLen;
    }

    public void setMaxCallTimeLen(Long maxCallTimeLen) {
        this.maxCallTimeLen = maxCallTimeLen;
    }

    public Long getMinConnectTimeLen() {
        return minConnectTimeLen;
    }

    public void setMinConnectTimeLen(Long minConnectTimeLen) {
        this.minConnectTimeLen = minConnectTimeLen;
    }

    public Long getMaxConnectTimeLen() {
        return maxConnectTimeLen;
    }

    public void setMaxConnectTimeLen(Long maxConnectTimeLen) {
        this.maxConnectTimeLen = maxConnectTimeLen;
    }


    //把属性转换成请求参数
    public String toParams() {
        return "&ID=" + (id==null?"":id)  +
                "&sessionID=" + (sessionID==null?"":sessionID)  +
                "&callIndex=" + (callIndex==null?"":callIndex) +
                "&extCode=" + (extCode==null?"":extCode)  +
                "&otherCode=" + (otherCode==null?"":otherCode)  +
                "&startTime=" + (startTime ==null?"": startTime) +
                "&endTime=" + (endTime ==null?"": endTime) +
                "&callDirect=" + (callDirect==null?"":callDirect) +
                "&minCallTimeLen=" + (minCallTimeLen==null?"":minCallTimeLen) +
                "&maxCallTimeLen=" + (maxCallTimeLen==null?"":maxCallTimeLen) +
                "&minConnectTimeLen=" + (minConnectTimeLen==null?"":minConnectTimeLen) +
                "&maxConnectTimeLen=" + (maxConnectTimeLen==null?"":maxConnectTimeLen);
    }
}
