package com.rootls.dao.impl;

import com.rootls.dao.FactoryPriceDao;
import com.rootls.dao.FactoryPriceDao;

import com.rootls.model.IFacPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-20
 * Time: 上午11:07
 * To change this template use File | Settings | File Templates.
 */
@Component
public class FactoryPriceDaoImpl extends BaseDaoImpl<IFacPrice> implements FactoryPriceDao {
}
