package com.other.common;

import com.other.bean.City;
import com.other.bean.DzGroup;
import com.other.bean.SmsUser;
import com.other.bean.Corpo;
import com.other.bean.Prod;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 14-1-26
 * Time: 上午11:32
 * To change this template use File | Settings | File Templates.
 */
public class InerCache {
    Logger logger = Logger.getLogger(InerCache.class);

    static Long inerCache_expire = 24 * 3600 * 1000L;

    Map<Integer, DzGroup> groupIdMap;
    Map<Integer, SmsUser> smsUserMap;
    Map<Integer, Prod> prodMap;
    Map<Integer, Corpo> corpoMap;
    Map<Integer,City> cityMap;

    Long initTime;
    static InerCache inerCache;

    public synchronized void init() {
        if (inerCache == null) {
            inerCache = new InerCache();
        }

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(BasicDataSource.class.cast(getCurrentWebApplicationContext().getBean("dataSource")));

            String sql = " select GroupID,GroupShowName,GroupPath,ParentID,YearSendAmount from ET_SmsUserGroup ";
            List<DzGroup> groupList = jdbcTemplate.query(sql, new RowMapper<DzGroup>() {
                @Override
                public DzGroup mapRow(ResultSet rs, int i) throws SQLException {
                    DzGroup dzGroup = new DzGroup(
                            rs.getInt("GroupID"),
                            rs.getString("GroupShowName"),
                            null
                    );
                    String path = rs.getString("GroupPath");
                    path = path.substring(3, path.length() - 3).replace("///", "->");
                    dzGroup.setGroupPath(path);
                    dzGroup.setParentId(rs.getInt("ParentID"));
                    Float chengben = rs.getFloat("YearSendAmount");
                    dzGroup.setPriceChengben(chengben != null ? chengben.floatValue() * 0.05f : null);
                    return dzGroup;
                }
            });

            for (DzGroup group : groupList) {
                String countSql = " select count(*) from ET_SmsUserGroup where ParentID=" + group.getId();
                if (jdbcTemplate.queryForInt(countSql) > 0) {
                    group.setParent(true);
                }
            }

            inerCache.groupIdMap = new HashMap<Integer, DzGroup>(1000);
            for (DzGroup group : groupList) {
                inerCache.groupIdMap.put(group.getId(), group);
            }
            logger.info("################# group's (id,group) map size :" + groupIdMap.size() + " #################");

            // ---------smsUser-----------
            initSmsUserMap(jdbcTemplate);


            JdbcTemplate tradeJdbcTemplate = new JdbcTemplate(BasicDataSource.class.cast(getCurrentWebApplicationContext().getBean("tradedataSource")));
            //--------------------------------------product--------------------------
            String productSql = " select * from Sys_Product ";
            List<Prod> prodList = tradeJdbcTemplate.query(productSql, new RowMapper<Prod>() {
                @Override
                public Prod mapRow(ResultSet rs, int i) throws SQLException {

                    String path = rs.getString("ProductPath");
                    path = path.length() > 3 ? path.substring(3, path.length() - 3).replace("///", "->") : path;
                    Prod prod = new Prod(
                            rs.getInt("ProductID"),
                            rs.getInt("ParentID"),
                            rs.getString("ProductName"),
                            path
                    );
                    return prod;
                }
            });

            inerCache.prodMap = new HashMap<Integer, Prod>(30000);
            for (Prod prod : prodList) {
                if(prod!=null){
                    inerCache.prodMap.put(prod.getId(), prod);
                }
            }
            logger.info("################# Prod's (id,Prod) map size :" + prodMap.size() + " #################");

            //-----------------------------corporation----------------------
            String corpoSql = " select * from Sys_Corporation ";
            List<Corpo> corpoList = tradeJdbcTemplate.query(corpoSql, new RowMapper<Corpo>() {
                @Override
                public Corpo mapRow(ResultSet rs, int i) throws SQLException {
                    if(rs.next()){
                        Corpo corpo = new Corpo(
                                rs.getInt("Corporation_ID"),
                                rs.getString("Corporation_Name")
                        );
                        return corpo;
                    }
                    return null;
                }
            });

            inerCache.corpoMap = new HashMap<Integer, Corpo>(30000);
            for (Corpo corpo : corpoList) {
                if(corpo!=null){
                    inerCache.corpoMap.put(corpo.getId(), corpo);
                }
            }
            logger.info("################# corpo's (id,corpo) map size :" + corpoMap.size() + " #################");

            //-----------------------------corporation----------------------
            String citySql = " select * from CITY ";
            List<City> cityList = tradeJdbcTemplate.query(citySql, new RowMapper<City>() {
                @Override
                public City mapRow(ResultSet rs, int i) throws SQLException {
                        return new City(
                                rs.getInt("CityID"),
                                rs.getString("CityName"),
                                rs.getString("ParentID"),
                                rs.getString("CityClassStr")
                        );
                    }
            });

            inerCache.cityMap = new HashMap<Integer, City>(30000);
            for (City city : cityList) {
                if(city!=null){
                    inerCache.cityMap.put(city.getId(), city);
                }
            }
            logger.info("################# city's (id,city) map size :" + cityMap.size() + " #################");

            inerCache.initTime = System.currentTimeMillis();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initSmsUserMap(JdbcTemplate jdbcTemplate) {
        //-------------------smsUser--------------------
        String smsUserSql = " select user_infoid,user_smsid from LZ_SMSUser ";
        List<SmsUser> smsUserList = jdbcTemplate.query(smsUserSql, new RowMapper<SmsUser>() {
            @Override
            public SmsUser mapRow(ResultSet rs, int i) throws SQLException {
                SmsUser smsUser = new SmsUser(
                        rs.getInt("user_infoid"),
                        rs.getInt("user_smsid")
                );
                return smsUser;
            }
        });

        inerCache.smsUserMap = new HashMap<Integer, SmsUser>(30000);
        for (SmsUser user : smsUserList) {
            inerCache.smsUserMap.put(user.getgId(), user);
        }
        logger.info("################# smsUser's (id,smsUser) map size :" + smsUserMap.size() + " #################");
    }

    public static Map<Integer, DzGroup> getGroupMap() {
        if (inerCache == null) {
            inerCache = new InerCache();
        }
        Long userMapTime = System.currentTimeMillis() - inerCache.initTime;
        if (inerCache.groupIdMap == null || inerCache.groupIdMap.isEmpty() ||
                userMapTime > inerCache_expire) {
            inerCache.init();
        }
        return inerCache.groupIdMap;
    }

    public static Map<Integer, Prod> getProdMap() {
        if (inerCache == null) {
            inerCache = new InerCache();
        }
        Long userMapTime = System.currentTimeMillis() - inerCache.initTime;
        if (inerCache.prodMap == null || inerCache.prodMap.isEmpty() ||
                userMapTime > inerCache_expire) {
            inerCache.init();
        }
        return inerCache.prodMap;
    }

    public static Map<Integer, Corpo> getCorporMap() {
        if (inerCache == null) {
            inerCache = new InerCache();
        }
        Long userMapTime = System.currentTimeMillis() - inerCache.initTime;
        if (inerCache.corpoMap == null || inerCache.corpoMap.isEmpty() ||
                userMapTime > inerCache_expire) {
            inerCache.init();
        }
        return inerCache.corpoMap;
    }

    public static Map<Integer, SmsUser> getSmsUserMap() {
        if (inerCache == null) {
            inerCache = new InerCache();
        }
//        Long userMapTime = System.currentTimeMillis() - inerCache.initTime;
//        if (inerCache.smsUserMap == null || inerCache.smsUserMap.isEmpty() ||
//                userMapTime > inerCache_expire) {
//            inerCache.init();
//        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(BasicDataSource.class.cast(getCurrentWebApplicationContext().getBean("dataSource")));
        inerCache.initSmsUserMap(jdbcTemplate);
        return inerCache.smsUserMap;
    }

    public static Map<Integer, City> getCityMap() {
        if (inerCache == null) {
            inerCache = new InerCache();
        }
        Long userMapTime = System.currentTimeMillis() - inerCache.initTime;
        if (inerCache.cityMap == null || inerCache.cityMap.isEmpty() ||
                userMapTime > inerCache_expire) {
            inerCache.init();
        }
        return inerCache.cityMap;
    }

    private InerCache() {
        initTime = System.currentTimeMillis();
    }

    public static void clearCache() {
        if (inerCache != null) {
            inerCache.groupIdMap = null;
            inerCache.smsUserMap = null;
        }
        inerCache = null;
        System.gc();
    }

}