package com.rootls.price.service;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.model.PriceJudgeDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
public interface PriceJudgeDetailService {
    List<PriceJudgeDetail> list(CommonDto commonDto);

    Page<PriceJudgeDetail> listPage(Page<PriceJudgeDetail> page, CommonDto commonDto);

    Boolean update(PriceJudgeDetail priceJudgeDetail);
}
