package com.oilchem.controller;

import com.oilchem.bean.Page;
import com.oilchem.domain.VProductPrice;
import com.oilchem.service.ProductPriceService;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.List;
import com.oilchem.bean.JSChart;

import static com.oilchem.bean.Config.PAGEBAR_SIZE;
import static java.net.URLDecoder.decode;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-4
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class PriceController {

    @Autowired
    ProductPriceService productPriceService;

    /**
     * 根据名称查找
     * @param model
     * @param page
     * @param productName
     * @return
     */
    @RequestMapping("/l/p")
    public String list(Model model,Page page,String productName,String area){

        Page<VProductPrice> pp = productPriceService.list(page, productName, area);
        List<String> areaList = productPriceService.getAreaList(productName);
        List<VProductPrice> list = pp.getContent();
        Boolean noshowModel = true;
        for(VProductPrice vProductPrice:list){
            noshowModel = noshowModel && isBlank(vProductPrice.getModelName());
        }

        model.addAttribute("list",list)
                .addAttribute("jmpArea",area)
                .addAttribute("areaList",areaList)
                .addAttribute("noshowModel",noshowModel)
                .addAttribute("productName",productName)
                .addAttribute("pageNumber",pp.getPageNumber())
                .addAttribute("pageSize",pp.getPageSize());
        addPageInfo(model,pp,"/l/p.do");

        return "content";
    }

    @ResponseBody
    @RequestMapping("/week/{type}")
    public  List<VProductPrice>  weekTopChange(@PathVariable String type){
        return productPriceService.weekTopList(type);
    }

    @ResponseBody
    @RequestMapping("/mq")
    public List<VProductPrice> marqueeList(){
        return productPriceService.marqueeList();
    }


    @RequestMapping("/price/toJsChart")
    public String toJsChart(Model model,VProductPrice vProductPrice) throws Exception{
        model.addAttribute("productName",decode(vProductPrice.getProductName()))
                .addAttribute("areaName",decode(vProductPrice.getAreaName()))
                .addAttribute("modelName",decode(vProductPrice.getModelName()))
                .addAttribute("priceJudgeId",vProductPrice.getPriceJudgeId());
        return "chart";
    }

    /**
     * 近三十天的价格曲线
     * @param parame
     * @return
     */
    @ResponseBody
    @RequestMapping("/price/jschart/{parame}")
    public String jsChart(@PathVariable String parame) throws Exception{
       if(parame==null || parame.equals("")){
           return null;
       }
        Integer priceJudgeId = Integer.valueOf(decode(parame, "UTF-8"));
       JSChart jsChart = productPriceService.getJsChart(priceJudgeId);
        ObjectMapper mapper = new ObjectMapper();
        String jsChartJson = mapper.writeValueAsString(jsChart);
        return "{\"JSChart\":"+jsChartJson+"}";
    }

    private <T> Model addPageInfo(Model model, Page<T> page, String contextUrl) {

        int current = page.getPageNumber();
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + PAGEBAR_SIZE, page.getTotalPage());
        Integer totalPages = page.getTotalPage();
        Integer totalElements = page.getTotalCount();
        Integer pageSize = page.getPageSize();
        String sort = page.getOrder();

        return model == null ? model : model.addAttribute("currentIndex", current)
                .addAttribute("beginIndex", begin)
                .addAttribute("endIndex", end)
                .addAttribute("totalPages", totalPages)
                .addAttribute("totalElements", totalElements)
                .addAttribute("contextUrl", contextUrl)
                .addAttribute("pageSize", pageSize)
                .addAttribute("sort", sort);
    }


}
