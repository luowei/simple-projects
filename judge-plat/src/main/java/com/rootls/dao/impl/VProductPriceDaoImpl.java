package com.rootls.dao.impl;

import com.rootls.bean.Config;
import com.rootls.bean.Page;
import com.rootls.dao.VProductPriceDao;
import com.rootls.domain.VProductPrice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.rootls.bean.Config.WEEK;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-4
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
@Component
public class VProductPriceDaoImpl extends BaseDaoImpl<VProductPrice> implements VProductPriceDao {


    public Page<VProductPrice> list(String name, String area, Page page) {


        Integer pageNumber = page.getPageNumber();
        Integer pageSize = page.getPageSize();
        String defaultOrder = page.getOrder() != null ? page.getOrder() : "price_date desc";
//        String createTempTable = "select top "+pageSize+" payId into #tmpTable from v_front_judge_price order by "+defaultOrder;

        int startRow = (pageNumber - 1) * pageSize;
        int endRow = pageNumber * pageSize;

        StringBuilder tempSql = new StringBuilder(
                "select * from (select * ,ROW_NUMBER() over(order by " + defaultOrder + " ) as row_number " +
                        " from v_front_judge_price vpp "
        );
        StringBuilder countSql = new StringBuilder("select count(*) from v_front_judge_price");
        if (name != null && name != "") {
            tempSql.append(" where product_name= '" + name + "' ");
            countSql.append(" where product_name = '" + name + "' ");
            if (area != null && area != "") {
                tempSql.append(" and area_name= '" + area + "'");
                countSql.append(" and area_name= '" + area + "'");
            }

        }

        tempSql.append(
                " ) product_price where row_number >" + startRow + " and row_number <= " + endRow + " order by " + defaultOrder
        );

        int total = getJdbcTemplate().queryForInt(countSql.toString());

        List<VProductPrice> vProductPriceList = getJdbcTemplate().query(tempSql.toString(), new ProductPriceRowMapper());

        return new Page<VProductPrice>(pageNumber, pageSize, total,defaultOrder,vProductPriceList);
    }


    public List<String> getAreaList(String name) {
        StringBuilder sql = new StringBuilder(" select distinct area_Name from v_front_judge_price ");
        if (name != null && name != "") {
            sql.append(" where product_name = '" + name + "'");
        }
        return getJdbcTemplate().queryForList(sql.toString(), String.class);
    }


    public List<VProductPrice> weekUpTopChangeList() {
        String sql = "select top " + Config.WEEK_ITEMS +"  * " +
                " from v_front_judge_price " +
                " where price_date > (getdate()-" + WEEK + ") " +
                " and change_rate > 0 " +
                " order by change_rate desc ";
        return getJdbcTemplate().query(sql, new ProductPriceRowMapper());
    }


    public List<VProductPrice> weekDownTopChangeList() {
        String sql = "select top " + Config.WEEK_ITEMS +"  * " +
                " from v_front_judge_price " +
                " where price_date > (getdate()-" + WEEK + ") " +
                " and change_rate < 0 " +
                " order by change_rate asc ";
        return getJdbcTemplate().query(sql, new ProductPriceRowMapper());
    }


    public List<VProductPrice> marqueeList() {
        String sql = "select top " + Config.MARQUEE_LISTSIZE +"  * " +
                " from v_front_judge_price " +
                " where  change_rate is not null  " +
                " order by price_date desc  ";
        return getJdbcTemplate().query(sql,new ProductPriceRowMapper());
    }

    @Override
    public List<VProductPrice> nearyThirtyDays(Integer priceJudgeId) {

        String sql = " select * from v_front_judge_price " +
                " where  change_rate is not null  " +
                " and price_judge_id = "+priceJudgeId+
                " order by price_date asc  ";

        return getJdbcTemplate().query(sql,new ProductPriceRowMapper());
    }

    public static class ProductPriceRowMapper implements RowMapper<VProductPrice> {

        public VProductPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
            VProductPrice vProductPrice = new VProductPrice(
                    rs.getString("product_name"),
                    rs.getString("area_name"),
                    rs.getInt("price_judge_id"),
                    rs.getBigDecimal("price"),
                    rs.getString("unit"),
                    rs.getBigDecimal("change_rate"),
                    rs.getTimestamp("price_date"),
                    rs.getString("model_name")
            );
            return vProductPrice;
        }
    }
}
