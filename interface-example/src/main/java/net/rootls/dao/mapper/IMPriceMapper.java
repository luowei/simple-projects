package net.rootls.dao.mapper;

import net.rootls.model.IMPrice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-22
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class IMPriceMapper implements RowMapper<IMPrice> {
    @Override
    public IMPrice mapRow(ResultSet rs, int i) throws SQLException {
            return new IMPrice(
                    rs.getInt("ganglian_id"),
                    rs.getDate("price_Date"),
                    rs.getString("ProductName"),
                    rs.getString("modelName"),
                    rs.getString("area_Name"),
                    rs.getString("price_type"),
                    rs.getFloat("low_end_price"),
                    rs.getFloat("high_end_price"),
                    rs.getFloat("mid_end_price"),
                    rs.getString("memo"),
                    rs.getFloat("RMB_price"),
                    rs.getFloat("Change_Rate"),
                    rs.getString("unit")
            ).setLid(rs.getInt("lz_International_market_product_id"));
    }
}
