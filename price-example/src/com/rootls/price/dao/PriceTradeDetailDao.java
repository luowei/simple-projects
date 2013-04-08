package com.rootls.price.dao;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.model.PriceTraderDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public interface PriceTradeDetailDao extends BaseDao<PriceTraderDetail>{
    List<PriceTraderDetail> list(CommonDto commonDto);

    Boolean update(PriceTraderDetail priceTraderDetail);

    Page<PriceTraderDetail> listPage(Page page, CommonDto commonDto);
}
