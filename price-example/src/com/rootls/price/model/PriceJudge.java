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
public class PriceJudge implements Serializable {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    int productModelId;

    public int getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(int productModelId) {
        this.productModelId = productModelId;
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

    String areaName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    int isDisplay;

    public int getDisplay() {
        return isDisplay;
    }

    public void setDisplay(int display) {
        isDisplay = display;
    }

    String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    BigDecimal lastPrice;
    Date lastPriceDate;
    Date lastModifyDate;
    Date firstPriceDate;

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Date getLastPriceDate() {
        return lastPriceDate;
    }

    public void setLastPriceDate(Date lastPriceDate) {
        this.lastPriceDate = lastPriceDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Date getFirstPriceDate() {
        return firstPriceDate;
    }

    public void setFirstPriceDate(Date firstPriceDate) {
        this.firstPriceDate = firstPriceDate;
    }

    public PriceJudge() {
    }

    public PriceJudge(int id) {
        this.id = id;
    }

    public PriceJudge(String productName, String modelName, String areaName,
                      int display, String unit) {
        this.productName = productName;
        this.modelName = modelName;
        this.areaName = areaName;
        this.isDisplay = display;
        this.unit = unit;
    }

    public PriceJudge(BigDecimal lastPrice, Date lastPriceDate,
                      Date lastModifyDate, Date firstPriceDate) {
        this.lastPrice = lastPrice;
        this.lastPriceDate = lastPriceDate;
        this.lastModifyDate = lastModifyDate;
        this.firstPriceDate = firstPriceDate;
    }

    public PriceJudge(int id, int productId, int productModelId,int display,
                      String productName, String modelName, String areaName,String unit) {
        this.id = id;
        this.productId = productId;
        this.productModelId = productModelId;
        this.productName = productName;
        this.modelName = modelName;
        this.areaName = areaName;
        this.isDisplay = display;
        this.unit = unit;
    }


}
