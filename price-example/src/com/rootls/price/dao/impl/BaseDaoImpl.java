package com.rootls.price.dao.impl;

import com.rootls.price.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
public class BaseDaoImpl<T> extends JdbcDaoSupport implements BaseDao<T> {

    @Autowired
    @Qualifier("ds")
    private DataSource dataSource;

    @PostConstruct
    void init(){
        setDataSource(dataSource);
    }


}