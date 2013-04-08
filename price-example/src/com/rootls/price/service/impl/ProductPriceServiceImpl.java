package com.rootls.price.service.impl;

import com.rootls.price.dao.ProductPriceDao;
import com.rootls.price.service.ProductPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-4
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Resource
    ProductPriceDao productPriceDao;


    @Override
    public int updateDometicPriceListbyIds(Map<String, Map<String, Integer>> stringMapMap) {
        return productPriceDao.updateDometicPriceListbyIds(stringMapMap);
    }

    @Override
    public int updateDometicOilListbyIds(Map<String, Map<String, Integer>> stringMapMap) {
        return productPriceDao.updateDometicOilListbyIds(stringMapMap);
    }

    @Override
    public int updateInteralPriceListbyIds(Map<String, Map<String, Integer>> stringMapMap) {
        return productPriceDao.updateInteralPriceListbyIds(stringMapMap);
    }

    @Override
    public int updateDometicExtfactoryListbyIds(Map<String, Map<String, Integer>> stringMapMap) {
        return productPriceDao.updateDometicExtfactoryListbyIds(stringMapMap);
    }
}
