package com.rootls.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-25
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public class Admin implements Serializable {

    //id
    Integer id;
    //用户名
    String name;
    //真实名
    String cname;

    //电话
    private String phone;

    //区号
    private String extCode;

    public Admin() {
    }

    public Admin(Integer id, String name, String cname) {
        this.id = id;
        this.name = name;
        this.cname = cname;
    }

    public Integer getId() {
        if(id==null){
            return 0;
        }
        return id;
    }

    public Admin setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Admin setName(String name) {
        this.name = name;
        return this;
    }

    public String getCname() {
        return cname;
    }

    public Admin setCname(String cname) {
        this.cname = cname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExtCode() {
        return extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode;
    }
}
