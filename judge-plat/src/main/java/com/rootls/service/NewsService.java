package com.rootls.service;

import com.rootls.domain.News;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-22
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
public interface NewsService {
    News getHeadNews();

    List<News> getListNews();

    List<News> getListNewsWithoutId(Integer integer);
}
