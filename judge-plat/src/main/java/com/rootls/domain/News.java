package com.rootls.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-22
 * Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
public class News implements Serializable {
    Integer id;
    String title;
    String content;
    String img;
    Date newsDate;
    String url;

    public News() {
    }

    public News(int id,String content, String img, String url) {
        this.id = id;
        this.content = content;
        this.img = img;
        this.url = url;
    }

    public News(int id,String title, String url, Date newsDate) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.newsDate = newsDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
