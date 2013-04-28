package com.rootls.model;

import java.io.Serializable;

/**
 * 联系人
 * User: luowei
 * Date: 13-3-22
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
public class LinkMan implements Serializable{

    Integer id;
    //客户id
    Integer gid;
    //    邮寄地址
    String sendAddr;
    //    联系人
    String sendLxr;
    //电话区号
    String quhao;
    //    电话
    String sendPhone;
    //    手机
    String sendMobile;

    //格式化的电话
    String formatPhone;
    //格式化的手机
    String formatMobile;
    private int GCLX;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getSendAddr() {
        return sendAddr;
    }

    public void setSendAddr(String sendAddr) {
        this.sendAddr = sendAddr;
    }

    public String getSendLxr() {
        return sendLxr;
    }

    public void setSendLxr(String sendLxr) {
        this.sendLxr = sendLxr;
    }

    public String getQuhao() {
        return quhao;
    }

    public void setQuhao(String quhao) {
        this.quhao = quhao;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getSendMobile() {
        return sendMobile;
    }

    public void setSendMobile(String sendMobile) {
        this.sendMobile = sendMobile;
    }

    public String getFormatPhone() {
        return formatPhone;
    }

    public void setFormatPhone(String formatPhone) {
        this.formatPhone = formatPhone;
    }

    public String getFormatMobile() {
        return formatMobile;
    }

    public void setFormatMobile(String formatMobile) {
        this.formatMobile = formatMobile;
    }

    public int getGCLX() {
        return GCLX;
    }

    public void setGCLX(int GCLX) {
        this.GCLX = GCLX;
    }
}
