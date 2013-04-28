package com.rootls.bean;

import com.jolbox.bonecp.BoneCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-11
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
public class Config implements ServletContextListener {

    public static JdbcTemplate jdbcTemplate;

    public static Map<String,Long> loginCache;
    public static Integer PAGE_SIZE = 20;

    public static String CALL_LIST_URL = "http://122.5.105.50:8000/Application/CallLogJson.ashx";
    public static String USERNAME = "lz_admin";
    public static String PASSWORD = "lz20130327";


    public static String properties_fileName = getConfigPath();
//    public static String JSON_MSG = "{\"code\":%d,\"msg\":\"%s\"}";
    public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static Integer DEFAULT_CURRENT = 1;
    public static Integer GETCALLLOG_IDEL = 1800000;
    public static String STARTID = "0";
    public static Boolean ENABLE_AUTO = true;


    public static JdbcTemplate getJdbcTemplate(){
        if(jdbcTemplate==null){
            jdbcTemplate = new JdbcTemplate(BoneCPDataSource.class.cast(getCurrentWebApplicationContext().getBean("interfaceDataSource")));
        }
        return jdbcTemplate;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        loginCache = new HashMap<String, Long>();

        Map<String, String> map = PropertiesUtil.getAllProperty();
        if (map == null || map.isEmpty()) {
            return;
        }

        String dateFormat = map.get("DATE_FORMAT");
        DATE_FORMAT = (dateFormat != null && dateFormat !="")?dateFormat:DATE_FORMAT;

        String callListUrl = map.get("CALL_LIST_URL");
        CALL_LIST_URL = (callListUrl != null && callListUrl !="")?callListUrl:CALL_LIST_URL;

        String username = map.get("USERNAME");
        USERNAME = (username != null && username !="")?username:USERNAME;

        String startId = map.get("STARTID");
        STARTID = (startId != null && startId !="")?startId:STARTID;

        String password = map.get("PASSWORD");
        PASSWORD = (password != null && password !="")?password:PASSWORD;

        Integer defaultCurrent = Integer.valueOf(map.get("DEFAULT_CURRENT"));
        DEFAULT_CURRENT = (defaultCurrent != null) ? defaultCurrent : DEFAULT_CURRENT;

        Integer getcalllogIdel = Integer.valueOf(map.get("GETCALLLOG_IDEL"));
        GETCALLLOG_IDEL = (getcalllogIdel != null) ? getcalllogIdel : GETCALLLOG_IDEL;

        Boolean enableAuto = Boolean.valueOf(map.get("ENABLE_AUTO"));
        ENABLE_AUTO = (enableAuto != null) ? enableAuto : ENABLE_AUTO;
    }


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

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    /**
     * 读取properties配置文件.
     * User: luowei
     * Date: 13-2-5
     * Time: 下午2:39
     * To change this template use File | Settings | File Templates.
     */
    public abstract static class PropertiesUtil {

        static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

        private static Properties props;
        private static String fileName = properties_fileName;

        private static void readProperties() {
            FileInputStream fis = null;
            try {
                if (props == null) {
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
        public static Map<String, String> getAllProperty() {
            Map<String, String> map = new HashMap<String, String>();
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
}
