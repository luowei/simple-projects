package com.rootls.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * 合同表
 */
public class Contract implements Serializable {
    //企业名称
    String companyName;
    String userName;

    //合同编号
    String htbianhao;
    //到账日期
    Date daozhangtime;
    //操作人
    Integer adminId;
    //客户id
    Integer guestId;
    //唯一id
    Integer id;
    //款项类型
    Integer kuanxianglx;
    //开票地
    String huikuandi;
    //合同类型
    Integer htlx;
    Integer accessoryType;

    public Contract() {
        super();
    }

    public Contract(String companyName, String userName, String htbianhao,
                    Integer htlx, Date daozhangtime, Integer accessoryType, Integer adminId) {
        super();
        this.companyName = companyName;
        this.userName = userName;
        this.htbianhao = htbianhao;
        this.htlx = htlx;
        this.daozhangtime = daozhangtime;
        this.accessoryType = accessoryType;
        this.adminId = adminId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHtbianhao() {
        return htbianhao;
    }

    public void setHtbianhao(String htbianhao) {
        this.htbianhao = htbianhao;
    }

    public Integer getHtlx() {
        return htlx;
    }

    public void setHtlx(Integer htlx) {
        this.htlx = htlx;
    }

    public Date getDaozhangtime() {
        return daozhangtime;
    }

    public void setDaozhangtime(Date daozhangtime) {
        this.daozhangtime = daozhangtime;
    }

    public Integer getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(Integer accessoryType) {
        this.accessoryType = accessoryType;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getGuestId() {
        return guestId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public Integer getId() {
        return id;
    }

    public Contract setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getKuanxianglx() {
        return kuanxianglx;
    }

    public Contract setKuanxianglx(Integer kuanxianglx) {
        this.kuanxianglx = kuanxianglx;
        return this;
    }

    public String getHuikuandi() {
        return huikuandi;
    }

    public Contract setHuikuandi(String huikuandi) {
        this.huikuandi = huikuandi;
        return this;
    }

    public boolean checkDaozhangtime(String daoZhangTime) {
        if (isBlank(daoZhangTime)) {
            return false;
        }
        if (this.daozhangtime != null) {
            Boolean eq = new SimpleDateFormat("yyyy-MM-dd").format(this.daozhangtime).equals(daoZhangTime);
            if (eq) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
