package com.rootls.bean;

import java.io.Serializable;

/**
 * 搜索条件
 * User: luowei
 * Date: 13-3-22
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
public class SearchBean implements Serializable {

    String receipt;
    //合同号
    Integer sid;
    //企业名称
    String qymc;
    //用户名
    String name;
    //合同编号
    String htbianhao;
    //会员类型
    Integer tria;
    //会员结束时间
    String endtime;
    //起始开票时间
    String startReceiptdate;
    //终止开票时间
    String endReceiptdate;
    //发票金额
    String recieptsum;
    //发票号
    String billId;
    //开票地
    String huikuandi;
    //款项类型
    Integer receipttype;
    //跟踪人
    Integer trackId;
    String trackStr;
    //邮寄地址
    String sendAddr;
    //    联系人
    String sendLxr;
    //    电话
    String sendPhone;
    //    手机
    String sendMobile;
    //    邮寄时间
    String sendTime;
    //    邮寄内容
    String sendContent;

    Integer guestType;
    //搜索类型,0为等于，1为包含
    Integer like;


    public Integer getGuestType() {
        return guestType;
    }

    public void setGuestType(Integer guestType) {
        this.guestType = guestType;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtbianhao() {
        return htbianhao;
    }

    public void setHtbianhao(String htbianhao) {
        this.htbianhao = htbianhao;
    }

    public Integer getTria() {
        return tria;
    }

    public void setTria(Integer tria) {
        this.tria = tria;
    }

    public String getRecieptsum() {
        return recieptsum;
    }

    public void setRecieptsum(String recieptsum) {
        this.recieptsum = recieptsum;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getHuikuandi() {
        return huikuandi;
    }

    public void setHuikuandi(String huikuandi) {
        this.huikuandi = huikuandi;
    }

    public Integer getReceipttype() {
        return receipttype;
    }

    public void setReceipttype(Integer receipttype) {
        this.receipttype = receipttype;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getTrackStr() {
        return trackStr;
    }

    public void setTrackStr(String trackStr) {
        int dotIdx = trackStr.indexOf(".");
        if (dotIdx > 0) {
            this.trackId = Integer.valueOf(trackStr.substring(0, dotIdx));
        }
        this.trackStr = trackStr;
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

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStartReceiptdate() {
        return startReceiptdate;
    }

    public void setStartReceiptdate(String startReceiptdate) {
        this.startReceiptdate = startReceiptdate;
    }

    public String getEndReceiptdate() {
        return endReceiptdate;
    }

    public void setEndReceiptdate(String endReceiptdate) {
        this.endReceiptdate = endReceiptdate;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getLike() {
        if (like == null) {
            return 0;
        }
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }
}
