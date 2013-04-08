package com.rootls.dao.mapper;

import com.rootls.model.IBOPrice;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-22
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
public class IBOPriceExtractor implements ResultSetExtractor<IBOPrice> {
    @Override
    public IBOPrice extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (rs.next()) {
            return new IBOPrice(
                    rs.getInt("ganglian_id"),
                    rs.getDate("priceDate"),
                    rs.getString("productName"),
                    rs.getString("ModelName"),
                    rs.getString("AreaName"),
                    rs.getString("MarketName"),
                    rs.getFloat("Price_CNPC"),
                    rs.getFloat("Price_Sinopec"),
                    rs.getFloat("Price_Market"),
                    rs.getFloat("Price_zzj"),
                    rs.getFloat("Price_lsj"),
                    rs.getFloat("Change_Rate"),
                    rs.getString("unit"),
                    rs.getString("memo")
            ).setLid(rs.getInt("lz_DomesticMarketOilProduct_ID"));
        }
        return null;
    }
}
