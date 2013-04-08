package com.rootls.price.bean;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.servlet.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-6
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
public class UserCache implements Filter {

    public static Map<Integer,UidName> userIdMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("**************  usercache init  ********************");
    }

    @Override
    public synchronized void  doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) {

        try {
            String sql = " select lz_Staff_id,lz_Staff_LoginName,lz_Staff_RealName from lz_Staff ";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(BasicDataSource.class.cast(getCurrentWebApplicationContext().getBean("ds")));
            List<UidName> uidNameList = jdbcTemplate.query(sql, new RowMapper<UidName>() {
                @Override
                public UidName mapRow(ResultSet rs, int i) throws SQLException {
                    return new UidName(
                            rs.getInt("lz_Staff_id"),
                            rs.getString("lz_Staff_LoginName"),
                            rs.getString("lz_Staff_RealName")
                    );
                }
            });

            userIdMap = new HashMap<Integer, UidName>(1000);
            for (UidName user:uidNameList){
                userIdMap.put(user.getAdminId(),user);
            }

            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println("################# user's (id,user) map size :"+userIdMap.size()+" #################");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public void destroy() {
    }

    public static class UidName {
        private int adminId;
        private String userName;
        private String realName;

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
    }
}
