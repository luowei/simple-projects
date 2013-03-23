package net.rootls.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 国内出厂价
 * User: luowei
 * Date: 13-2-21
 * Time: 下午5:52
 * To change this template use File | Settings | File Templates.
 */
public class IFacPrice implements Serializable {

    Integer gid;
    Integer lid;
    Date priceDate;
    String productName;
    String modelName;
    String manufactureName;
    Float exfactoryPrice;
    String memo;
    Float changeRate;
    String areaName;
    String saleCompanyName;
    String units;

    Date inStartDate;
    Date inEndDate;

    public IFacPrice() {
    }

    public IFacPrice(Integer gid, Date priceDate, String productName,
                     String modelName, String manufactureName, Float exfactoryPrice,
                     String memo, Float changeRate, String areaName,
                     String saleCompanyName, String units) {
        this.gid = gid;
        this.priceDate = priceDate;
        this.productName = productName;
        this.modelName = modelName;
        this.manufactureName = manufactureName;
        this.exfactoryPrice = exfactoryPrice;
        this.memo = memo;
        this.changeRate = changeRate;
        this.areaName = areaName;
        this.saleCompanyName = saleCompanyName;
        this.units = units;
    }

    @JsonProperty("id")
    public Integer getGid() {
        return gid;
    }

    public IFacPrice setGid(Integer gid) {
        this.gid = gid;
        return this;
    }

    @JsonProperty("lid")
    public Integer getLid() {
        return lid;
    }

    public IFacPrice setLid(Integer lid) {
        this.lid = lid;
        return this;
    }

    @JsonProperty("pd")
    public Date getPriceDate() {
        return priceDate;
    }

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public IFacPrice setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
        return this;
    }

    @JsonProperty("pn")
    public String getProductName() {
        return productName;
    }

    public IFacPrice setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    @JsonProperty("mn")
    public String getModelName() {
        return modelName;
    }

    public IFacPrice setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    @JsonProperty("mfn")
    public String getManufactureName() {
        return manufactureName;
    }

    public IFacPrice setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
        return this;
    }

    @JsonProperty("fp")
    public Float getExfactoryPrice() {
        return exfactoryPrice;
    }

    public IFacPrice setExfactoryPrice(Float exfactoryPrice) {
        this.exfactoryPrice = exfactoryPrice;
        return this;
    }

    @JsonProperty("dsc")
    public String getMemo() {
        return memo;
    }

    public IFacPrice setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    @JsonProperty("cr")
    public Float getChangeRate() {
        return changeRate;
    }

    public IFacPrice setChangeRate(Float changeRate) {
        this.changeRate = changeRate;
        return this;
    }

    @JsonProperty("an")
    public String getAreaName() {
        return areaName;
    }

    public IFacPrice setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    @JsonProperty("cn")
    public String getSaleCompanyName() {
        return saleCompanyName;
    }

    public IFacPrice setSaleCompanyName(String saleCompanyName) {
        this.saleCompanyName = saleCompanyName;
        return this;
    }

    @JsonProperty("u")
    public String getUnits() {
        return units;
    }

    public IFacPrice setUnits(String units) {
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
