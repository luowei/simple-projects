package com.rootls.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-22
 * Time: 下午2:31
 * To change this template use File | Settings | File Templates.
 */
public class BaseRepository extends JdbcDaoSupport {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @PostConstruct
    void init(){
        setDataSource(dataSource);
    }
}
