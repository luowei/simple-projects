package com.rootls.service.impl;

import com.rootls.bean.Page;
import com.rootls.dao.VProductPriceDao;
import com.rootls.domain.VProductPrice;
import com.rootls.service.ProductPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import com.rootls.bean.JSChart;
import static com.rootls.bean.Config.*;
import static com.rootls.bean.JSChart.Element;
import static com.rootls.bean.JSChart.Option;
import static com.rootls.bean.JSChart.Point;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-4
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Resource
    VProductPriceDao vProductPriceDao;

    public Page<VProductPrice> list(Page page, String name, String area) {
            return vProductPriceDao.list(name, area, page);
    }

    @Override
    public List<String> getAreaList(String name) {
            return vProductPriceDao.getAreaList(name);
    }

    @Override
    public List<VProductPrice> weekTopList(String type) {

        if(UP.equals(type)){
            return vProductPriceDao.weekUpTopChangeList();
        }
        if(DOWN.equals(type)){
            return vProductPriceDao.weekDownTopChangeList();
        }
        return null;
    }

    @Override
    public List<VProductPrice> marqueeList() {
        return vProductPriceDao.marqueeList();
    }

    @Override
    public JSChart getJsChart(Integer priceJudgeId) {

        List<VProductPrice> vProductPriceList = vProductPriceDao.nearyThirtyDays(priceJudgeId);
        JSChart jsChart = getJsChart(vProductPriceList);
        return jsChart;
    }

    public JSChart getJsChart(List<VProductPrice> vProductPriceList) {
        JSChart jsChart = new JSChart();
        Set<Element> datasets = jsChart.getDatasets();
        Set<Option> optionset = jsChart.getOptionset();
        Element element = new Element("line").setId("line_chart");
        List<Point> pointList = element.getData();
        DateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("MM-dd");

        for(VProductPrice vProductPrice:vProductPriceList){
            String fullDate = fullDateFormat.format(vProductPrice.getPriceDate());
            String unit = dateFormat.format(vProductPrice.getPriceDate());
            String value = vProductPrice.getPrice().toString();
            pointList.add(new Point(unit,value));

            String[] tipValue={unit,value};
            Option labelX = new Option("setLabelX","['"+tipValue[0]+"','']");
            Option toolTip = new Option("setTooltip","['"+tipValue[0]+"','"+fullDate+","+tipValue[1]+"']");
            optionset.add(labelX);
            optionset.add(toolTip);
        }

//        optionset.add(new Option("setSize","640,400"));
//        optionset.add(new Option("setTitle","价格曲线图"));
//        optionset.add(new Option("setTitleColor","#454545"));
//        optionset.add(new Option("setTitleFontSize",11);
//        optionset.add(new Option("setAxisNameX","日期"));
//        optionset.add(new Option("setAxisNameY","价格"));
        datasets.add(element);
        jsChart.setDatasets(datasets).setOptionset(optionset);
        return jsChart;
    }


}
