package com.rootls.dao.impl;

import com.rootls.dao.BaseDao;
import com.rootls.model.User;
import com.rootls.dao.BaseDao;
import com.rootls.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-27
 * Time: 下午3:56
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BaseDaoImpl<T> extends JdbcDaoSupport implements BaseDao<T> {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    void init(){
        setDataSource(dataSource);
    }

//    Class<T> type;
//    void setGenericType(Class<T> type) {
//        this.type = type;
//    }
//    public Class<T> getGenericType() {
//        return this.type;
//    }

    public Integer getTotalElements(String countQuerySql) {
        return getJdbcTemplate().queryForInt(countQuerySql);
    }

    public T getOne(T price, User user, String oneSql, ResultSetExtractor<T> resultSetExtractor) {

        return getJdbcTemplate().query(oneSql, resultSetExtractor);
    }

    public List<T> getList(T price, User user, String listSql, RowMapper<T> rowMapper) {

        return getJdbcTemplate().query(listSql, rowMapper);
    }

    public List<T> getAll(T price, User user, String allSql, RowMapper<T> rowMapper) {
        return getJdbcTemplate().query(allSql, rowMapper);
    }


}
