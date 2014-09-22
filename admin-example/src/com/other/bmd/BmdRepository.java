package com.other.bmd;

import com.rootls.bean.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by luowei on 2014/7/11.
 */
@Component
public class BmdRepository  extends JdbcDaoSupport {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @PostConstruct
    void init() {
        setDataSource(dataSource);
    }

    public Page<Bmd> getPageBmd(Bmd bmd, Page page) {
        Integer pageNumber = page.getPageNumber();
        Integer pageSize = page.getPageSize();
        String defaultOrder = page.getOrder() != null ? page.getOrder() : " addTime desc";

        int startRow = (pageNumber - 1) * pageSize;
        int endRow = pageNumber * pageSize;

        String querySql = " select *,ROW_NUMBER() over(order by " + defaultOrder + " ) as row_number from Mobile_BMD where 1=1 ";
        String countSql = " select count(*) from Mobile_BMD where 1=1 ";

        String whereSql = getWhereSql(bmd);
        querySql = querySql + (whereSql == null ? "" : whereSql);
        countSql = countSql + (whereSql == null ? "" : whereSql);
        String sql = "select * from ( " + querySql + " ) info where  row_number >" + startRow + " and row_number <= " + endRow + " order by " + defaultOrder;

        int total = getJdbcTemplate().queryForInt(countSql);
        List<Bmd> content = getJdbcTemplate().query(sql, new RowMapper<Bmd>() {
            @Override
            public Bmd mapRow(ResultSet rs, int i) throws SQLException {
                Bmd intf = new Bmd(
                        rs.getString("mobile"),
                        rs.getInt("mType"),
                        rs.getTimestamp("addtime")
                );
                return intf;
            }
        });

        page.setTotalCount(total);
        page.setContent(content);
        return page;
    }

    private String getWhereSql(Bmd bmd) {
        String where = "";
        if (StringUtils.isNotBlank(bmd.getMobile())) {
            where = where + " and mobile like '%" + bmd.getMobile() + "%' ";
        }
        return where;
    }

    public int addBmd(String mobile) {
        return getJdbcTemplate().update(" insert into Mobile_BMD(mobile,addTime) values('"+mobile+"',getdate())");
    }

    public int delBmd(Bmd bmd) {
        return getJdbcTemplate().update(" delete from Mobile_BMD where mobile='"+bmd.getMobile()+"' ");
    }

    public boolean exsit(String mobile) {
        return getJdbcTemplate().queryForInt("select count(*) from Mobile_BMD where mobile='"+mobile+"' ") > 0;
    }

    public Bmd.OtherMobile getOtherMobile(String mobile) {
        return getJdbcTemplate().query("select top 1 id,memo from ET_SMS_OtherMobile where Mobile ='"+mobile+"' and memo",
                new ResultSetExtractor<Bmd.OtherMobile>() {
            @Override
            public Bmd.OtherMobile extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    return new Bmd.OtherMobile(
                            rs.getInt("id"),
                            rs.getString("memo")
                    );
                }
                return null;
            }
        });
    }

    public int delOtherMobile(String mobile) {
        return getJdbcTemplate().update("delete from ET_SMS_OtherMobile where Mobile ='"+mobile+"' and memo='系统自动原因:未加入白名单' ");
    }
}
