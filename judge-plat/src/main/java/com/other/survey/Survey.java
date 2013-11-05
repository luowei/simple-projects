package com.other.survey;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-10-31
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class Survey implements Serializable {

    Integer id;
    Integer userId;
    String userName;
    String userMobie;
    String userAddress;
    String ip;

    String question01;
    String question02;
    String question03;
    String question04;
    String question05;
    String question06;
    String question07;
    String question08;
    String question09;
    String question10;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobie() {
        return userMobie;
    }

    public void setUserMobie(String userMobie) {
        this.userMobie = userMobie;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getQuestion01() {
        return question01;
    }

    public void setQuestion01(String question01) {
        this.question01 = question01;
    }

    public String getQuestion02() {
        return question02;
    }

    public void setQuestion02(String question02) {
        this.question02 = question02;
    }

    public String getQuestion03() {
        return question03;
    }

    public void setQuestion03(String question03) {
        this.question03 = question03;
    }

    public String getQuestion04() {
        return question04;
    }

    public void setQuestion04(String question04) {
        this.question04 = question04;
    }

    public String getQuestion05() {
        return question05;
    }

    public void setQuestion05(String question05) {
        this.question05 = question05;
    }

    public String getQuestion06() {
        return question06;
    }

    public void setQuestion06(String question06) {
        this.question06 = question06;
    }

    public String getQuestion07() {
        return question07;
    }

    public void setQuestion07(String question07) {
        this.question07 = question07;
    }

    public String getQuestion08() {
        return question08;
    }

    public void setQuestion08(String question08) {
        this.question08 = question08;
    }

    public String getQuestion09() {
        return question09;
    }

    public void setQuestion09(String question09) {
        this.question09 = question09;
    }

    public String getQuestion10() {
        return question10;
    }

    public void setQuestion10(String question10) {
        this.question10 = question10;
    }

}
