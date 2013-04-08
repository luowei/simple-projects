package com.rootls.price.service.impl;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.dao.PriceJudgeDao;
import com.rootls.price.dao.PriceJudgeDetailDao;
import com.rootls.price.model.PriceJudgeDetail;
import com.rootls.price.service.PriceJudgeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PriceJudgeServiceImpl implements PriceJudgeService {

    @Resource
    PriceJudgeDao priceJudgeDao;
    @Resource
    PriceJudgeDetailDao priceJudgeDetailDao;

    @Override
    public Page<PriceJudgeDetail> listPage(Page<PriceJudgeDetail> page, CommonDto commonDto) {
        return priceJudgeDetailDao.listPage(page, commonDto);
    }
}
