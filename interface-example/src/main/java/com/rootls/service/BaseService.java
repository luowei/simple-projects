package com.rootls.service;

import com.rootls.model.User;
import com.rootls.model.User;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-22
 * Time: 上午9:43
 * To change this template use File | Settings | File Templates.
 */
public interface BaseService<T> {
    T getOne(T price, User user, String oneSql, ResultSetExtractor<T> resultSetExtractor);

    List<T> getList(T price, User user, String listSql, RowMapper<T> rowMapper);

    List<T> getAll(T price, User user, String allSql, RowMapper<T> rowMapper);

    Integer getTotalElements(String countQuerySql);
}
