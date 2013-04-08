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
public class PriceJudgeDetail extends PriceJudge implements Serializable {

    private Date priceDate;

    public PriceJudgeDetail(BigDecimal lastPrice, Date lastPriceDate, Date lastModifyDate, Date firstPriceDate) {
        super(lastPrice,lastPriceDate,lastModifyDate,firstPriceDate);
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    private int priceJudgeId;

    public int getPriceJudgeId() {
        return priceJudgeId;
    }

    public void setPriceJudgeId(int priceJudgeId) {
        this.priceJudgeId = priceJudgeId;
    }

    private BigDecimal judgePrice;

    public BigDecimal getJudgePrice() {
        return judgePrice;
    }

    public void setJudgePrice(BigDecimal judgePrice) {
        this.judgePrice = judgePrice;
    }

    private BigDecimal changeRate;

    public BigDecimal getChangeRate() {
        return changeRate==null?changeRate:changeRate.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setChangeRate(BigDecimal changeRate) {
        this.changeRate = changeRate;
    }

    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private int judgeStatus;

    public int getJudgeStatus() {
        return judgeStatus;
    }

    public void setJudgeStatus(int judgeStatus) {
        this.judgeStatus = judgeStatus;
    }

    private int adminId;

    public int getAdminId() {
        return adminId;
    }

    public PriceJudgeDetail setAdminId(int adminId) {
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

    public PriceJudgeDetail setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public PriceJudgeDetail() {
    }

    public PriceJudgeDetail(int id, int priceJudgeId, BigDecimal judgePrice,
                            BigDecimal changeRate,Date priceDate) {
        super(id);
        this.priceJudgeId = priceJudgeId;
        this.judgePrice = judgePrice;
        this.changeRate = changeRate;
        this.priceDate = priceDate;
    }

    public PriceJudgeDetail(int id,String productName, String modelName,
                            String areaName,int display, String unit,
                            Date priceDate,int priceJudgeId, BigDecimal judgePrice,
                            BigDecimal changeRate) {
        this.id = id;
        this.productName = productName;
        this.modelName = modelName;
        this.areaName = areaName;
        this.isDisplay = display;
        this.unit = unit;
        this.priceDate = priceDate;
        this.priceJudgeId = priceJudgeId;
        this.judgePrice = judgePrice;
        this.changeRate = changeRate;
    }

    private Date lastDate;
    private BigDecimal lastprice;
    private Date currentDate;

    public Date getLastDate() {
        return lastDate;
    }
    public BigDecimal getLastprice() {
        return lastprice;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public PriceJudgeDetail(int id, Date lastDate, BigDecimal lastPrice,Date currentDate,
                            String productName, String modelName, String areaName,
                            int is_display, String unit, Date priceDate,
                            int priceJudgeId, BigDecimal judgePrice, BigDecimal changeRate) {
//        super(productName, modelName, areaName, is_display, unit);
        this.id = id;
        this.productName = productName;
        this.modelName = modelName;
        this.areaName = areaName;
        this.isDisplay = is_display;
        this.unit = unit;
        this.lastDate = lastDate;
        this.lastprice = lastPrice;
        this.priceDate = priceDate;
        this.currentDate = currentDate;
        this.priceJudgeId = priceJudgeId;
        this.judgePrice = judgePrice;
        this.changeRate = changeRate;

    }


}
