package com.rootls.price.dao;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.model.PriceJudgeDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
public interface PriceJudgeDetailDao extends BaseDao<PriceJudgeDetail> {
    Page<PriceJudgeDetail> listPage(Page<PriceJudgeDetail> page, CommonDto commonDto);

    List<PriceJudgeDetail> list(CommonDto commonDto);

    Boolean update(PriceJudgeDetail priceJudgeDetail);

}
