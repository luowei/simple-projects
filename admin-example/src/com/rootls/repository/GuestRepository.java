package com.rootls.repository;

import com.rootls.bean.SearchBean;
import com.rootls.model.Guest;
import com.rootls.bean.SearchBean;
import com.rootls.model.Guest;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-25
 * Time: 下午5:38
 * To change this template use File | Settings | File Templates.
 */
@Component
public class GuestRepository  extends BaseRepository {

    Logger logger = Logger.getLogger(GuestRepository.class);

    List<Guest> findBySearchBean(SearchBean searchBean){
        return null;
    }

    public Guest findByName(String name) {
        String sql = " select * from guestbook where name='"+name+"' ";

        List<Guest> guestList =  getJdbcTemplate().query(sql, new GuestMapper());

        if (guestList!=null && guestList.size()>=1){
            return guestList.get(0);
        }
        return null;
    }

    public Guest findByGid(Integer gid) {
        String sql = " select * from guestbook where id='"+gid+"' ";

        List<Guest> guestList =  getJdbcTemplate().query(sql, new GuestMapper());

        if (guestList!=null && guestList.size()>=1){
            return guestList.get(0);
        }
        return null;
    }

    class GuestMapper implements RowMapper<Guest>{

        @Override
        public Guest mapRow(ResultSet rs, int i) throws SQLException {
            Guest guest = new Guest();
            guest.setName(rs.getString("name"));
            guest.setTrackId(rs.getInt("adminid"));
            guest.setId(rs.getInt("id"));
            guest.setEndtime(rs.getDate("endtime"));
            guest.setTria(rs.getInt("trial"));
            guest.setQymc(rs.getString("qymc"));
            return guest;
        }
    }

}
