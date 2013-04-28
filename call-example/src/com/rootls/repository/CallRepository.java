package com.rootls.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.rootls.model.CallResponse.CallLog;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-16
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CallRepository extends BaseRepository {


    public CallLog exsitCallLog(CallLog callLog, String tableName) {

        String sql = "select * from " + tableName + " where PL_CallSessionID ='" + callLog.getSessionID() + "' ";

        //转换时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        if (callLog.getCallDateTime() != null) {
            sql = sql + " and PL_PhoneTime >='" + df.format(callLog.getCallDateTime()) + "' ";
        }

        //查询数据
        List<CallLog> callLogList = getJdbcTemplate().query(sql, new RowMapper<CallLog>() {

            @Override
            public CallLog mapRow(ResultSet rs, int i) throws SQLException {

                CallLog callLog1 = new CallLog(
                        rs.getDate("PL_Calltime"),
                        null,//rs.getInt("callDirect"),
                        null,//rs.getInt("callIndex"),
                        rs.getInt("PL_State"),
                        null,//rs.getLong("callTimeLen"),
                        rs.getDate("PL_PhoneTime"),
                        rs.getLong("PL_PhoneUse"),
                        rs.getDate("PL_EndTime"),
                        rs.getString("PL_ExtNo"),
                        null,//rs.getInt("id"),
                        rs.getString("PL_GuestbookPhone"),
                        rs.getString("PL_RecordFile"),
                        rs.getString("PL_CallSessionID")
                );
                callLog1.setAdminId(rs.getInt("PL_adminid"));
                callLog1.setAdminName(rs.getString("PL_adminName"));
                callLog1.setAdminPhone(rs.getString("PL_adminphone"));
                callLog1.setGuestBookId(rs.getInt("PL_Guestbookid"));
                callLog1.setOtherCode(rs.getString("PL_GuestbookPhone"));
                callLog1.setInfoSourceId(rs.getInt("PL_ET_infoSources"));
                callLog1.setGclx(rs.getInt("PL_GC_LX"));
                callLog1.setLX(rs.getString("PL_LX"));
                callLog1.setPhoneFrom(rs.getString("PL_PhoneFrom"));
                callLog1.setMyCustomerId(rs.getInt("PL_MyCustomerID"));

                return callLog1;
            }
        });

        //返回结果
        if (callLogList == null || callLogList.size() < 1) {
            return null;
        } else {
            return callLogList.get(0);
        }
    }

    public void save(String sql) {
        getJdbcTemplate().execute(sql);
    }


    public void update(String sql) {
        getJdbcTemplate().update(sql);
    }
}
