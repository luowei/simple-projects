package com.rootls.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-11
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class UserData  implements Serializable {

    Long id;
    Long uid;
    String productCode;
    String productName;
    Date startDate;
    Date endDate;

    public UserData() {
    }

    public UserData(Long id, Long uid, String productCode, String productName) {
        this.id = id;
        this.uid = uid;
        this.productCode = productCode;
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getStartDate() {
        return startDate;
    }

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
