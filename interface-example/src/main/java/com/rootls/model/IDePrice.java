package com.rootls.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 国内市场价
 * User: luowei
 * Date: 13-2-21
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class IDePrice implements Serializable {

    Integer gid;
    Integer lid;
    Date priceDate;
    String productName;
    String modelName;
    String areaName;
    String marketName;
    String manufactureName;
    Float lowEndPrice;
    Float highEndPrice;
    Float average;  //市场价
    String memo;
    Float changeRate;
    String units;

    Date inStartDate;
    Date inEndDate;

    public IDePrice() {
    }

    public IDePrice(Integer gid, Date priceDate, String productName,
                    String modelName, String areaName, String marketName,
                    String manufactureName, Float lowEndPrice, Float highEndPrice,
                    Float average, String memo, Float changeRate, String units) {
        this.gid = gid;
        this.priceDate = priceDate;
        this.productName = productName;
        this.modelName = modelName;
        this.areaName = areaName;
        this.marketName = marketName;
        this.manufactureName = manufactureName;
        this.lowEndPrice = lowEndPrice;
        this.highEndPrice = highEndPrice;
        this.average = average;
        this.memo = memo;
        this.changeRate = changeRate;
        this.units = units;
    }

    @JsonProperty("id")
    public Integer getGid() {
        return gid;
    }

    public IDePrice setGid(Integer gid) {
        this.gid = gid;
        return this;
    }

    @JsonProperty("lid")
    public Integer getLid() {
        return lid;
    }

    public IDePrice setLid(Integer lid) {
        this.lid = lid;
        return this;
    }

    @JsonProperty("pd")
    public Date getPriceDate() {
        return priceDate;
    }

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public IDePrice setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
        return this;
    }

    @JsonProperty("pn")
    public String getProductName() {
        return productName;
    }

    public IDePrice setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    @JsonProperty("mn")
    public String getModelName() {
        return modelName;
    }

    public IDePrice setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    @JsonProperty("an")
    public String getAreaName() {
        return areaName;
    }

    public IDePrice setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    @JsonProperty("mkn")
    public String getMarketName() {
        return marketName;
    }

    public IDePrice setMarketName(String marketName) {
        this.marketName = marketName;
        return this;
    }

    @JsonProperty("mfn")
    public String getManufactureName() {
        return manufactureName;
    }

    public IDePrice setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
        return this;
    }

    @JsonProperty("lp")
    public Float getLowEndPrice() {
        return lowEndPrice;
    }

    public IDePrice setLowEndPrice(Float lowEndPrice) {
        this.lowEndPrice = lowEndPrice;
        return this;
    }

    @JsonProperty("hp")
    public Float getHighEndPrice() {
        return highEndPrice;
    }

    public IDePrice setHighEndPrice(Float highEndPrice) {
        this.highEndPrice = highEndPrice;
        return this;
    }

    @JsonProperty("mp")
    public Float getAverage() {
        return average;
    }

    public IDePrice setAverage(Float average) {
        this.average = average;
        return this;
    }

    @JsonProperty("dsc")
    public String getMemo() {
        return memo;
    }

    public IDePrice setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    @JsonProperty("cr")
    public Float getChangeRate() {
        return changeRate;
    }

    public IDePrice setChangeRate(Float changeRate) {
        this.changeRate = changeRate;
        return this;
    }

    @JsonProperty("u")
    public String getUnits() {
        return units;
    }

    public IDePrice setUnits(String units) {
        this.units = units;
        return this;
    }

    @JsonIgnore
    public Date getInStartDate() {
        return inStartDate;
    }

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public void setInStartDate(Date inStartDate) {
        this.inStartDate = inStartDate;
    }

    @JsonIgnore
    public Date getInEndDate() {
        return inEndDate;
    }

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public void setInEndDate(Date inEndDate) {
        this.inEndDate = inEndDate;
    }
}
