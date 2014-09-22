package com.rootls.dao.impl;

import com.rootls.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-27
 * Time: 下午3:56
 * To change this template use File | Settings | File Templates.
 */
public class BaseDaoImpl<T> extends JdbcDaoSupport implements BaseDao<T> {

    //方法一
    @Autowired
//    @Qualifier("dataSource")
    private DataSource dataSource;

    @PostConstruct
    void init(){
        setDataSource(dataSource);
    }


}
