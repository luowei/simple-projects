package com.other.bean;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 14-1-24
 * Time: 下午5:25
 * To change this template use File | Settings | File Templates.
 */
@JsonPropertyOrder({"id", "name", "isParent", "groupPath"})
public class DzGroup implements Serializable {
    //id为：dzGroupId <- dbo.dz_sms_group(id)
    Integer id;
    Integer htId;
    Integer dzId;

    //真正的组Id
    Integer smsGroupId;
    Float priceChengben;
    Integer maxSend;

    Integer parentId;
    Boolean parent;


    String groupName;
    String groupPath;

    public DzGroup() {
    }

    public DzGroup(Integer id, String groupName, String groupPath) {
        this.id = id;
        this.groupName = groupName;
        this.groupPath = groupPath;
    }

    public DzGroup(Integer smsGroupId, Integer maxSend, Float priceChengben) {
        this.smsGroupId = smsGroupId;
        this.maxSend = maxSend;
        this.priceChengben = priceChengben;
    }

    public DzGroup(Integer htId, Integer dzId, Integer smsGroupId, Float priceChengben, Integer maxSend) {
        this.htId = htId;
        this.dzId = dzId;
        this.smsGroupId = smsGroupId;
        this.priceChengben = priceChengben;
        this.maxSend = maxSend;
    }

    public Integer getId() {
        return id == null ? 0 : id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Integer getHtId() {
        return htId;
    }

    public void setHtId(Integer htId) {
        this.htId = htId;
    }

    @JsonIgnore
    public Integer getDzId() {
        return dzId;
    }

    public void setDzId(Integer dzId) {
        this.dzId = dzId;
    }

    @JsonIgnore
    public Integer getSmsGroupId() {
        return smsGroupId;
    }

    public void setSmsGroupId(Integer smsGroupId) {
        this.smsGroupId = smsGroupId;
    }

    @JsonIgnore
    public Float getPriceChengben() {
        if(priceChengben==null){
            priceChengben = 0f;
        }
        return priceChengben;
    }

    public void setPriceChengben(Float priceChengben) {
        this.priceChengben = priceChengben;
    }

    @JsonIgnore
    public Integer getMaxSend() {
        return maxSend;
    }

    public void setMaxSend(Integer maxSend) {
        this.maxSend = maxSend;
    }

    @JsonIgnore  //@JsonWriteNullProperties(true)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @JsonProperty("isParent")
    public Boolean getParent() {
        return parent == null ? false : parent;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    @JsonProperty("name")
    public String getGroupName() {
        return groupName == null ? "" : groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupPath() {
        return groupPath == null ? "" : groupPath;
    }

    public void setGroupPath(String groupPath) {
        this.groupPath = groupPath;
    }
}
