package com.other.bean;

import java.io.Serializable;

/**
 * 短信用户
 * User: luowei
 * Date: 14-4-3
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */
public class SmsUser implements Serializable {

    Integer gId;
    Integer smsId;

    public SmsUser() {
    }

    public SmsUser(Integer gId, Integer smsId) {
        this.gId = gId;
        this.smsId = smsId;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }
}
