package com.other.common;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import java.io.Serializable;

/**
 * Created by luowei on 2014/7/2.
 */
@JsonPropertyOrder({"code","msg","data"})
public class ResponseData<T> implements Serializable {

    Integer code = 0;
    String msg = "";
    T data;

    public ResponseData(Integer code) {
        this.code = code;
    }

    public ResponseData(String msg) {
        this.msg = msg;
    }

    public ResponseData(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseData setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseData setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @SuppressWarnings("deprecation")
    @JsonWriteNullProperties(false)
    public T getData() {
        return data;
    }

    public ResponseData setData(T data) {
        this.data = data;
        return this;
    }
}
