package com.rootls.price.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-20
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
public class InternationalPrice  implements Serializable {

    Integer gid;
    Integer pid;
    String productName;
    String modelName;
    String areaName;
    String memo;
    String unit;
    String priceType;
    Integer gangLianFalg;

    public Integer getGid() {
        return gid;
    }

    public InternationalPrice setGid(Integer gid) {
        this.gid = gid;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public InternationalPrice setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public InternationalPrice setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public InternationalPrice setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public InternationalPrice setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public InternationalPrice setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public InternationalPrice setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getPriceType() {
        return priceType;
    }

    public InternationalPrice setPriceType(String priceType) {
        this.priceType = priceType;
        return this;
    }

    public Integer getGangLianFalg() {
        return gangLianFalg;
    }

    public InternationalPrice setGangLianFalg(Integer gangLianFalg) {
        this.gangLianFalg = gangLianFalg;
        return this;
    }

}
