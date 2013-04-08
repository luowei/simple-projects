package com.rootls.dao;

import com.rootls.model.TradeDetail;
import com.rootls.model.User;
import com.rootls.model.TradeDetail;
import com.rootls.model.User;
import com.rootls.model.UserData;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-11
 * Time: 下午5:27
 * To change this template use File | Settings | File Templates.
 */
public interface TradeDetailDao {
    List<TradeDetail> list(String listSql, RowMapper<TradeDetail> rowMapper);

    Integer getTotalElements(String countQuerySql);

    List<UserData> listPCode(User user);

    void updatePCode(UserData userData);

    String getProductNameByCode(String productCode);

    void addPCode(UserData userData);

    boolean exsitPCode(String productCode);

    void delPCode(Integer id);
}
