package com.rootls.price.model;

import com.rootls.price.bean.UserCache;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class PriceTraderDetail extends PriceTrader implements Serializable {

    private int priceTraderId;

    public int getPriceTraderId() {
        return priceTraderId;
    }

    public void setPriceTraderId(int priceTraderId) {
        this.priceTraderId = priceTraderId;
    }

    private BigDecimal tradePrice;

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    private BigDecimal tradeNum;

    public BigDecimal getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(BigDecimal tradeNum) {
        this.tradeNum = tradeNum;
    }

    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private Date priceDate;

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    private Date currentDate;

    public PriceTraderDetail setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
        return this;
    }
    public Date getCurrentDate() {
        return currentDate;
    }

    private int adminId;

    public int getAdminId() {
        return adminId;
    }

    public PriceTraderDetail setAdminId(int adminId) {
        this.adminId = adminId;
        return this;
    }

    public String getUserName(){
        UserCache.UidName user = UserCache.userIdMap.get(this.adminId);
        return user!=null ? user.getRealName():null;
    }

    private Timestamp modifyDate;

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public PriceTraderDetail setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public PriceTraderDetail() {
    }

    public PriceTraderDetail(int id, int priceTraderId, Date priceDate,
                             BigDecimal tradePrice, BigDecimal tradeNum) {
        this.id=id;
        this.priceTraderId=priceTraderId;
        this.priceDate=priceDate;
        this.tradePrice=tradePrice;
        this.tradeNum=tradeNum;
    }

    public PriceTraderDetail(BigDecimal lastPrice, Date lastPriceDate, Date lastModifyTime,Date firstPriceDate) {
        super(lastPrice, lastPriceDate, lastModifyTime,firstPriceDate);
    }

    public PriceTraderDetail(int id, int priceTraderId,
                             BigDecimal tradePrice, BigDecimal tradeNum, Date priceDate) {
        this.id = id;
        this.priceTraderId = priceTraderId;
        this.tradePrice = tradePrice;
        this.tradeNum = tradeNum;
        this.priceDate = priceDate;
    }

    public PriceTraderDetail(int id, int priceTraderId,
                             String productName, String modelName, String areaName,
                             String marketName, String traderName, String unit, int isDisplay,
                             Date priceDate, BigDecimal tradePrice,BigDecimal tradeNum) {

        this.id = id;
        this.priceTraderId=priceTraderId;
        this.productName = productName;
        this.modelName = modelName;
        this.areaName=areaName;
        this.marketName= marketName;
        this.traderName= traderName;
        this.unit= unit;
        this.isDisplay= isDisplay;
        this.priceDate= priceDate;
        this.tradePrice= tradePrice;
        this.tradeNum = tradeNum;
    }
}
