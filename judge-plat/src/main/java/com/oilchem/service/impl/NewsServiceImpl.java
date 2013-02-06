package com.oilchem.service.impl;

import com.oilchem.dao.NewsDao;
import com.oilchem.domain.News;
import com.oilchem.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-22
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
@Service
public class NewsServiceImpl implements NewsService{

    @Resource
    NewsDao newsDao;


    @Override
    public News getHeadNews() {
        return newsDao.getHeadNews();
    }

    @Override
    public List<News> getListNews() {
        return newsDao.getListNews();
    }

    @Override
    public List<News> getListNewsWithoutId(Integer id) {
        return newsDao.getListNewsWithoutId(id);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
