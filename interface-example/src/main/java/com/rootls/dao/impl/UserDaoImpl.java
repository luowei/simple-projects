package com.rootls.dao.impl;

import com.rootls.model.User;
import com.rootls.dao.UserDao;
import com.rootls.dao.mapper.UserExtractor;
import com.rootls.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-25
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserDaoImpl implements UserDao {

    @Resource(name = "interfaceJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    @Override
    public User findUserById(Integer userId) {
        String sql = " select * from Lz_Interface_User where id="+userId;
        return jdbcTemplate.query(sql,new UserExtractor());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = " select * from Lz_Interface_User where username='"+username+"' and userpwd='"+password+"' ";
        return jdbcTemplate.query(sql,new UserExtractor());
    }

    @Override
    public boolean updateUserPassword(String userName, String newPassword) {
        String sql = " update Lz_Interface_User set userpwd='"+newPassword+"' ";
        final String fSql = sql;
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(fSql);
            }
        };
        return jdbcTemplate.update(psc)< 0 ? false : true;
    }

    @Override
    public void updateLoginInfo(User user, String clientIp) {
        String sql = " update Lz_Interface_User set lastip='"+clientIp+"',logintimes=logintimes+1,lasttime=GETDATE() where id="+user.getId();
        final String fSql = sql;
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(fSql);
            }
        };
        jdbcTemplate.update(psc);
    }
}
