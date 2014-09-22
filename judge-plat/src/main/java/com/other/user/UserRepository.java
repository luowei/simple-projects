package com.other.user;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by luowei on 2014/9/4.
 */
@Component
public class UserRepository {

    @Resource(name = "pcdbJdbcTemplate")
    JdbcTemplate jdbcTemplate;


    public UserInfo findUserByUser(UserInfo user) {
        String sql = "select top 1 id,[name],TmpPwd,trial,endtime,qymc from [guestbook] where " +
                "passport_parentid=" + user.getParentId() + " and id=" + user.getUserId() +
                " and TmpPwd='" + user.getPassWord() + "'";
        return jdbcTemplate.query(sql, new ResultSetExtractor<UserInfo>() {
            @Override
            public UserInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    UserInfo user = new UserInfo(
                            rs.getInt("trial"),
                            rs.getString("qymc"),
                            rs.getDate("endtime")
                    );
                    user.setUsername(rs.getString("name"));
                    return user;
                }

                return null;
            }
        });
    }
}
