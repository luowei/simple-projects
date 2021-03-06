package com.rootls.service;



import com.rootls.bean.Page;
import com.rootls.domain.VProductPrice;

import java.util.List;

import com.rootls.bean.JSChart;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-4
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
public interface ProductPriceService {
    Page<VProductPrice> list(Page page, String name, String area);

    List<String> getAreaList(String name);

    List<VProductPrice> weekTopList(String type);

    List<VProductPrice> marqueeList();

    JSChart getJsChart(Integer priceJudgeId);
}
