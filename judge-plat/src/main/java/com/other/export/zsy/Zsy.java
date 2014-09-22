package com.other.export.zsy;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * excel对应的Bean对象
 * Created by luowei on 2014/9/18.
 */
public class Zsy implements Serializable {

    Integer id;
    String priceDate;
    String theName;
    String paihao;
    String jiagedian;
    BigDecimal diJia;
    BigDecimal gaoJia;

    public Zsy() {
    }

    public Zsy(Integer id, String priceDate, String theName, String paihao,
               String jiagedian, BigDecimal diJia, BigDecimal gaoJia) {
        this.id = id;
        this.priceDate = priceDate;
        this.theName = theName;
        this.paihao = paihao;
        this.jiagedian = jiagedian;
        this.diJia = diJia;
        this.gaoJia = gaoJia;
    }

    public Zsy(int anInt, BigDecimal price) {
        this.id = id;
        this.diJia =  price;
        this.gaoJia = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(String priceDate) {
        this.priceDate = priceDate;
    }

    public String getTheName() {
        return theName;
    }

    public void setTheName(String theName) {
        this.theName = theName;
    }

    public String getPaihao() {
        return paihao;
    }

    public void setPaihao(String paihao) {
        this.paihao = paihao;
    }

    public String getJiagedian() {
        return jiagedian;
    }

    public void setJiagedian(String jiagedian) {
        this.jiagedian = jiagedian;
    }

    public BigDecimal getDiJia() {
        return diJia;
    }

    public void setDiJia(BigDecimal diJia) {
        this.diJia = diJia;
    }

    public BigDecimal getGaoJia() {
        return gaoJia;
    }

    public void setGaoJia(BigDecimal gaoJia) {
        this.gaoJia = gaoJia;
    }
}
