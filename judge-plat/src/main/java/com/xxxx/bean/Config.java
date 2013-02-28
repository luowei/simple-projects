package com.xxxx.bean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-11
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
public class Config implements ServletContextListener {

    public static String properties_fileName = getConfigPath();

    private static String getConfigPath() {
        String configFilePath = Config.class.getClassLoader().getResource("config.properties").getPath().substring(1);
        // 判断系统 linux，windows
        if ("\\".equals(File.separator)) {
            configFilePath = configFilePath.replace("%20", " ");
        } else if ("/".equals(File.separator)) {
            configFilePath = "/" + configFilePath.replace("%20", " ");
        }
        return configFilePath;
    }

    public static String UP = "up";
    public static String DOWN = "down";

    public static Integer PAGE_SIZE = 20;
    public static Integer PAGEBAR_SIZE = 10;

    //一周多少天
    public static Integer WEEK = 7;

    //涨跌幅显示记录数
    public static Integer WEEK_ITEMS = 10;

    //首页滚动信息数
    public static Integer MARQUEE_LISTSIZE = 8;

    //新闻图片domain
    public static String NEWS_IMG_DOMAIN = "http://www.xxxx.net";

    //大宗商品新闻链接
    public static String NEWS_URL = "http://www.xxxx.com/bulk/108_%s.html";

    //头条新内容长度
    public static Integer HEADNEWS_CONTENT_LEN = 120;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        Map<String, String> map = PropertiesUtil.getAllProperty();
        String up = map.get("UP");
        UP = (up != null && up != "") ? up : UP;

        String down = map.get("DOWN");
        DOWN = (down != null && down != "") ? down : DOWN;


        String news_domain = map.get("NEWS_IMG_DOMAIN");
        NEWS_IMG_DOMAIN = (news_domain != null && news_domain != "") ? news_domain : NEWS_IMG_DOMAIN;

        String news_url = map.get("NEWS_URL");
        NEWS_URL = (news_url != null && news_url != "") ? news_url : NEWS_URL;

        Integer headnews_content_len = Integer.valueOf(map.get("HEADNEWS_CONTENT_LEN"));
        HEADNEWS_CONTENT_LEN = (headnews_content_len != null && headnews_content_len.intValue() >= 0) ? headnews_content_len : HEADNEWS_CONTENT_LEN;

        Integer week = Integer.valueOf(map.get("WEEK"));
        WEEK = (week != null && week.intValue() >= 0) ? week : WEEK;

        Integer week_items = Integer.valueOf(map.get("WEEK_ITEMS"));
        WEEK_ITEMS = (week_items != null && week_items.intValue() >= 0) ? week_items : WEEK_ITEMS;

        Integer marquee_listsize = Integer.valueOf(map.get("MARQUEE_LISTSIZE")).intValue();
        MARQUEE_LISTSIZE = (marquee_listsize != null && marquee_listsize.intValue() >= 0) ? marquee_listsize : MARQUEE_LISTSIZE;

        Integer page_size = Integer.valueOf(map.get("PAGE_SIZE"));
        PAGE_SIZE = (page_size != null && page_size.intValue() >= 0) ? page_size : PAGE_SIZE;

        Integer pagebar_size = Integer.valueOf(map.get("PAGEBAR_SIZE"));
        PAGEBAR_SIZE = (pagebar_size != null && pagebar_size.intValue() >= 0) ? pagebar_size : PAGEBAR_SIZE;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
