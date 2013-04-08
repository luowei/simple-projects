package com.rootls.price.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-20
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class OilPrice  implements Serializable {

    Integer gid;
    Integer pid;
    String productName;
    String modelName;
    String areaName;
    String marketName;
    String unit;
    Integer gangLianFalg;

    public Integer getGid() {
        return gid;
    }

    public OilPrice setGid(Integer gid) {
        this.gid = gid;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public OilPrice setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public OilPrice setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public OilPrice setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public OilPrice setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public String getMarketName() {
        return marketName;
    }

    public OilPrice setMarketName(String marketName) {
        this.marketName = marketName;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public OilPrice setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public Integer getGangLianFalg() {
        return gangLianFalg;
    }

    public OilPrice setGangLianFalg(Integer gangLianFalg) {
        this.gangLianFalg = gangLianFalg;
        return this;
    }
}
