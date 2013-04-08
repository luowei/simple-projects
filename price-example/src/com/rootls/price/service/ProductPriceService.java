package com.rootls.price.service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-4
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
public interface ProductPriceService {
    int updateDometicPriceListbyIds(Map<String, Map<String, Integer>> stringMapMap);

    int updateDometicOilListbyIds(Map<String, Map<String, Integer>> stringMapMap);

    int updateInteralPriceListbyIds(Map<String, Map<String, Integer>> stringMapMap);

    int updateDometicExtfactoryListbyIds(Map<String, Map<String, Integer>> stringMapMap);
}
