package com.other.bean;

import java.io.Serializable;

/**
 * Created by luowei on 2014/7/22.
 */
public class City implements Serializable {

    Integer id;
    String cityName;
    String parentId;
    String cityPath;


    public City() {
    }

    public City(Integer id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public City(Integer id, String cityName, String parentId, String cityPath) {
        this.id = id;
        this.cityName = cityName;
        this.parentId = parentId;
        this.cityPath = cityPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCityPath() {
        return cityPath;
    }

    public void setCityPath(String cityPath) {
        this.cityPath = cityPath;
    }
}
