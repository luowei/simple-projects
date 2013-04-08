package com.rootls.price.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-20
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
public class FactoryPrice implements Serializable {

    Integer gid;
    Integer pid;
    String productName;
    String modelName;
    String areaName;
    String manufactureName;
    String saleCompanyName;
    String units;
    Integer gangLianFalg;


    public Integer getGid() {
        return gid;
    }

    public FactoryPrice setGid(Integer gid) {
        this.gid = gid;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public FactoryPrice setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public FactoryPrice setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public FactoryPrice setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public FactoryPrice setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public FactoryPrice setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
        return this;
    }

    public String getSaleCompanyName() {
        return saleCompanyName;
    }

    public FactoryPrice setSaleCompanyName(String saleCompanyName) {
        this.saleCompanyName = saleCompanyName;
        return this;
    }

    public String getUnits() {
        return units;
    }

    public FactoryPrice setUnits(String units) {
        this.units = units;
        return this;
    }

    public Integer getGangLianFalg() {
        return gangLianFalg;
    }

    public FactoryPrice setGangLianFalg(Integer gangLianFalg) {
        this.gangLianFalg = gangLianFalg;
        return this;
    }
}
