package com.other.util;

import org.apache.commons.dbcp.BasicDataSource;
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
 * Date: 14-1-21
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
public class OtherCache {

    Map<Integer, Type> typeIdMap;
    Long initTime;
    static OtherCache otherCache;

    public synchronized void init() {
        if (otherCache == null) {
            otherCache = new OtherCache();
        }

        try {
            String sql = " select * from dz_type ";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(BasicDataSource.class.cast(getCurrentWebApplicationContext().getBean("pcdbdataSource")));
            List<Type> typeList = jdbcTemplate.query(sql, new RowMapper<Type>() {
                @Override
                public Type mapRow(ResultSet rs, int i) throws SQLException {
                    Type type = new Type(
                            rs.getInt("id"),
                            rs.getString("typeName")
                    );
                    return type;
                }
            });

            otherCache.typeIdMap = new HashMap<Integer, Type>(1000);
            for (Type user : typeList) {
                otherCache.typeIdMap.put(user.getId(), user);
            }
            otherCache.initTime = System.currentTimeMillis();
            System.out.println("################# dz_type's (id,type) map size :" + typeIdMap.size() + " #################");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //一天
    public static Long CACHE_EXPIRE = 24 * 3600 * 1000L;

    public static Map<Integer, Type> getTypeMap() {
        if (otherCache == null) {
            otherCache = new OtherCache();
        }
        Long userMapTime = System.currentTimeMillis() - otherCache.initTime;
        if (otherCache.typeIdMap == null || otherCache.typeIdMap.isEmpty() ||
                userMapTime > CACHE_EXPIRE) {
            otherCache.init();
        }
        return otherCache.typeIdMap;
    }

    public static Map<Integer, Type> getUserMapByFlag(String flag) {
        if (otherCache == null) {
            otherCache = new OtherCache();
        }
        Long userMapTime = System.currentTimeMillis() - otherCache.initTime;
        if (otherCache.typeIdMap == null || otherCache.typeIdMap.isEmpty() ||
                userMapTime > CACHE_EXPIRE || (flag != null && "true".equals(flag))) {
            otherCache.init();
        }
        return otherCache.typeIdMap;
    }

    private OtherCache() {
        initTime = System.currentTimeMillis();
    }

    public static class Type {
        Integer id;
        String typeName;

        public Type(Integer id, String typeName) {
            this.id = id;
            this.typeName = typeName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }

}
