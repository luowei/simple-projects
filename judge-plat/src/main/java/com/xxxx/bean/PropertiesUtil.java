package com.xxxx.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-5
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class PropertiesUtil {

    static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static  Properties props;
    private static String fileName = Config.properties_fileName;

    private static void readProperties() {
        FileInputStream fis = null;
        try {
            if(props==null){
                props = new Properties();
            }
            fis = new FileInputStream(fileName);
            props.load(fis);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 获取某个属性
     */
    public static String getProperty(String key) {
        readProperties();
        return props.getProperty(key);
    }

    /**
     * 获取所有属性，返回一个map,不常用
     * 可以试试props.putAll(t)
     */
    public static Map<String,String> getAllProperty() {
        Map<String,String> map = new HashMap<String,String>();
        readProperties();
        Enumeration enu = props.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = props.getProperty(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 在控制台上打印出所有属性，调试时用。
     */
    public static void printProperties() {
        props.list(System.out);
    }

    /**
     * 写入properties信息
     */
    public static Boolean writeProperties(String key, String value) {
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            props.setProperty(key, value);
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "『comments』Update key：" + key);
            return true;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
//    public static void main(String[] args) {
//        PropertiesUtil util=new PropertiesUtil("config.properties");
//        System.out.println("ip=" + util.getProperty("ip"));
//        util.writeProperties("key", "value0");
//    }
}
