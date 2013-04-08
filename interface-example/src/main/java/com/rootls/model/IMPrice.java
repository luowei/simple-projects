package com.rootls.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 国际市场价
 * User: luowei
 * Date: 13-2-21
 * Time: 下午6:02
 * To change this template use File | Settings | File Templates.
 */
public class IMPrice implements Serializable {

    Integer gid;
    Integer lid;
    Date priceDate;
    String productName;
    String modelName;
    String areaName;
    String priceType;
    Float lowEndPrice;
    Float highEndPrice;
    Float midEndPrice;
    String memo;
    Float rmbPrice;  //人民币价格
    Float changeRate;
    String unit;

    Date inStartDate;
    Date inEndDate;

    public IMPrice() {
    }

    public IMPrice(Integer gid, Date priceDate, String productName,
                   String modelName, String areaName, String priceType,
                   Float lowEndPrice, Float highEndPrice, Float midEndPrice,
                   String memo, Float rmbPrice, Float changeRate, String unit) {
        this.gid = gid;
        this.priceDate = priceDate;
        this.productName = productName;
        this.modelName = modelName;
        this.areaName = areaName;
        this.priceType = priceType;
        this.lowEndPrice = lowEndPrice;
        this.highEndPrice = highEndPrice;
        this.midEndPrice = midEndPrice;
        this.memo = memo;
        this.rmbPrice = rmbPrice;
        this.changeRate = changeRate;
        this.unit = unit;
    }

    @JsonProperty("id")
    public Integer getGid() {
        return gid;
    }

    public IMPrice setGid(Integer gid) {
        this.gid = gid;
        return this;
    }

    @JsonProperty("lid")
    public Integer getLid() {
        return lid;
    }

    public IMPrice setLid(Integer lid) {
        this.lid = lid;
        return this;
    }

    @JsonProperty("pd")
    public Date getPriceDate() {
        return priceDate;
    }

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public IMPrice setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
        return this;
    }

    @JsonProperty("pn")
    public String getProductName() {
        return productName;
    }

    public IMPrice setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    @JsonProperty("mn")
    public String getModelName() {
        return modelName;
    }

    public IMPrice setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    @JsonProperty("an")
    public String getAreaName() {
        return areaName;
    }

    public IMPrice setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    @JsonProperty("pt")
    public String getPriceType() {
        return priceType;
    }

    public IMPrice setPriceType(String priceType) {
        this.priceType = priceType;
        return this;
    }

    @JsonProperty("lp")
    public Float getLowEndPrice() {
        return lowEndPrice;
    }

    public IMPrice setLowEndPrice(Float lowEndPrice) {
        this.lowEndPrice = lowEndPrice;
        return this;
    }

    @JsonProperty("hp")
    public Float getHighEndPrice() {
        return highEndPrice;
    }

    public IMPrice setHighEndPrice(Float highEndPrice) {
        this.highEndPrice = highEndPrice;
        return this;
    }

    @JsonProperty("mp")
    public Float getMidEndPrice() {
        return midEndPrice;
    }

    public IMPrice setMidEndPrice(Float midEndPrice) {
        this.midEndPrice = midEndPrice;
        return this;
    }

    @JsonProperty("dsc")
    public String getMemo() {
        return memo;
    }

    public IMPrice setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    @JsonProperty("rp")
    public Float getRmbPrice() {
        return rmbPrice;
    }

    public IMPrice setRmbPrice(Float rmbPrice) {
        this.rmbPrice = rmbPrice;
        return this;
    }

    @JsonProperty("cr")
    public Float getChangeRate() {
        return changeRate;
    }

    public IMPrice setChangeRate(Float changeRate) {
        this.changeRate = changeRate;
        return this;
    }

    @JsonProperty("u")
    public String getUnit() {
        return unit;
    }

    public IMPrice setUnit(String unit) {
        this.unit = unit;
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
