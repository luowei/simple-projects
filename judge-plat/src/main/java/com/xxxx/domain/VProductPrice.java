package com.xxxx.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-4
 * Time: 下午2:19
 * To change this template use File | Settings | File Templates.
 */
public class VProductPrice implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    private Integer priceJugdeId;

    public Integer getPriceJugdeId() {
        return priceJugdeId;
    }

    public void setPriceJugdeId(Integer priceJugdeId) {
        this.priceJugdeId = priceJugdeId;
    }

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal changeRate;

    public BigDecimal getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(BigDecimal changeRate) {
        this.changeRate = changeRate;
    }

    private Timestamp priceDate;

    public Timestamp getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Timestamp priceDate) {
        this.priceDate = priceDate;
    }

    private String modelName;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    private int priceJudgeId;

    public int getPriceJudgeId() {
        return priceJudgeId;
    }

    public void setPriceJudgeId(int priceJudgeId) {
        this.priceJudgeId = priceJudgeId;
    }

    public VProductPrice() {
    }

    public VProductPrice(String productName, String areaName, Integer priceJugdeId,
                         BigDecimal price,String unit, BigDecimal changeRate, Timestamp priceDate) {
        this(productName, areaName, priceJugdeId, price,unit, changeRate, priceDate, null);
    }

    public VProductPrice(String productName, String areaName, Integer priceJugdeId, BigDecimal price,
                         String unit,BigDecimal changeRate, Timestamp priceDate, String modelName) {
        this.productName = productName;
        this.areaName = areaName;
        this.priceJugdeId = priceJugdeId;
        this.price = price;
        this.unit=unit;
        this.changeRate = changeRate;
        this.priceDate = priceDate;
        this.modelName = modelName;
    }

    public int getRateFlag(){
        return this.getChangeRate()==null? 0:this.getChangeRate().compareTo(BigDecimal.ZERO);
    }
}
