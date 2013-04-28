package com.rootls.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-18
 * Time: 上午11:05
 * To change this template use File | Settings | File Templates.
 */
public class CallId {

    Integer id;
    Integer callId;
    Date callTime;

    public CallId() {
    }

    public CallId(Integer callId) {
        this.callId = callId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCallId() {
        if(callId==null){
            return 0;
        }
        return callId;
    }

    public void setCallId(Integer callId) {
        this.callId = callId;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }
}
