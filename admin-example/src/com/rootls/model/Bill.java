package com.rootls.model;

import com.rootls.bean.UserCache;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

import static com.rootls.bean.UserCache.UidName;
import static com.rootls.bean.UserCache.getUserMap;

/**
 * 发票
 * User: luowei
 * Date: 13-3-22
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public class Bill implements Serializable {

    Integer id;
    //发票号
    String receipt;
    // 合同id
    Integer sid;
    //客户id
    Integer gid;
    //    用户名
    String name;
    //    合同编号
    String htbianhao;
    //    开票时间
    Date receiptdate;
    //    收入项目
    String shouruxianmu;
    // 邮寄状态0:未寄出,1:已寄出, 2:已到达,-1:退回
    Integer sendtype;
    //    发票金额
    Float receiptsum;
    //    到账日期
    Date receiptdzdate;
    //    款项类型,1:应收
    Integer kuanxianglx;
    //    发票状态 ,1:有效 , -1:作废
    Integer receipttype;
    //    邮寄地址
    String sendAddr;
    //邮编
    String sendZip;
    //    联系人
    String sendLxr;
    //    电话
    String sendPhone;
    //    手机
    String sendMobile;
    //    邮寄时间
    Date sendTime;
    //    邮寄内容
    String sendContent;
    //企业名称
    String qymc;
    //开票地
    String huikuandi;
    //客户类型
    Integer trial;
    //客客户有效日期
    Date endTime;
    //跟踪人id
    Integer trackId;
    //跟踪人名称
    String trackname;
    //会员类型
    String guestType;

    //到账日期
    String daoZhangTime;

    //合同
    Contract contract = new Contract();

    //客户
    Guest guest = new Guest();

    //联系人
    LinkMan linkMan = new LinkMan();

    //    款项类型,1:应收
    String kuanxianglxStr;
    //    发票状态 ,1:有效 , -1:作废
    String receipttypeStr;


    public Bill() {
    }

    public Bill(Integer id, String receipt,Integer sid, Integer gid, String name,
                String qymc, Integer trackId, Integer trial, Date sendTime,
                Integer kuanxianglx, String huikuandi, String htbianhao,
                Date receiptdate, String shouruxianmu, Float receiptsum,String sendZip,
                String sendAddr, String sendLxr, String sendPhone, String sendMobile,
                String sendContent, Date endTime,Integer reciepttype,Integer sendtype) {
        this.id = id;
        this.receipt = receipt;
        this.sid = sid;
        this.gid = gid;
        this.name = name;
        this.qymc = qymc;
        this.trackId = trackId;
        this.trial = trial;
        this.sendTime = sendTime;
        this.kuanxianglx = kuanxianglx;
        this.huikuandi = huikuandi;
        this.htbianhao = htbianhao;
        this.receiptdate = receiptdate;
        this.shouruxianmu = shouruxianmu;
        this.receiptsum = receiptsum;
        this.sendZip = sendZip;
        this.sendAddr = sendAddr;
        this.sendLxr = sendLxr;
        this.sendPhone = sendPhone;
        this.sendMobile = sendMobile;
        this.sendContent = sendContent;
        this.endTime = endTime;
        this.receipttype = reciepttype;
        this.sendtype = sendtype;
    }

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

    public Date getReceiptdate() {
        return receiptdate;
    }

    public void setReceiptdate(Date receiptdate) {
        this.receiptdate = receiptdate;
    }

    public String getShouruxianmu() {
        return shouruxianmu;
    }

    public void setShouruxianmu(String shouruxianmu) {
        this.shouruxianmu = shouruxianmu;
    }

    public Float getReceiptsum() {
        return receiptsum;
    }

    public void setReceiptsum(Float receiptsum) {
        this.receiptsum = receiptsum;
    }

    public Date getReceiptdzdate() {
        return receiptdzdate;
    }

    public void setReceiptdzdate(Date receiptdzdate) {
        this.receiptdzdate = receiptdzdate;
    }

    public Integer getKuanxianglx() {
        return kuanxianglx;
    }

    public void setKuanxianglx(Integer kuanxianglx) {
        this.kuanxianglx = kuanxianglx;
    }

    public Integer getReceipttype() {
        return receipttype;
    }

    public void setReceipttype(Integer receipttype) {
        this.receipttype = receipttype;
    }

    public Integer getSendtype() {
        return sendtype;
    }

    public void setSendtype(Integer sendtype) {
        this.sendtype = sendtype;
    }

    public String getSendAddr() {
        return sendAddr;
    }

    public String getSendZip() {
        return sendZip;
    }

    public void setSendZip(String sendZip) {
        this.sendZip = sendZip;
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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getHuikuandi() {
        return huikuandi;
    }

    public void setHuikuandi(String huikuandi) {
        this.huikuandi = huikuandi;
    }

    public Integer getTrial() {
        return trial;
    }

    public void setTrial(Integer trial) {
        this.trial = trial;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getQymc() {
        return qymc;
    }

    public String getGuestType() {
        Long today = new Date().getTime();
        if(trial!=null && endTime!=null){
            if(trial.equals(0) && endTime.getTime()>=today){
                this.guestType = "试用";
            }
            if(trial.equals(0) && endTime.getTime()<today){
                this.guestType = "试用过期";
            }
            if(trial.equals(1) && endTime.getTime()>=today){
                this.guestType = "正式";
            }
            if(trial.equals(1) && endTime.getTime()<today){
                this.guestType = "正式过期";
            }
        }
        return guestType;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getTrackname() {
        UserCache.UidName user = UserCache.getUserMap().get(trackId);
        if(user==null){
            return null;
        }
        return user.getRealName();
    }

    public void setTrackname(String trackname) {
        this.trackname = trackname;
    }

    @JsonIgnore
    public Contract getContract() {
        contract.setHuikuandi(huikuandi);
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @JsonIgnore
    public Guest getGuest() {
        UserCache.UidName user = UserCache.getUserMap().get(trackId);
        if(user==null){
            return guest;
        }
        Admin admin = new Admin(user.getAdminId(),user.getUserName(),user.getRealName());
        guest.setAdmin(admin);
        guest.setName(name);
        guest.setQymc(qymc);
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @JsonIgnore
    public LinkMan getLinkMan() {
        linkMan.setGid(gid);
        return linkMan;
    }

    public void setLinkMan(LinkMan linkMan) {
        this.linkMan = linkMan;
    }

    public String getDaoZhangTime() {
        return daoZhangTime;
    }

    public void setDaoZhangTime(String daoZhangTime) {
        this.daoZhangTime = daoZhangTime;
    }

    public String getReceipttypeStr() {
        if(receipttype!=null && receipttype.equals(1)){
            return "有效";
        }else if(receipttype!=null && receipttype.equals(-1)){
            return "作废";
        }else {
            return "";
        }
    }

    public String getKuanxianglxStr() {
        if(kuanxianglx!=null && kuanxianglx.equals(1)){
            return "应收";
        }else {
            return "";
        }
    }
}
