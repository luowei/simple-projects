package com.rootls.price.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class PriceTrader implements Serializable {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    String modelName;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    int modelId;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    int isDisplay;

    public int getDisplay() {
        return isDisplay;
    }

    public void setDisplay(int display) {
        isDisplay = display;
    }

    String areaName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    String marketName;

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    String traderName;

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    Date firstPriceDate;

    public Date getFirstPriceDate() {
        return firstPriceDate;
    }

    public void setFirstPriceDate(Date firstPriceDate) {
        this.firstPriceDate = firstPriceDate;
    }

    Date lastPriceDate;

    public Date getLastPriceDate() {
        return lastPriceDate;
    }

    public void setLastPriceDate(Date lastPriceDate) {
        this.lastPriceDate = lastPriceDate;
    }

    Date lastModifyTime;

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    BigDecimal lastPrice;

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public PriceTrader setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
        return this;
    }

    public PriceTrader() {
    }

    public PriceTrader(BigDecimal lastPrice,
                       Date lastPriceDate, Date lastModifyTime,Date firstPriceDate) {
        this.lastPrice = lastPrice;
        this.lastPriceDate = lastPriceDate;
        this.lastModifyTime = lastModifyTime;
        this.firstPriceDate = firstPriceDate;
    }
}
