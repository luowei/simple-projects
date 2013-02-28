package com.xxxx.controller;

import com.xxxx.domain.News;
import com.xxxx.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-22
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @ResponseBody
    @RequestMapping("/head")
    public News getHeadNews(){

        News headNews=newsService.getHeadNews();
        return headNews;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<News> getListNews(){

        List<News> headNewsList=newsService.getListNews();
        return headNewsList;
    }

}
