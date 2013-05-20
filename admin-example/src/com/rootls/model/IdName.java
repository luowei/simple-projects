package com.rootls.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-5-15
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public class IdName implements Serializable {

    Integer id;
    String name;

    public IdName() {
    }

    public IdName(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
