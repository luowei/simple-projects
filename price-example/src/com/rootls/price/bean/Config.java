package com.rootls.price.bean;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-11
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
public class Config {

    public static String UP = "up";
    public static String DOWN = "down";

    public static String PRODUCT = "p";
    public static String OIL = "o";

    public static int PAGE_SIZE = 20;
    public static int PAGEBAR_SIZE = 10;

    //一周多少天
    public static int WEEK = 20;

    //涨跌幅显示记录数
    public static int WEEK_ITEMS = 10;

    //首页滚动信息数
    public static int MARQUEE_LISTSIZE = 8;
    
    public static void main(){
    	String aa="-1";
    	System.out.println(Integer.valueOf(aa));
    }
}
