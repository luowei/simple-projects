package com.oilchem.controller;

import com.oilchem.bean.Config;
import com.oilchem.domain.News;
import com.oilchem.domain.VProductPrice;
import com.oilchem.service.NewsService;
import com.oilchem.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-4
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class IndexController {

    @Autowired
    ProductPriceService productPriceService;
    @Autowired
    NewsService newsService;

    @RequestMapping("/index.do")
    public String index(Model model){

        List<VProductPrice>  marqueeList = productPriceService.marqueeList();

        Boolean noshowModel = true;
        for(VProductPrice vProductPrice:marqueeList){
            noshowModel = noshowModel && isBlank(vProductPrice.getModelName());
        }

        News headNews=newsService.getHeadNews();
        List<News> newsList=newsService.getListNews();

        //去掉有图片的一条
        Integer id = headNews.getId();
        for(News news:newsList){
            if(id!=null && id.equals(news)){
                newsList.remove(news);
            }
        }
        if(newsList.size()>5){
            newsList = newsList.subList(0,5);
        }
//        List<News> newsList=newsService.getListNewsWithoutId(headNews==null?null:headNews.getId());

//        List<VProductPrice> weekUpList =  productPriceService.weekTopList(Config.UP);
//        List<VProductPrice> weekDownList =  productPriceService.weekTopList(Config.DOWN);

        model.addAttribute("marqueeList",marqueeList)
                .addAttribute("noshowModel",noshowModel)
//                .addAttribute("weekUpList",weekUpList)
//                .addAttribute("weekDownList",weekDownList)
                .addAttribute("headNews",headNews)
                .addAttribute("newsList",newsList);

        return "indexredrect";
    }

}
