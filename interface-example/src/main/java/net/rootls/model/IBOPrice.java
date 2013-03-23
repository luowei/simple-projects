package net.rootls.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-21
 * Time: 下午6:09
 * To change this template use File | Settings | File Templates.
 */
public class IBOPrice {

    Integer gid;
    Integer lid;
    Date priceDate;
    String productName;
    String modelName;
    String areaName;
    String marketName;
    Float priceCnpc;            //中石油价
    Float priceSinopec;         //中石化价
    Float priceMarket;          //社会价
    Float pricezzj;              //中准价
    Float pricelsj;              //零售价
    Float changeRate;
    String units;
    String memo;

    Date inStartDate;
    Date inEndDate;

    public IBOPrice() {
    }

    public IBOPrice(Integer gid, Date priceDate, String productName,
                    String modelName, String areaName, String marketName,
                    Float priceCnpc, Float priceSinopec, Float priceMarket,
                    Float pricezzj, Float pricelsj, Float changeRate,
                    String units, String memo) {
        this.gid = gid;
        this.priceDate = priceDate;
        this.productName = productName;
        this.modelName = modelName;
        this.areaName = areaName;
        this.marketName = marketName;
        this.priceCnpc = priceCnpc;
        this.priceSinopec = priceSinopec;
        this.priceMarket = priceMarket;
        this.pricezzj = pricezzj;
        this.pricelsj = pricelsj;
        this.changeRate = changeRate;
        this.units = units;
        this.memo = memo;
    }

    @JsonProperty("id")
    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @JsonProperty("lid")
    public Integer getLid() {
        return lid;
    }

    public IBOPrice setLid(Integer lid) {
        this.lid = lid;
        return this;
    }

    @JsonProperty("pd")
    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    @JsonProperty("pn")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("mn")
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @JsonProperty("an")
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @JsonProperty("mkn")
    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    @JsonProperty("cp")
    public Float getPriceCnpc() {
        return priceCnpc;
    }

    public void setPriceCnpc(Float priceCnpc) {
        this.priceCnpc = priceCnpc;
    }

    @JsonProperty("sp")
    public Float getPriceSinopec() {
        return priceSinopec;
    }

    public void setPriceSinopec(Float priceSinopec) {
        this.priceSinopec = priceSinopec;
    }

    @JsonIgnore //@JsonProperty("mp")
    public Float getPriceMarket() {
        return priceMarket;
    }

    public void setPriceMarket(Float priceMarket) {
        this.priceMarket = priceMarket;
    }

    @JsonProperty("zp")
    public Float getPricezzj() {
        return pricezzj;
    }

    public void setPricezzj(Float pricezzj) {
        this.pricezzj = pricezzj;
    }

    @JsonProperty("lp")
    public Float getPricelsj() {
        return pricelsj;
    }

    public void setPricelsj(Float pricelsj) {
        this.pricelsj = pricelsj;
    }

    @JsonIgnore //@JsonProperty("cr")
    public Float getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(Float changeRate) {
        this.changeRate = changeRate;
    }

    @JsonProperty("u")
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @JsonProperty("dsc")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
