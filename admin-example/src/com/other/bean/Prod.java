package com.other.bean;

import java.io.Serializable;

/**
 * Created by luowei on 2014/5/27.
 */
public class Prod implements Serializable {
    Integer id;
    Integer pId;
    String productName;
    String productPath;

    public Prod() {
    }

    public Prod(Integer id, Integer pId, String productName, String productPath) {
        this.id = id;
        this.pId = pId;
        this.productName = productName;
        this.productPath = productPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPath() {
        return productPath;
    }

    public void setProductPath(String productPath) {
        this.productPath = productPath;
    }
}
