package com.rootls.dao.mapper;

import com.rootls.model.TradeDetail;
import com.rootls.model.TradeDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-11
 * Time: 下午5:18
 * To change this template use File | Settings | File Templates.
 */
public class TradeDetailMapper  implements RowMapper<TradeDetail> {


    @Override
    public TradeDetail mapRow(ResultSet rs, int i) throws SQLException {

        return new TradeDetail(
                rs.getLong("id"),
                rs.getInt("col_year"),
                rs.getInt("col_month"),
                rs.getString("year_month"),
                rs.getString("product_code"),
                rs.getString("product_name"),
                rs.getString("product_type"),
                rs.getInt("type_code"),
                rs.getString("company_type"),
                rs.getString("trade_type"),
                rs.getString("transportation"),
                rs.getString("customs"),
                rs.getString("city"),
                rs.getString("country"),
                rs.getBigDecimal("amount"),
                rs.getString("unit"),
                rs.getBigDecimal("amount_money"),
                rs.getBigDecimal("unit_price")
        );
    }
}
