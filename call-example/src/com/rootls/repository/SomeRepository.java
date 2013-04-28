package com.rootls.repository;

import com.rootls.model.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-18
 * Time: 下午2:23
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SomeRepository extends BaseRepository {

    @Resource
    JdbcTemplate expertJdbcTemplate;


    public Admin findAdminByExtCode(String extCode) {
        String sql = "select * from lz_Admin where workphoneExt = '" + extCode + "'";
        List<Admin> adminList = expertJdbcTemplate.query(sql, new RowMapper<Admin>() {
            @Override
            public Admin mapRow(ResultSet rs, int i) throws SQLException {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("username"));
                admin.setCname(rs.getString("truename"));
                admin.setExtCode(rs.getString("workphoneExt"));
                admin.setPhone(rs.getString("workphone"));
                return admin;
            }
        });

        if (adminList == null || adminList.size() < 1) {
            return null;
        }
        return adminList.get(0);
    }

    public Guest findGuestByPhoneOrMobile(String otherCode) {
        if (otherCode.startsWith("013") || otherCode.startsWith("014") ||
                otherCode.startsWith("015") || otherCode.startsWith("018")){
            otherCode = otherCode.substring(1);
        }
        String sql = " select * from guestbook where phone = '"+otherCode+"' or mobile = '"+otherCode+"'";
        List<Guest> guestList = getJdbcTemplate().query(sql,new RowMapper<Guest>(){

            @Override
            public Guest mapRow(ResultSet rs, int i) throws SQLException {
                Guest guest = new Guest();
                guest.setId(rs.getInt("id"));
                guest.setName(rs.getString("name"));
                String phone = rs.getString("phone");
                if(phone==null || phone.equals("")){
                    phone = rs.getString("mobile");
                }
                guest.setGuestPhone(phone);

                return guest;
            }
        });
        if(guestList!=null && guestList.size()>=1){
            return guestList.get(0);
        }
        return null;
    }

    public Guest findGuestById(Integer gid) {
        String sql = " select * from guestbook where id="+gid;
        List<Guest> guestList = getJdbcTemplate().query(sql,new RowMapper<Guest>(){

            @Override
            public Guest mapRow(ResultSet rs, int i) throws SQLException {
                Guest guest = new Guest();
                guest.setId(rs.getInt("id"));
                guest.setName(rs.getString("name"));
                String phone = rs.getString("phone");
                if(phone==null || phone.equals("")){
                    phone = rs.getString("mobile");
                }
                guest.setGuestPhone(phone);

                return guest;
            }
        });
        if(guestList!=null && guestList.size()>=1){
            return guestList.get(0);
        }
        return null;
    }

    public LinkMan findLinkManByPhoneOrMobile(String otherCode) {
        String sql = " select * from GuestBook_Lxr where GC_DH = '"+otherCode+"' or GC_Mobile = '"+otherCode+"'";
        List<LinkMan> linkManList = getJdbcTemplate().query(sql,new RowMapper<LinkMan>(){

            @Override
            public LinkMan mapRow(ResultSet rs, int i) throws SQLException {
                LinkMan linkMan = new LinkMan();
                linkMan.setId(rs.getInt("GC_ID"));
                linkMan.setGid(rs.getInt("GC_Guestbookid"));
                linkMan.setFormatPhone(rs.getString("GC_DH"));
                linkMan.setFormatMobile(rs.getString("GC_SJ"));
                return linkMan;
            }
        });
        if(linkManList!=null && linkManList.size()>=1){
            return linkManList.get(0);
        }
        return null;
    }

    public MyCustomer findMyCustomerByPhoneOrMobile(String otherCode) {
        String sql = " select * from Sys_MyCustomer where areaPhone = '"+otherCode+"' or mobile = '"+otherCode+"'";
        List<MyCustomer> myCustomerList = getJdbcTemplate().query(sql,new RowMapper<MyCustomer>(){

            @Override
            public MyCustomer mapRow(ResultSet rs, int i) throws SQLException {
                MyCustomer myCustomer = new MyCustomer();
                myCustomer.setId(rs.getInt("CID"));
                myCustomer.setGid(rs.getInt("GID"));
                return myCustomer;
            }
        });
        if(myCustomerList!=null && myCustomerList.size()>=1){
            return myCustomerList.get(0);
        }
        return null;
    }


    public InfoSource findInfoSourceByPhoneOrMobile(String otherCode) {

        String sql = " select * from ET_InformationSources where telephone='"+otherCode+"' or mobile='"
                +(otherCode.startsWith("0")?otherCode.substring(1):otherCode)+"'";
        List<InfoSource> infoSourcesList = expertJdbcTemplate.query(sql,new RowMapper<InfoSource>(){

            @Override
            public InfoSource mapRow(ResultSet rs, int i) throws SQLException {
                InfoSource infoSource = new InfoSource();
                infoSource.setId(rs.getInt("id"));
                return infoSource;
            }
        });
        if(infoSourcesList!=null && infoSourcesList.size()>=1){
            return infoSourcesList.get(0);
        }
        return null;
    }


    public PhoneInfo getPhoneInfoByMobile(String otherCode) {
        if(otherCode.startsWith("0")){
            otherCode = otherCode.substring(1);
            if(otherCode.length()==11){
                 otherCode = otherCode.substring(0,7);
            }
        }
        String sql = " select * from Lz_Mobile where num='"+otherCode+"'";
        List<PhoneInfo> phoneInfos = getJdbcTemplate().query(sql,new RowMapper<PhoneInfo>(){

            @Override
            public PhoneInfo mapRow(ResultSet rs, int i) throws SQLException {
                PhoneInfo phoneInfo1 = new PhoneInfo();
                phoneInfo1.setArea(rs.getString("area"));
                phoneInfo1.setPhoneFrom(rs.getString("area"));
                phoneInfo1.setCardType(rs.getString("t"));
                return phoneInfo1;
            }
        });
        if(phoneInfos!=null && phoneInfos.size()>=1){
            return phoneInfos.get(0);
        }
        return null;
    }

    public CallId getRequestCallId() {
        String sql = " select top 1 * from t_callId order by callId desc";
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        return getJdbcTemplate().query(sql,new ResultSetExtractor<CallId>() {
            @Override
            public CallId extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    CallId callId = new CallId();
                    callId.setId(rs.getInt("id"));
                    callId.setCallId(rs.getInt("callId"));
                    callId.setCallTime(rs.getDate("callTime"));
                    return callId;
                }
                return new CallId(0);
            }
        });
    }

    public void saveLastCallId(Integer lastId) {
        getJdbcTemplate().execute(" insert into t_callId(callId,callTime) values("+lastId+",GETDATE())");
    }




}
