package com.other.export.zsy;

import com.oilchem.dao.impl.BaseDaoImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by luowei on 2014/9/18.
 */
@Component
public class ZsyRepository extends BaseDaoImpl<Zsy> {

    public Zsy findByIdAndPriceDate(final Integer id, final String priceDate) {

        final String sql = "select top 1 lz_DomesticMarketProduct_Id,Average from lz_DomesticMarketPrice " +
                " where lz_DomesticMarketProduct_Id=? and price_date=? ";
        return getJdbcTemplate().query(new PreparedStatementCreator() {
                                           @Override
                                           public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                                               PreparedStatement ps = con.prepareStatement(sql);
                                               ps.setInt(1, id);
                                               ps.setString(2, priceDate);
                                               return ps;
                                           }
                                       },
                new ResultSetExtractor<Zsy>() {
                    @Override
                    public Zsy extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (rs.next()) {
                            Zsy zsy = new Zsy(
                                    rs.getInt("lz_DomesticMarketProduct_Id"),
                                    rs.getBigDecimal("Average")
                            );
                        }
                        return null;
                    }
                }
        );

    }

}
