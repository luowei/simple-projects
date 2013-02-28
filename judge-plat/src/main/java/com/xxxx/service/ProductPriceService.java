package com.xxxx.service;



import com.xxxx.bean.Page;
import com.xxxx.domain.VProductPrice;

import java.util.List;

import com.xxxx.bean.JSChart;

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
