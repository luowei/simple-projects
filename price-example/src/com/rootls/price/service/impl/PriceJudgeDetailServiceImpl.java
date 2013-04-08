package com.rootls.price.service.impl;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.dao.PriceJudgeDetailDao;
import com.rootls.price.model.PriceJudgeDetail;
import com.rootls.price.service.PriceJudgeDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PriceJudgeDetailServiceImpl implements PriceJudgeDetailService {

    @Resource
    PriceJudgeDetailDao priceJudgeDetailDao;


    @Override
    public List<PriceJudgeDetail> list(CommonDto commonDto) {

        return priceJudgeDetailDao.list(commonDto);
    }

    @Override
    public Page<PriceJudgeDetail> listPage(Page<PriceJudgeDetail> page, CommonDto commonDto) {
        return priceJudgeDetailDao.listPage(page, commonDto);
    }

    @Override
    public Boolean update(PriceJudgeDetail priceJudgeDetail) {

        return priceJudgeDetailDao.update(priceJudgeDetail);
    }
}
