package com.oilchem.dao;



import com.oilchem.bean.Page;
import com.oilchem.domain.VProductPrice;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-4
 * Time: 下午2:45
 * To change this template use File | Settings | File Templates.
 */
public interface VProductPriceDao extends BaseDao<VProductPrice> {

    Page<VProductPrice> list(String name, String area, Page page);

    List<String> getAreaList(String name);

    List<VProductPrice> weekUpTopChangeList();

    List<VProductPrice> weekDownTopChangeList();

    List<VProductPrice> marqueeList();

    List<VProductPrice> nearyThirtyDays(Integer priceJudgeId);
}
