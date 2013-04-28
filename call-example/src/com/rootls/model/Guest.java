package com.rootls.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户
 * User: luowei
 * Date: 13-3-22
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
public class Guest implements Serializable {
    //id
    Integer id;

    //name
    String name;

    //企业名称
    String qymc;

    //会员类型,0:试用，1:正式
    Integer tria;

    //会员结束时间
    Date endtime;

    //跟踪人,adminid
    Integer trackId;

    //跟踪人
    Admin admin = new Admin();
    private String guestPhone;

    //客户类型
    public String getGuestType() {
        if (tria == null) {
            return null;
        }
        if (tria.equals(1)) {
            if (endtime.getTime() > new Date().getTime()) {
                return "正式";
            } else {
                return "正式过期";
            }
        } else {
            if (endtime.getTime() > new Date().getTime()) {
                return "试用";
            } else {
                return "试用过期";
            }
        }
    }

    public Integer getId() {
        if(id==null){
            return 0;
        }
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public Integer getTria() {
        return tria;
    }

    public void setTria(Integer tria) {
        this.tria = tria;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }
}
