package com.rootls.repository;

import com.rootls.model.IdName;
import com.rootls.model.WebClass;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-5-14
 * Time: 下午4:00
 * To change this template use File | Settings | File Templates.
 */
@Component
public class WebClassRepository extends BaseRepository {


    public WebClass findById(Integer id) {
        String sql = " select WEBID,WEBName,web_LanMu,web_Products,web_childweb,web_admin from LZ_WEBClass where WEBID=" + id;
        return getJdbcTemplate().query(sql, new ResultSetExtractor<WebClass>() {
            @Override
            public WebClass extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return new WebClass(
                            rs.getInt("WEBID"),
                            rs.getString("WEBName"),
                            rs.getString("web_LanMu"),
                            rs.getString("web_Products"),
                            rs.getString("web_childweb"),
                            rs.getString("web_admin")
                    );
                }
                return null;
            }
        });
    }

    public Map<Integer, IdName> findLanMuMap() {
        final Map<Integer, IdName> lanMuMap = new HashMap<Integer, IdName>();
        String sql = "select LanMuID,LanMuPath from [Sys_LanMu]";
        getJdbcTemplate().query(sql, new RowMapper<IdName>() {
            @Override
            public IdName mapRow(ResultSet rs, int rowNum) throws SQLException {
                String path = rs.getString("LanMuPath");
                String name = path.substring(3,path.length()-3).replace("///","->");

                Integer id = rs.getInt("LanMuID");
                IdName idName = new IdName(id, name);
                lanMuMap.put(id, idName);
                return null;
            }
        });

        return lanMuMap;
    }

    public Map<Integer, IdName> findProductMap() {
        final Map<Integer, IdName> productMap = new HashMap<Integer, IdName>();
        String sql = "select ProductID,ProductPath from [Sys_Product]";
        getJdbcTemplate().query(sql, new RowMapper<IdName>() {
            @Override
            public IdName mapRow(ResultSet rs, int rowNum) throws SQLException {
                String path = rs.getString("ProductPath");
                String name = path.substring(3,path.length()-3).replace("///","->");

                Integer id = rs.getInt("ProductID");
                IdName idName = new IdName(id, name);
                productMap.put(id, idName);
                return null;
            }
        });

        return productMap;
    }

    public void updateById(Integer id, String lanMuIds, String productIds, String webCalssIds, String adminIds) {

        lanMuIds = lanMuIds == "" ? lanMuIds : "," + lanMuIds + ",";
        productIds = productIds == "" ? productIds : "," + productIds + ",";
        webCalssIds = webCalssIds == "" ? webCalssIds : "," + webCalssIds + ",";
        adminIds = adminIds == "" ? adminIds : "," + adminIds + ",";

        String sql = " update LZ_WEBClass set web_LanMu='" + lanMuIds + "',web_Products='" + productIds +
                "',web_childweb='" + webCalssIds + "',web_admin='" + adminIds + "' where WEBID=" + id;
        getJdbcTemplate().update(sql);
    }

    public IdName getWebClassById(String i) {

        String sql = " select WEBID,WEBPath from LZ_WEBClass where WEBID=" + Integer.valueOf(i);
        return getJdbcTemplate().query(sql, new ResultSetExtractor<IdName>() {
            @Override
            public IdName extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    String path = rs.getString("WEBPath");
                    String name = path.substring(3,path.length()-3).replace("///","->");
                    return new IdName(
                            rs.getInt("WEBID"),name);
                }
                return null;
            }
        });
    }
}
