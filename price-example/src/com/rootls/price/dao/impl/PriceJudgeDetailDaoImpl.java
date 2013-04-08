package com.rootls.price.dao.impl;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.dao.PriceJudgeDetailDao;
import com.rootls.price.model.PriceJudgeDetail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PriceJudgeDetailDaoImpl extends BaseDaoImpl<PriceJudgeDetail> implements PriceJudgeDetailDao {

    Log logger = LogFactory.getLog(PriceJudgeDetailDaoImpl.class);

    @Override
    public Page<PriceJudgeDetail> listPage(Page<PriceJudgeDetail> page, CommonDto commonDto) {

        globlePriceDate = new Date();
        Integer pageNumber = page.getPageNumber();
        Integer pageSize = page.getPageSize();
        String defaultOrder = page.getOrder() != null ? page.getOrder() : "price_date desc";

        int startRow = (pageNumber - 1) * pageSize;
        int endRow = pageNumber * pageSize;

        String name = commonDto.getProductName();
        String area = commonDto.getAreaName();
        String modelName = commonDto.getModelName();
        Date priceDate = commonDto.getPriceDate();


        StringBuilder tempSql = new StringBuilder(
                "select * from (select * ,ROW_NUMBER() over(order by " + defaultOrder + " ) as row_number " +
                        " from v_manage_price_judge vpj where 1=1 ");

        StringBuilder countSql = new StringBuilder("select count(*) from v_manage_price_judge where 1=1 ");

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


        tempSql.append(" ) product_price where row_number >" + startRow + " and row_number <= " + endRow + " order by " + defaultOrder);

        int total = getJdbcTemplate().queryForInt(countSql.toString());

        List<PriceJudgeDetail> priceJudgeDetailList = getJdbcTemplate().query(tempSql.toString(), new RowMapper<PriceJudgeDetail>(){

            @Override
            public PriceJudgeDetail mapRow(ResultSet rs, int i) throws SQLException {
                return new PriceJudgeDetail(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("model_name"),
                        rs.getString("area_name"),
                        rs.getInt("is_display"),
                        rs.getString("unit"),
                        rs.getDate("price_date"),
                        rs.getInt("price_judge_id"),
                        rs.getBigDecimal("judge_price"),
                        rs.getBigDecimal("change_rate")
                ).setAdminId(rs.getInt("admin_id")).setModifyDate(rs.getTimestamp("modify_date"));
            }
        });

        return new Page<PriceJudgeDetail>(pageNumber, pageSize, total, defaultOrder, priceJudgeDetailList);

    }

    Date globlePriceDate = null;

    @Override
    public synchronized List<PriceJudgeDetail> list(CommonDto commonDto) {

        globlePriceDate = commonDto.getPriceDate();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(commonDto.getPriceDate());

        StringBuilder sql = new StringBuilder(" select * from  t_price_judge " +
                " where product_name='" + commonDto.getProductName() + "' ");

        String area = commonDto.getAreaName();
        String modelName = commonDto.getModelName();
        if (area != null &&  !"".equals(area)) {
            sql.append(" and area_name= '" + area + "' ");
        }
        if (modelName != null &&  !"".equals(modelName)) {
            sql.append(" and model_name= '" + modelName + "'");
        }

        List<PriceJudgeDetail> priceJudgeDetailList = getJdbcTemplate().query(sql.toString(), new PriceJudgeDetailRowMapper());

        return priceJudgeDetailList;
    }

    private Integer tempId=null;
    @Override
    public synchronized Boolean update(PriceJudgeDetail priceJudgeDetail) {

        String priceDate = new SimpleDateFormat("yyyy-MM-dd").format(priceJudgeDetail.getPriceDate());
//        String modifyDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(priceJudgeDetail.getModifyDate());

        //拼接更新明细sql
        String sql = null;
        if(priceJudgeDetail.getId()==0){
            sql = " insert into t_price_judge_detail(price_date,price_judge_id,judge_price,change_rate,admin_id,modify_date)  values('"+
                    priceDate + "'," + priceJudgeDetail.getPriceJudgeId() + "," + priceJudgeDetail.getJudgePrice() + ","+
                    priceJudgeDetail.getChangeRate() + ","+priceJudgeDetail.getAdminId()+",getdate())";
        }else{
            sql = " update t_price_judge_detail set price_date='"+priceDate+"',judge_price="+priceJudgeDetail.getJudgePrice()+
                    ",change_rate="+priceJudgeDetail.getChangeRate()+",admin_id="+priceJudgeDetail.getAdminId()+",modify_date=getdate()" +
                    " where id="+priceJudgeDetail.getId();
        }

        //拼接查询字典sql
        tempId = priceJudgeDetail.getId();
        String traderSql = " select * from t_price_judge where id="+priceJudgeDetail.getPriceJudgeId();
        PriceJudgeDetail oldPriceJudge = getJdbcTemplate().query(traderSql,new ResultSetExtractor<PriceJudgeDetail>() {
            @Override
            public PriceJudgeDetail extractData(ResultSet rs) throws SQLException, DataAccessException {

                if (tempId!=null && !tempId.equals(0) && rs.next()) {
                    return new PriceJudgeDetail(rs.getBigDecimal("last_price"),rs.getDate("last_price_date"),
                            rs.getDate("modify_date"),rs.getDate("first_price_date"));
                }
                return null;
            }
        });

        //拼接更新字典sql
        String updatePriceSql = null;
        if(oldPriceJudge==null || (oldPriceJudge!=null && oldPriceJudge.getFirstPriceDate()==null)){
            updatePriceSql = " update t_price_judge set first_price_date='"+priceDate+
                    "',last_price_date='"+priceDate+"',modify_date='"+priceDate+"',last_price="+priceJudgeDetail.getJudgePrice()+
                    " where id="+priceJudgeDetail.getPriceJudgeId();
        }else {
            updatePriceSql = " update t_price_judge set last_price_date='"+priceDate+"',modify_date='"+priceDate+
                    "',last_price="+priceJudgeDetail.getJudgePrice()+
                    " where id="+priceJudgeDetail.getPriceJudgeId();
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

    public PriceJudgeDetail getLastPriceJudge(int id) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(globlePriceDate);
        String sql = " select top 1 * from v_manage_price_judge where  price_judge_id = " + id + " and price_date < '" + date + " 00:00:00'  order by price_date desc";
        PriceJudgeDetail priceJudgeDetail = getJdbcTemplate().query(sql, new PriceJudgeDetailExtractor());
        return priceJudgeDetail;
    }

    private PriceJudgeDetail getPriceJudgeDetail(int priceJudgeId) {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(globlePriceDate);
        String sql = " select top 1 * from v_manage_price_judge where  price_judge_id = " + priceJudgeId +
                " and convert(varchar(10),price_date,120) = '" + date+"'  order by price_date desc";
        PriceJudgeDetail priceJudgeDetail = getJdbcTemplate().query(sql, new PriceJudgeDetailExtractor());
        return priceJudgeDetail;
    }

    private class PriceJudgeDetailRowMapper implements RowMapper<PriceJudgeDetail> {
        @Override
        public PriceJudgeDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            int priceJudgeId = rs.getInt("id");

            PriceJudgeDetail priceJudgeDetail = getPriceJudgeDetail(priceJudgeId);
            Integer id = priceJudgeDetail.getId();
            Date priceDate = priceJudgeDetail.getPriceDate();
            BigDecimal judgePrice = priceJudgeDetail.getJudgePrice();
            BigDecimal changeRate = priceJudgeDetail.getChangeRate();

            PriceJudgeDetail lastPriceJudgeDetail = getLastPriceJudge(priceJudgeId);
            Date lastDate = lastPriceJudgeDetail.getPriceDate();
            BigDecimal lastPrice = lastPriceJudgeDetail.getJudgePrice();

            return new PriceJudgeDetail(
                    id==null?0:id.intValue(),
                    lastDate, lastPrice, globlePriceDate,
                    rs.getString("product_name"),
                    rs.getString("model_name"),
                    rs.getString("area_name"),
                    rs.getInt("is_display"),
                    rs.getString("unit"),
                    priceDate,
                    priceJudgeId,judgePrice,changeRate
            ).setAdminId(priceJudgeDetail.getAdminId()).setModifyDate(priceJudgeDetail.getModifyDate());
        }
    }

    private class PriceJudgeDetailExtractor implements ResultSetExtractor<PriceJudgeDetail> {

        @Override
        public PriceJudgeDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
            if (rs.next()) {
//                System.out.println("+++++++++++++++++ "+rs.getDate("modify_date").toString()+" +++++++++++++++++");
                return new PriceJudgeDetail(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("model_name"),
                        rs.getString("area_name"),
                        rs.getInt("is_display"),
                        rs.getString("unit"),
                        rs.getDate("price_date"),
                        rs.getInt("price_judge_id"),
                        rs.getBigDecimal("judge_price"),
                        rs.getBigDecimal("change_rate")
                ).setAdminId(rs.getInt("admin_id")).setModifyDate(rs.getTimestamp("modify_date"));
            } else {
                return new PriceJudgeDetail();
            }
        }
    }
}
