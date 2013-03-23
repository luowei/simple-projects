package net.rootls.service.impl;

import net.rootls.dao.TradeDetailDao;
import net.rootls.model.TradeDetail;
import net.rootls.model.User;
import net.rootls.model.UserData;
import net.rootls.service.TradeDetailService;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-11
 * Time: 下午5:30
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TradeDetailServiceImpl implements TradeDetailService {

    @Resource
    TradeDetailDao tradeDetailDao;

    @Override
    public List<TradeDetail> list(String listSql, RowMapper<TradeDetail> rowMapper) {

          return tradeDetailDao.list(listSql, rowMapper);
    }

    @Override
    public Integer getTotalElements(String countQuerySql) {

        return tradeDetailDao.getTotalElements(countQuerySql);
    }

    @Override
    public List<UserData> listPCode(User user) {
        return tradeDetailDao.listPCode(user);
    }

    @Override
    public void updatePCode(UserData userData) {
        tradeDetailDao.updatePCode(userData);
    }

    @Override
    public String getProductNameByCode(String productCode) {
        return tradeDetailDao.getProductNameByCode(productCode);
    }

    @Override
    public void addPCode(UserData userData) {
        tradeDetailDao.addPCode(userData);
    }

    @Override
    public boolean exsitPCode(String productCode) {
        return tradeDetailDao.exsitPCode(productCode);
    }

    @Override
    public void delPCode(Integer id) {
        tradeDetailDao.delPCode(id);
    }
}
