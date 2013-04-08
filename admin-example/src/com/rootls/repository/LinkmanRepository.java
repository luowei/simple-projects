package com.rootls.repository;

import com.rootls.model.Bill;
import com.rootls.model.LinkMan;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * 联系人
 * User: luowei
 * Date: 13-3-22
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
@Component
public class LinkmanRepository  extends BaseRepository{
    public LinkMan findByGidAndType2(Integer id) {
        String sql = "select * from GuestBook_Lxr where GC_GuestBookid="+id+" and GC_Lx=2 ";
        List<LinkMan> linkManList =  getJdbcTemplate().query(sql, new LinkManMapper());

        if (linkManList!=null && linkManList.size()>=1){
            return linkManList.get(0);
        }
        return null;
    }

    public int updateFormatPhoneByGidAndType2(Integer id,String phone) {
        String sql = " update guestbook_lxr set GC_DH='"+phone+"' where GC_Guestbookid="+id;
        return getJdbcTemplate().update(sql);
    }

    public int updateFormatMobileByGidAndType2(Integer id,String mobile) {
        String sql = " update guestbook_lxr set GC_SJ='"+mobile+"' where GC_Guestbookid="+id;
        return getJdbcTemplate().update(sql);
    }

    public void updateLinkMan(Bill bill,Bill dbbill) {

        StringBuilder updateSql = new StringBuilder();

        //更邮编
        if (isNotBlank(bill.getSendZip()) && bill.getSendZip().equals(dbbill.getSendZip())) {
            updateSql.append(" ,GC_ZIP='" + bill.getSendZip() + "' ");
        }
        //更邮寄地址
        if (isNotBlank(bill.getSendAddr()) && bill.getSendAddr().equals(dbbill.getSendAddr())) {
            updateSql.append(" ,GC_Address='" + bill.getSendAddr() + "' ");
        }
        //更新联系人
        if (isNotBlank(bill.getSendLxr()) && bill.getSendLxr().equals(dbbill.getSendLxr())) {
            updateSql.append(" ,GC_Contactor='" + bill.getSendLxr() + "' ");
        }
    }

    private class LinkManMapper implements RowMapper<LinkMan> {

        @Override
        public LinkMan mapRow(ResultSet rs, int i) throws SQLException {
            LinkMan linkMan = new LinkMan();
            linkMan.setId(rs.getInt("GC_ID"));
            linkMan.setGid(rs.getInt("GC_Guestbookid"));
            linkMan.setSendAddr(rs.getString("GC_Address"));
            linkMan.setSendLxr(rs.getString("GC_Contactor"));
            linkMan.setSendPhone(rs.getString("GC_DH"));
            linkMan.setSendMobile(rs.getString("GC_SJ"));
            return linkMan;
        }
    }


























































}
