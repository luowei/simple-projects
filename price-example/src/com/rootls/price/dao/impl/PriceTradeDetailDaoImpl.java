package com.rootls.price.dao.impl;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.dao.PriceTradeDetailDao;
import com.rootls.price.model.PriceTraderDetail;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PriceTradeDetailDaoImpl extends BaseDaoImpl<PriceTraderDetail> implements PriceTradeDetailDao {

    Date globlePriceDate = new Date();
    String globleDate = new SimpleDateFormat("yyyy-MM-dd").format(globlePriceDate);


    @Override
    public Page<PriceTraderDetail> listPage(Page page, CommonDto commonDto) {

        Integer pageNumber = page.getPageNumber();
        Integer pageSize = page.getPageSize();
        String defaultOrder = page.getOrder() != null ? page.getOrder() : "price_date desc";

        int startRow = (pageNumber - 1) * pageSize;
        int endRow = pageNumber * pageSize;

        String name = commonDto.getProductName();
        String area = commonDto.getAreaName();
        String modelName = commonDto.getModelName();
        String marketName = commonDto.getMarketName();
        String traderName = commonDto.getTraderName();
        Date priceDate = commonDto.getPriceDate();

        StringBuilder tempSql = new StringBuilder(
                "select * from (select * ,ROW_NUMBER() over(order by " + defaultOrder + " ) as row_number " +
                        " from v_manage_price_trade vpt where 1=1 ");

        StringBuilder countSql = new StringBuilder("select count(*) from v_manage_price_trade where 1=1 ");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(commonDto.getStartDate()!=null){
            tempSql.append(" and price_date >= '" +sdf.format(commonDto.getStartDate()) + "' ");
            countSql.append(" and price_date >= '" + sdf.format(commonDto.getStartDate()) + "' ");
        }
        if(commonDto.getEndDate()!=null){
            tempSql.append(" and price_date < '" +sdf.format(commonDto.getEndDate()) + "' ");
            countSql.append(" and price_date < '" + sdf.format(commonDto.getEndDate()) + "' ");
        }

        if (name != null && name != "") {
            tempSql.append(" and product_name= '" + name + "' ");
            countSql.append(" and product_name = '" + name + "' ");
        }
        if (area != null && area != "") {
            tempSql.append(" and area_name= '" + area + "'");
            countSql.append(" and area_name= '" + area + "'");
        }
        if (modelName != null && modelName != "") {
            tempSql.append(" and model_name= '" + modelName + "'");
            countSql.append(" and model_name= '" + modelName + "'");
        }
        if (marketName != null && marketName != "") {
            tempSql.append(" and market_name= '" + marketName + "'");
            countSql.append(" and market_name= '" + marketName + "'");
        }
        if (traderName != null && traderName != "") {
            tempSql.append(" and trader_name= '" + traderName + "'");
            countSql.append(" and trader_name= '" + traderName + "'");
        }

        tempSql.append(" ) product_price where row_number >" + startRow + " and row_number <= " + endRow + " order by " + defaultOrder);

        int total = getJdbcTemplate().queryForInt(countSql.toString());

        List<PriceTraderDetail> priceJudgeDetailList = getJdbcTemplate().query(tempSql.toString(), new RowMapper<PriceTraderDetail>() {
            @Override
            public PriceTraderDetail mapRow(ResultSet rs, int i) throws SQLException {

                return new PriceTraderDetail(
                        rs.getInt("id"),
                        rs.getInt("price_trade_id"),
                        rs.getString("product_name"),
                        rs.getString("model_name"),
                        rs.getString("area_name"),
                        rs.getString("market_name"),
                        rs.getString("trader_name"),
                        rs.getString("unit"),
                        rs.getInt("is_display"),
                        rs.getDate("price_date"),
                        rs.getBigDecimal("trade_price"),
                        rs.getBigDecimal("trade_num")
                ) .setAdminId(rs.getInt("admin_id"))
                        .setModifyDate(rs.getTimestamp("modify_date"));
            }
        });

        return new Page<PriceTraderDetail>(pageNumber, pageSize, total, defaultOrder, priceJudgeDetailList);
    }

    @Override
    public synchronized List<PriceTraderDetail> list(CommonDto commonDto) {

        globlePriceDate = commonDto.getPriceDate();
        globleDate = new SimpleDateFormat("yyyy-MM-dd").format(globlePriceDate);

        StringBuilder sql = new StringBuilder(" select * from  t_price_trader " +
                " where product_name='" + commonDto.getProductName() + "' ");

        String area = commonDto.getAreaName();
        String modelName = commonDto.getModelName();
        String marketName = commonDto.getMarketName();
        String traderName = commonDto.getTraderName();
        if (area != null && !"".equals(area)) {
            sql.append(" and area_name= '" + area + "' ");
        }
        if (modelName != null &&  ! "".equals(modelName)) {
            sql.append(" and model_name= '" + modelName + "'");
        }
        if (marketName != null &&  !"".equals(marketName)) {
            sql.append(" and market_name= '" + marketName + "'");
        }
        if (traderName != null &&  !"".equals(traderName)) {
            sql.append(" and trader_name= '" + traderName + "'");
        }

        List<PriceTraderDetail> priceTraderDetailList = getJdbcTemplate().query(sql.toString(), new PriceTraderDetailRowMapper());

        return priceTraderDetailList;
    }

    private Integer tempId=null;
    @Override
    public synchronized Boolean update(PriceTraderDetail priceTraderDetail) {
        String priceDate = new SimpleDateFormat("yyyy-MM-dd").format(priceTraderDetail.getPriceDate());
//        String modifyDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(priceTraderDetail.getModifyDate());

        //拼接更新明细sql
        String sql = null;
        if (priceTraderDetail.getId() == 0) {
            sql = " insert into t_price_trader_detail(price_trader_id,trade_price,trade_num,price_date,admin_id,modify_date)  values( " +
                    priceTraderDetail.getPriceTraderId() + "," + priceTraderDetail.getTradePrice() +","+
                    priceTraderDetail.getTradeNum() + ",'" + priceDate + "',"+priceTraderDetail.getAdminId()+",getdate())";
        } else {
            sql = " update t_price_trader_detail set price_date='" + priceDate +
                    "',trade_price=" + priceTraderDetail.getTradePrice() +
                    ",trade_num=" + priceTraderDetail.getTradeNum() +
                    ",admin_id="+priceTraderDetail.getAdminId()+
                    ",modify_date=getdate()"+
                    " where id=" + priceTraderDetail.getId();
        }

        //拼接查询字典sql
        tempId = priceTraderDetail.getId();
        String traderSql = " select * from t_price_trader where id="+priceTraderDetail.getPriceTraderId();
        PriceTraderDetail oldPriceTrader = getJdbcTemplate().query(traderSql,new ResultSetExtractor<PriceTraderDetail>() {
            @Override
            public PriceTraderDetail extractData(ResultSet rs) throws SQLException, DataAccessException {

                if (tempId!=null && !tempId.equals(0) && rs.next()) {
                    return new PriceTraderDetail(rs.getBigDecimal("last_price"),rs.getDate("last_price_date"),
                            rs.getDate("modify_date"),rs.getDate("first_price_date"));
                }
                return null;
            }
        });

        //拼接更新字典sql
        String updatePriceSql = null;
        if(oldPriceTrader==null || (oldPriceTrader!=null && oldPriceTrader.getFirstPriceDate()==null)){
            updatePriceSql = " update t_price_trader set first_price_date='"+priceDate+
                    "',last_price_date='"+priceDate+"',modify_date='"+priceDate+"',last_price="+priceTraderDetail.getTradePrice()+
                    " where id="+priceTraderDetail.getPriceTraderId();
        }else {
            updatePriceSql = " update t_price_trader set last_price_date='"+priceDate+"',modify_date='"+priceDate+
                    "',last_price="+priceTraderDetail.getTradePrice()+
                    " where id="+priceTraderDetail.getPriceTraderId();
        }

        try {
            getJdbcTemplate().execute(sql);
            getJdbcTemplate().execute(updatePriceSql);
        } catch (DataAccessException e) {
            logger.error(e);
            return false;
        }

        return true;
    }

    private class PriceTraderDetailRowMapper implements RowMapper<PriceTraderDetail> {
        @Override
        public PriceTraderDetail mapRow(ResultSet rs, int i) throws SQLException {

            int priceTradeId = rs.getInt("id");

            PriceTraderDetail priceTraderDetail = getPriceTraderDetail(priceTradeId);
            Integer id = priceTraderDetail.getId();
            Date priceDate = priceTraderDetail.getPriceDate();
            BigDecimal tradePrice = priceTraderDetail.getTradePrice();
            BigDecimal tradeNum = priceTraderDetail.getTradeNum();

            return new PriceTraderDetail(
                    id == null ? 0 : id.intValue(),
                    priceTradeId,
                    rs.getString("product_name"),
                    rs.getString("model_name"),
                    rs.getString("area_name"),
                    rs.getString("market_name"),
                    rs.getString("trader_name"),
                    rs.getString("unit"),
                    rs.getInt("is_display"),
                    priceDate, tradePrice, tradeNum
            ).setCurrentDate(globlePriceDate)
                    .setAdminId(priceTraderDetail.getAdminId())
                    .setModifyDate(priceTraderDetail.getModifyDate());
        }
    }

    private PriceTraderDetail getPriceTraderDetail(int priceTradeId) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(globlePriceDate);
        String sql = " select top 1 * from t_price_trader_detail where  price_trader_id = " + priceTradeId +
                " and convert(varchar(10),price_date,120) = '" + date + "' order by price_date desc";
        PriceTraderDetail priceTraderDetail = getJdbcTemplate().query(sql, new PriceTraderDetailExtractor());
        return priceTraderDetail;
    }

    private class PriceTraderDetailExtractor implements ResultSetExtractor<PriceTraderDetail> {
        @Override
        public PriceTraderDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
            if (rs.next()) {
                return new PriceTraderDetail(
                        rs.getInt("id"),
                        rs.getInt("price_trader_id"),
                        rs.getDate("price_date"),
                        rs.getBigDecimal("trade_price"),
                        rs.getBigDecimal("trade_num")
                ) .setAdminId(rs.getInt("admin_id"))
                        .setModifyDate(rs.getTimestamp("modify_date"));
            } else {
                return new PriceTraderDetail();
            }
        }
    }
}
