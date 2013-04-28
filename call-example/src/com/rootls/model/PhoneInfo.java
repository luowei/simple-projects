package com.rootls.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-18
 * Time: 下午1:22
 * To change this template use File | Settings | File Templates.
 */
public class PhoneInfo implements Serializable {

    private String LX;
    private String phoneFrom;
    private int isMobile;

    private String area;
    private String cardType;
    private int extCode;

    public String getLX() {
        return LX;
    }

    public void setLX(String LX) {
        this.LX = LX;
    }

    public String getPhoneFrom() {
        return phoneFrom;
    }

    public void setPhoneFrom(String phoneFrom) {
        this.phoneFrom = phoneFrom;
    }


    public int getIsMobile() {
        return isMobile;
    }

    public int getMobile() {
        return isMobile;
    }

    public void setMobile(int mobile) {
        isMobile = mobile;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getExtCode() {
        return extCode;
    }

    public void setExtCode(int extCode) {
        this.extCode = extCode;
    }
}
