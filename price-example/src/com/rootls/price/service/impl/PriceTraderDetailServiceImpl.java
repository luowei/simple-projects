package com.rootls.price.service.impl;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.dao.PriceTradeDetailDao;
import com.rootls.price.model.PriceTraderDetail;
import com.rootls.price.service.PriceTraderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:35
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PriceTraderDetailServiceImpl implements PriceTraderDetailService {

    @Resource
    PriceTradeDetailDao priceTradeDetailDao;

    @Override
    public List<PriceTraderDetail> list(CommonDto commonDto) {
        return priceTradeDetailDao.list(commonDto);
    }

    @Override
    public Boolean update(PriceTraderDetail priceTraderDetail) {
        return priceTradeDetailDao.update(priceTraderDetail);
    }

    @Override
    public Page<PriceTraderDetail> listPage(Page page, CommonDto commonDto) {
        return priceTradeDetailDao.listPage(page,commonDto);
    }
}
