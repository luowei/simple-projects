package com.rootls.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-11
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
public class TradeDetail implements Serializable {

    private Long id;
    //年
    private Integer year;
    //月
    private Integer month;
    private String yearMonth;
    //产品代码
    private String productCode;
    //产品名称
    private String productName;
    //产品名称
    private String productType;
    //产品类型码
    private Integer typeCode;
    //企业性质
    private String companyType;
    //贸易方式
    private String tradeType;
    //运输方式
    private String transportation;
    //海关
    private String customs;
    //城市
    private String city;
    //产销国家
    private String country;
    //数量
    private BigDecimal amount;
    //计量单位
    private String unit;
    //金额
    private BigDecimal amountMoney;
    //单价
    private BigDecimal unitPrice;


    String inStartDate;
    String inEndDate;
    Integer type;

    public TradeDetail() {
    }

    public TradeDetail(Long id, Integer year, Integer month, String yearMonth,
                       String productCode, String productName, String productType,
                       Integer typeCode, String companyType, String tradeType,
                       String transportation, String customs, String city, String country,
                       BigDecimal amount, String unit, BigDecimal amountMoney, BigDecimal unitPrice) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.productCode = productCode;
        this.productName = productName;
        this.productType = productType;
        this.typeCode = typeCode;
        this.companyType = companyType;
        this.tradeType = tradeType;
        this.transportation = transportation;
        this.customs = customs;
        this.city = city;
        this.country = country;
        this.amount = amount;
        this.unit = unit;
        this.amountMoney = amountMoney;
        this.unitPrice = unitPrice;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @JsonIgnore
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @JsonProperty("ym")
    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    @JsonProperty("pc")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @JsonProperty("pn")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonIgnore
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonIgnore
    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    @JsonProperty("cp")
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    @JsonProperty("td")
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    @JsonProperty("ts")
    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    @JsonProperty("cu")
    public String getCustoms() {
        return customs;
    }

    public void setCustoms(String customs) {
        this.customs = customs;
    }

    @JsonProperty("ct")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("cou")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("num")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @JsonProperty("u")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @JsonProperty("mny")
    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(BigDecimal amountMoney) {
        this.amountMoney = amountMoney;
    }

    @JsonProperty("up")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @JsonIgnore
    public String getInStartDate() {
        return inStartDate;
    }

    public void setInStartDate(String inStartDate) {
        this.inStartDate = inStartDate;
    }

    @JsonIgnore
    public String getInEndDate() {
        return inEndDate;
    }

    public void setInEndDate(String inEndDate) {
        this.inEndDate = inEndDate;
    }

    @JsonIgnore
    public Integer getType() {
        if(type==null){
            type=0;
        }
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
