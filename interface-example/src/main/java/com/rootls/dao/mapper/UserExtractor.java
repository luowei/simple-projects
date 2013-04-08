package com.rootls.dao.mapper;

import com.rootls.model.User;
import com.rootls.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-25
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class UserExtractor  implements ResultSetExtractor<User> {

    @Override
    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
        if(rs.next()){
             return new User(
                     rs.getInt("id"),
                     rs.getString("username"),
                     rs.getString("userpwd"),
                     rs.getString("ipright"),
                     rs.getString("lastip"),
                     rs.getDate("lasttime"),
                     rs.getInt("logintimes"),
                     rs.getInt("price"),
                     rs.getDate("price_startdate"),
                     rs.getDate("price_enddate")
            ).setStartYearMonth(rs.getString("start_yearmonth"))
                     .setEndYearMonth(rs.getString("end_yearmonth"));
        }
        return null;
    }
}
