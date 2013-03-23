package net.rootls.dao.mapper;

import net.rootls.model.UserData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-15
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
public class UserDataMapper implements RowMapper<UserData> {
    @Override
    public UserData mapRow(ResultSet rs, int i) throws SQLException {
        return new UserData(
                rs.getLong("id"),
                rs.getLong("uid"),
                rs.getString("product_code"),
                rs.getString("product_name")
        );
    }
}
