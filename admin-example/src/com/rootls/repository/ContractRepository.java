package com.rootls.repository;

import com.rootls.model.Contract;
import com.rootls.model.Contract;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 合同
 * User: luowei
 * Date: 13-3-22
 * Time: 下午2:30
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ContractRepository extends BaseRepository{

    Logger logger = Logger.getLogger(ContractRepository.class);


    public Contract findByHtbianhao(String htbianhao) {
        String sql = " select * from guestbook_ht where htbianhao='"+htbianhao+"'";
        List<Contract> contractList = getJdbcTemplate().query(sql, new ContractMapper());;

        if (contractList!=null && contractList.size()>=1){
            return contractList.get(0);
        }
        return null;
    }

    public void updateDaoZhangTime(String daoZhangTime,String htbianhao) {
        String sql = " update guestbook_ht set daozhangtime='"+daoZhangTime+"' where htbianhao='"+htbianhao+"' ";
        getJdbcTemplate().execute(sql);
    }

    public void update(String sql) {
        getJdbcTemplate().update(sql);
    }

    class ContractMapper implements RowMapper<Contract> {

        @Override
        public Contract mapRow(ResultSet rs, int i) throws SQLException {
            Contract contract = new Contract();
            contract.setId(rs.getInt("sid"));
            contract.setGuestId(rs.getInt("id"));
            contract.setHtbianhao(rs.getString("htbianhao"));
            contract.setAdminId(rs.getInt("adminid"));
            contract.setHuikuandi(rs.getString("huikuandi"));
            contract.setKuanxianglx(rs.getInt("kuanxianglx"));

            contract.setHtlx(rs.getInt("htlx"));
            contract.setDaozhangtime(rs.getDate("daozhangtime"));
            return contract;
        }
    }
}
