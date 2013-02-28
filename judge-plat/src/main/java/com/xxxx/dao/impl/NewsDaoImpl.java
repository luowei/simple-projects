package com.xxxx.dao.impl;

import com.xxxx.dao.NewsDao;
import com.xxxx.domain.News;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static com.xxxx.bean.Config.HEADNEWS_CONTENT_LEN;
import static com.xxxx.bean.Config.NEWS_IMG_DOMAIN;
import static com.xxxx.bean.Config.NEWS_URL;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;
import static org.apache.commons.lang3.StringUtils.substringBefore;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-22
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
@Component
public class NewsDaoImpl implements NewsDao {

    @Resource(name = "pcdbJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    @Override
    public News getHeadNews() {

        String sql = " select top 1 id,SavePathFileName as imgUrl,CONTENT as headContent from pcdb.dbo.News_SL " +
                " where SavePathFileName is not null order by RQ desc ";
        return jdbcTemplate.query(sql, new ResultSetExtractor<News>() {
            @Override
            public News extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (!rs.next()) {
                    return null;
                }
                String headContent = rs.getString("headContent");

                String imgUrl = rs.getString("imgUrl");
                int id = rs.getInt("id");

                headContent = headContent.replaceAll("\\<.*?>", "").replaceAll("&nbsp;", "")
                        .replaceAll("&amp;", "").replaceAll("\\r\\n", "<br/>").substring(0, HEADNEWS_CONTENT_LEN);
//                headContent = substringBefore(headContent.replaceFirst("\\r\\n", "<br/>").replaceFirst("\\r\\n", "<br/>"), "\r\n");
                String tmpImgUrl = substringBefore(imgUrl, "|");
                imgUrl = NEWS_IMG_DOMAIN +substringBeforeLast(tmpImgUrl, ".")+"_small."+substringAfterLast(tmpImgUrl,".");
                String url = format(NEWS_URL, id);

                return new News(id,headContent, imgUrl, url);
            }
        });
    }

    @Override
    public List<News> getListNews() {

        String sql = " select top 6 id,TITLE as title,RQ as newsDate from pcdb.dbo.News_SL " +
                "  order by RQ desc ";

        return jdbcTemplate.query(sql,new RowMapper<News>(){
            @Override
            public News mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                Date newsDate = rs.getDate("newsDate");
                String url = format(NEWS_URL,id);
                return new News(id,title,url,newsDate);
            }
        });
    }

    public List<News> getListNewsWithoutId(Integer id) {

        String sql = " select top 6 id,TITLE as title,RQ as newsDate from pcdb.dbo.News_SL order by RQ desc ";
        final Integer fid = id;
        return jdbcTemplate.query(sql,new RowMapper<News>(){
            @Override
            public News mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                if(fid!=null && fid.equals(id)){
                    return null;
                }
                String title = rs.getString("title");
                Date newsDate = rs.getDate("newsDate");
                String url = format(NEWS_URL,id);
                return new News(id,title,url,newsDate);
            }
        });
    }


}
