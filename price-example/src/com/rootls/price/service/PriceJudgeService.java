package com.rootls.price.service;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.model.PriceJudgeDetail;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public interface PriceJudgeService {
    Page<PriceJudgeDetail> listPage(Page<PriceJudgeDetail> page, CommonDto commonDto);
}
