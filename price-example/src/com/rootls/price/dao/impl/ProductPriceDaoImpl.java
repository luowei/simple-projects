package com.rootls.price.dao.impl;

import com.rootls.price.dao.ProductPriceDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-4
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ProductPriceDaoImpl extends JdbcDaoSupport implements ProductPriceDao {
	
	@Autowired
    @Qualifier("ds")
    private DataSource dataSource;

    @PostConstruct
    void init(){
        setDataSource(dataSource);
    }
    Logger logger = Logger.getLogger(ProductPriceDaoImpl.class);

    @Override
    public int updateDometicPriceListbyIds(final Map<String, Map<String, Integer>> ids) {
        if(null==ids||ids.size()==0){
            logger.warn("warning :ids is null!");
        }
        String[] sqls = new String[ids.size()];
        int i = 0;
        for(String pid : ids.keySet()){
        	Map<String,Integer> result = ids.get(pid);
        	String ganglianid = result.keySet().toArray()[0].toString();
        	Integer toganglian = result.get(result.keySet().toArray()[0]);
        	String sql = "update lz_DomesticMarketProduct set ganglian_id="+ganglianid+",to_ganglian="+toganglian+" where lz_DomesticMarketProduct_id ="+pid;
        	if(toganglian==-1){
        		sql = "update lz_DomesticMarketProduct set ganglian_id="+ganglianid+",to_ganglian="+"to_ganglian"+" where lz_DomesticMarketProduct_id ="+pid;
        	}
        	sqls[i]=sql;
        	i++;
        }
        this.getJdbcTemplate().batchUpdate(sqls);
        return 0;
    }

    @Override
    public int updateDometicOilListbyIds(final Map<String,Map<String,Integer>> ids) {
    	 if(null==ids||ids.size()==0){
             logger.warn("warning :ids is null!");
         }
         String[] sqls = new String[ids.size()];
         int i = 0;
         for(String pid : ids.keySet()){
         	Map<String,Integer> result = ids.get(pid);
         	String ganglianid = result.keySet().toArray()[0].toString();
         	Integer toganglian = result.get(result.keySet().toArray()[0]);
         	String sql = "update lz_DomesticMarketOilProduct set ganglian_id="+ganglianid+",to_ganglian="+toganglian+" where lz_DomesticMarketOilProduct_id ="+pid;
         	if(toganglian==-1){
         		sql = "update lz_DomesticMarketOilProduct set ganglian_id="+ganglianid+",to_ganglian="+"to_ganglian"+" where lz_DomesticMarketOilProduct_id ="+pid;
         	}
         	sqls[i]=sql;
         	i++;
         }
         this.getJdbcTemplate().batchUpdate(sqls);
        return 0;

    }

    @Override
    public int updateInteralPriceListbyIds(final Map<String,Map<String,Integer>> ids) {
    	 if(null==ids||ids.size()==0){
             logger.warn("warning :ids is null!");
         }
         String[] sqls = new String[ids.size()];
         int i = 0;
         for(String pid : ids.keySet()){
         	Map<String,Integer> result = ids.get(pid);
         	String ganglianid = result.keySet().toArray()[0].toString();
         	Integer toganglian = result.get(result.keySet().toArray()[0]);
         	String sql = "update lz_International_market_product set ganglian_id="+ganglianid+",to_ganglian="+toganglian+" where lz_International_market_product_id ="+pid;
         	if(toganglian==-1){
         		sql = "update lz_International_market_product set ganglian_id="+ganglianid+",to_ganglian="+"to_ganglian"+" where lz_International_market_product_id ="+pid;
         	}
         	sqls[i]=sql;
         	i++;
         }
         this.getJdbcTemplate().batchUpdate(sqls);
         return 0;

    }

    @Override
    public int updateDometicExtfactoryListbyIds(final Map<String,Map<String,Integer>> ids) {
    	 if(null==ids||ids.size()==0){
             logger.warn("warning :ids is null!");
         }
         String[] sqls = new String[ids.size()];
         int i = 0;
         for(String pid : ids.keySet()){
         	Map<String,Integer> result = ids.get(pid);
         	String ganglianid = result.keySet().toArray()[0].toString();
         	Integer toganglian = result.get(result.keySet().toArray()[0]);
         	String sql = "update lz_Domestic_exfactory_product set ganglian_id="+ganglianid+",to_ganglian="+toganglian+" where lz_Domestic_exfactory_product_id ="+pid;
         	if(toganglian==-1){
         		sql = "update lz_Domestic_exfactory_product set ganglian_id="+ganglianid+",to_ganglian="+"to_ganglian"+" where lz_Domestic_exfactory_product_id ="+pid;
         	}
         	sqls[i]=sql;
         	i++;
         }
         this.getJdbcTemplate().batchUpdate(sqls);
         return 0;

    }
    
}
