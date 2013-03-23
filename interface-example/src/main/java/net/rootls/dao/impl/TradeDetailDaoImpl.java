package net.rootls.dao.impl;

import net.rootls.dao.TradeDetailDao;
import net.rootls.dao.mapper.UserDataMapper;
import net.rootls.model.TradeDetail;
import net.rootls.model.User;
import net.rootls.model.UserData;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-11
 * Time: 下午5:28
 * To change this template use File | Settings | File Templates.
 */
@Component
public class TradeDetailDaoImpl implements TradeDetailDao {


    @Resource(name = "lzdbJdbcTemplate")
    JdbcTemplate lzdbjdbcTemplate;

    @Override
    public List<TradeDetail> list(String listSql, RowMapper<TradeDetail> rowMapper) {
        return lzdbjdbcTemplate.query(listSql, rowMapper);
    }

    @Override
    public Integer getTotalElements(String countQuerySql) {
        return lzdbjdbcTemplate.queryForInt(countQuerySql);
    }

    @Override
    public List<UserData> listPCode(User user) {
        String sql = "select * from t_user_detail where uid = " + user.getId();
        return lzdbjdbcTemplate.query(sql, new UserDataMapper());
    }

    @Override
    public void updatePCode(UserData userData) {
        String productName = userData.getProductName();
        String sql = " update t_user_detail set product_code='" + userData.getProductCode() +
                "',product_name='" + (productName==null?"":productName) + "' where id =" + userData.getId();
        lzdbjdbcTemplate.execute(sql);
    }

    @Override
    public String getProductNameByCode(String productCode) {
        String sql = " select product_name from t_product where product_code='" + productCode + "'";
        return lzdbjdbcTemplate.query(sql, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return rs.getString("product_name");
                }
                return null;
            }
        });
    }

    @Override
    public void addPCode(UserData userData) {
        String productName = userData.getProductName();
        String sql = " insert into t_user_detail(uid,product_code,product_name) " +
                "values(" + userData.getUid() + ",'" + userData.getProductCode() + "','" + (productName==null?"":productName) + "')";
        lzdbjdbcTemplate.execute(sql);
    }

    @Override
    public boolean exsitPCode(String productCode) {
        String sql = "select count(id) from t_user_detail where product_code='" + productCode + "' ";
        if (lzdbjdbcTemplate.queryForInt(sql) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void delPCode(Integer id) {
        lzdbjdbcTemplate.execute("delete from t_user_detail where id = "+id);
    }
}
