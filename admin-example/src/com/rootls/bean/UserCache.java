package com.rootls.bean;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rootls.bean.Config.USERCACHE_EXPIRE;
import static org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-6
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
public class UserCache{

    Map<Integer,UidName> userIdMap;
    Long initTime;
    static UserCache userCache;

    public synchronized void init(){
        if(userCache==null){
            userCache = new UserCache();
        }

        try {
            String sql = " select * from Lz_Admin ";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(BasicDataSource.class.cast(getCurrentWebApplicationContext().getBean("dataSource")));
            List<UidName> uidNameList = jdbcTemplate.query(sql, new RowMapper<UidName>() {
                @Override
                public UidName mapRow(ResultSet rs, int i) throws SQLException {
                    UidName uidName = new UidName(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("truename")
                    );
                    uidName.setQPName(rs.getString("name_jp"));
                    uidName.setJPName(rs.getString("name_qp"));
                    return uidName;
                }
            });

            userCache.userIdMap = new HashMap<Integer, UidName>(1000);
            for (UidName user:uidNameList){
                userCache.userIdMap.put(user.getAdminId(),user);
            }
            userCache.initTime = System.currentTimeMillis();
            System.out.println("################# user's (id,user) map size :"+userIdMap.size()+" #################");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Map<Integer,UidName> getUserMap(){
        if(userCache==null){
            userCache = new UserCache();
        }
        Long userMapTime = System.currentTimeMillis()-userCache.initTime;
        if(userCache.userIdMap==null || userCache.userIdMap.isEmpty() || userMapTime > USERCACHE_EXPIRE){
            userCache.init();
        }
        return userCache.userIdMap;
    }

    private UserCache() {
        initTime = System.currentTimeMillis();
    }

    public static class UidName {
        private int adminId;
        private String userName;
        private String realName;
        private String qPName;
        private String jPName;

        public UidName() {
        }

        public UidName(int adminId, String userName,String realName) {
            this.adminId = adminId;
            this.userName = userName;
            this.realName = realName;
        }

        public int getAdminId() {
            return adminId;
        }

        public void setAdminId(int adminId) {
            this.adminId = adminId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getQPName() {
            return qPName;
        }

        public void setQPName(String qPName) {
            this.qPName = qPName;
        }

        public String getJPName() {
            return jPName;
        }

        public void setJPName(String jPName) {
            this.jPName = jPName;
        }
    }
}
