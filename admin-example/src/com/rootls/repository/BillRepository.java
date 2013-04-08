package com.rootls.repository;

import com.rootls.model.Bill;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 发票.
 * User: luowei
 * Date: 13-3-22
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BillRepository extends BaseRepository {

    Logger logger = Logger.getLogger(BillRepository.class);


    public Integer queryForInt(String countSql) {
        return getJdbcTemplate().queryForInt(countSql);
    }

    public List<Bill> list(String sql) {
        return getJdbcTemplate().query(sql, new VBillMapper());
    }

    public Bill findByReceipt(String receipt) {
        String sql = "select * from guestbook_ht_receipt where receipt='"+receipt+"'";
        List<Bill> billList = getJdbcTemplate().query(sql, new RowMapper(){
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setGid(rs.getInt("gid"));
                bill.setHtbianhao(rs.getString("htbianhao"));
                bill.setReceipt(rs.getString("receipt"));
                bill.setSendPhone(rs.getString("send_phone"));
                bill.setSendMobile(rs.getString("send_mobile"));
                return bill;
            }
        });
        if (billList!=null && billList.size()>=1){
            return billList.get(0);
        }
        return null;
    }

    public int updateByReceipt(String sql) {
        return getJdbcTemplate().update(sql);
    }


    class VBillMapper implements RowMapper<Bill> {

        @Override
        public Bill mapRow(ResultSet rs, int i) throws SQLException {
            return new Bill(
                    rs.getInt("id"),
                    rs.getString("receipt"),
                    rs.getInt("sid"),
                    rs.getInt("gid"),
                    rs.getString("name"),
                    rs.getString("qymc"),
                    rs.getInt("trackid"),
                    rs.getInt("trial"),
                    rs.getDate("sendtime"),
                    rs.getInt("kuanxianglx"),
                    rs.getString("huikuandi"),
                    rs.getString("htbianhao"),
                    rs.getDate("receiptdate"),
                    rs.getString("shouruxianmu"),
                    rs.getFloat("receiptsum"),
                    rs.getString("sendzip"),
                    rs.getString("sendaddr"),
                    rs.getString("sendlxr"),
                    rs.getString("sendphone"),
                    rs.getString("sendmobile"),
                    rs.getString("sendcontent"),
                    rs.getDate("endtime"),
                    rs.getInt("receipttype"),
                    rs.getInt("sendtype")
            );
        }
    }
}
