package com.rootls.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-5-15
 * Time: 下午2:28
 * To change this template use File | Settings | File Templates.
 */
public class WebClass implements Serializable {


    private Integer id;
    private String name;
    private String lanMuIds;
    private String productIds;
    private String childWebClassIds;
    private String adminIds;

    public WebClass() {
    }

    public WebClass(Integer id, String name, String lanMuIds, String productIds,
                    String childWebClassIds,String adminIds) {
        this.id = id;
        this.name = name;
        this.lanMuIds = lanMuIds;
        this.productIds = productIds;
        this.childWebClassIds = childWebClassIds;
        this.adminIds = adminIds;
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

    public String getLanMuIds() {
        return lanMuIds;
    }

    public void setLanMuIds(String lanMuIds) {
        this.lanMuIds = lanMuIds;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getChildWebClassIds() {
        return childWebClassIds;
    }

    public void setChildWebClassIds(String childWebClassIds) {
        this.childWebClassIds = childWebClassIds;
    }

    public String getAdminIds() {
        return adminIds;
    }

    public void setAdminIds(String adminIds) {
        this.adminIds = adminIds;
    }
}
