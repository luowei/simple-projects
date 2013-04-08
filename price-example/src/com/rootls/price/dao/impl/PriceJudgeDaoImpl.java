package com.rootls.price.dao.impl;

import com.rootls.price.dao.PriceJudgeDao;
import com.rootls.price.model.PriceJudge;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PriceJudgeDaoImpl extends BaseDaoImpl<PriceJudge> implements PriceJudgeDao {


    class PriceJudgRowMapper implements RowMapper<PriceJudge> {

        public PriceJudge mapRow(ResultSet rs, int rowNum) throws SQLException {
            PriceJudge priceJudge = new PriceJudge(
                    rs.getInt("id"),
                    rs.getInt("product_id"),
                    rs.getInt("product_model_id"),
                    rs.getInt("is_display"),
                    rs.getString("product_name"),
                    rs.getString("model_name"),
                    rs.getString("area_name"),
                    rs.getString("unit")
            );
            return priceJudge;
        }
    }
}
