package net.rootls.dao.mapper;

import net.rootls.model.IDePrice;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-22
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
public class IDePriceExtractor implements ResultSetExtractor<IDePrice> {
    @Override
    public IDePrice extractData(ResultSet rs) throws SQLException, DataAccessException {
        if(rs.next()){
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
        return null;
    }
}
