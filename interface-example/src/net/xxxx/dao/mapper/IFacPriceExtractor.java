package net.xxxx.dao.mapper;

import net.xxxx.model.IFacPrice;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-22
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class IFacPriceExtractor implements ResultSetExtractor<IFacPrice> {
    @Override
    public IFacPrice extractData(ResultSet rs) throws SQLException, DataAccessException {
        if(rs.next()){
            return new IFacPrice(
                    rs.getInt("ganglian_id"),
                    rs.getDate("price_date"),
                    rs.getString("ProductName"),
                    rs.getString("modelName"),
                    rs.getString("manufacture_Name"),
                    rs.getFloat("exfactory_price"),
                    rs.getString("memo"),
                    rs.getFloat("Change_Rate"),
                    rs.getString("area_Name"),
                    rs.getString("sale_company_name"),
                    rs.getString("units")
            );
        }
        return null;
    }
}
