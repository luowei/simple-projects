package com.other.bmd;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by luowei on 2014/7/11.
 */
public class Bmd implements Serializable {
    String mobile;
    Integer mType;
    Date addTime;

   public static class OtherMobile{
       Integer id;
       String memo;

       public OtherMobile(Integer id, String memo) {
           this.id = id;
           this.memo = memo;
       }

       public Integer getId() {
           return id;
       }

       public String getMemo() {
           return memo;
       }
   }

    public Bmd() {
    }

    public Bmd(String mobile, Integer mType, Date addTime) {
        this.mobile = mobile;
        this.mType = mType;
        this.addTime = addTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
