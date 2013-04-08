package com.rootls.price.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-17
 * Time: 上午11:30
 * To change this template use File | Settings | File Templates.
 */
public class CommonDto {

    String productName;
    String modelName;
    String areaName;
    String marketName;
    String traderName;
    Date priceDate;

    Date startDate;
    Date endDate;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public Date getStartDate() {
        return startDate;
    }

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean validDateProductName(){
        return this.getProductName()!=null &&
                this.getProductName()!="" &&
                this.getPriceDate()!=null;
    }
}
