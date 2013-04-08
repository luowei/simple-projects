package com.rootls.dao.mapper;

import com.rootls.model.IDePrice;
import com.rootls.model.IDePrice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-22
 * Time: 下午4:01
 * To change this template use File | Settings | File Templates.
 */
public class IDePriceMapper implements RowMapper<IDePrice> {
    @Override
    public IDePrice mapRow(ResultSet rs, int i) throws SQLException {
            return new IDePrice(
                    rs.getInt("ganglian_id"),
                    rs.getDate("price_date"),
                    rs.getString("productName"),
                    rs.getString("modelName"),
                    rs.getString("area_Name"),
                    rs.getString("market_Name"),
                    rs.getString("manufacture_Name"),
                    rs.getFloat("low_end_price"),
                    rs.getFloat("high_end_price"),
                    rs.getFloat("Average"),
                    rs.getString("memo"),
                    rs.getFloat("Change_Rate"),
                    rs.getString("units")
            ).setLid(rs.getInt("lz_DomesticMarketProduct_Id"));
    }
}
