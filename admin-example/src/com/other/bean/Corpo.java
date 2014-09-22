package com.other.bean;

import java.io.Serializable;

/**
 * Created by luowei on 2014/5/27.
 */
public class Corpo implements Serializable {
    Integer id;
    String corpoName;

    public Corpo() {
    }

    public Corpo(Integer id, String corpoName) {
        this.id = id;
        this.corpoName = corpoName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorpoName() {
        return corpoName;
    }

    public void setCorpoName(String corpoName) {
        this.corpoName = corpoName;
    }
}
