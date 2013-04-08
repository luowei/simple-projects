package com.rootls.view.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import com.rootls.bean.*;
import com.rootls.dao.mapper.IDePriceExtractor;
import com.rootls.dao.mapper.IDePriceMapper;
import com.rootls.model.IDePrice;
import com.rootls.model.User;
import com.rootls.service.DomesticPriceService;
import com.rootls.service.FactoryPriceService;
import com.rootls.service.InternationalPriceService;
import com.rootls.service.OilPriceService;
import com.rootls.bean.FrequentCheck;
import com.rootls.bean.IArgDto;
import com.rootls.bean.NeedLogin;
import com.rootls.bean.RetBean;
import com.rootls.dao.mapper.IDePriceExtractor;
import com.rootls.dao.mapper.IDePriceMapper;
import com.rootls.model.IDePrice;
import com.rootls.model.User;
import com.rootls.service.DomesticPriceService;
import com.rootls.service.FactoryPriceService;
import com.rootls.service.InternationalPriceService;
import com.rootls.service.OilPriceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 国内市场价.
 * User: luowei
 * Date: 13-2-20
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */
@Controller @NeedLogin
@FrequentCheck
@SessionAttributes("user")
@RequestMapping("/dmprice")
public class DomesticPriceController extends BaseController {

    Logger logger = LoggerFactory.getLogger(DomesticPriceController.class);

    @Autowired
    DomesticPriceService domesticPriceService;

    @Autowired
    FactoryPriceService factoryPriceService;

    @Autowired
    InternationalPriceService internationalPriceService;

    @Autowired
    OilPriceService oilPriceService;

    @ResponseBody
    @RequestMapping("/getOne")
    public RetBean<IDePrice> getOne(Model model, IDePrice iDePrice, User user) {
        if (user.getStartDate() == null) {
            return new RetBean<IDePrice>(Config.FAILD_RET_CODE, Config.NODATA_RET_MSG);
        }
        StringBuilder oneSql = getOneQuerySql(iDePrice, user, "oneSql");
        ResultSetExtractor<IDePrice> resultSetExtractor = new IDePriceExtractor();
        IDePrice price = domesticPriceService.getOne(iDePrice, user, oneSql.toString(), resultSetExtractor);
        return new RetBean<IDePrice>(price);
    }


    @ResponseBody
    @RequestMapping("/getList")
    public RetBean<List<IDePrice>> getList(Model model, IDePrice iDePrice, User user, IArgDto iArgDto) {
        if (user.getStartDate() == null) {
            return new RetBean<List<IDePrice>>(Config.FAILD_RET_CODE, Config.NODATA_RET_MSG);
        }

        Integer totalElements = domesticPriceService.getTotalElements(getCountQuerySql(iDePrice, user));
        Integer pageSize = iArgDto.getPageSize() == null ? Config.DEFAULT_PAGESIZE : iArgDto.getPageSize();
        Integer pageCurrent = iArgDto.getCurrentPage() == null ? Config.DEFAULT_CURRENT : iArgDto.getCurrentPage();
        iArgDto.setCurrentPage(pageCurrent).setPageSize(pageSize);

        String listSql = getPageQuerySql(iDePrice, user, iArgDto);
        RowMapper<IDePrice> rowMapper = new IDePriceMapper();
        List<IDePrice> priceList = domesticPriceService.getList(iDePrice, user, listSql, rowMapper);
        return new RetBean<List<IDePrice>>(priceList)
                .setTotalPage(getTotalPage(pageSize, totalElements))
                .setCurrentPage(pageCurrent)
                .setPageSize(pageSize);
    }


//    @ResponseBody @RequestMapping("/getAll")
//    public RetBean<List<IDePrice>> getAll(Model model, IDePrice iDePrice, User user) {
//        if (user.getStartDate() == null || user.getEndDate()==null) {
//            return new RetBean<List<IDePrice>>(FAILD_RET_CODE, NODATA_RET_MSG);
//        }
//        StringBuilder allSql = getQuerySql(iDePrice, user, null);
//        RowMapper<IDePrice> rowMapper = new IDePriceMapper();
//        List<IDePrice> priceList = domesticPriceService.getAll(iDePrice, user, allSql.toString(), rowMapper);
//        return new RetBean<List<IDePrice>>(priceList);
//    }


    private StringBuilder getOneQuerySql(IDePrice iDePrice, User user, String type) {
        StringBuilder querySql = new StringBuilder(" select ");
        if ("oneSql".equals(type)) {
            querySql.append(" top 1 ");
        }
        querySql.append(" ganglian_id,lz_DomesticMarketProduct_Id,price_date,ProductName,modelName,area_Name,market_Name," +
                "manufacture_Name,low_end_price,high_end_price,Average,memo,Change_Rate,units from Vlz_DMPrice ");

        querySql = appendWhere(iDePrice, user, querySql);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (iDePrice.getPriceDate() != null) {
            querySql.append(" and price_date = '" + sdf.format(iDePrice.getPriceDate()) + "' ");
        }

        return querySql.append(" order by price_date desc ");
    }

    private String getCountQuerySql(IDePrice iDePrice, User user) {
        return appendDateRegion(user,
                appendWhere(iDePrice, user, new StringBuilder(" select count(ganglian_id) from Vlz_DMPrice "))
        ).toString();
    }

    private String getPageQuerySql(IDePrice iDePrice, User user, IArgDto iArgDto) {
        StringBuilder querySql = new StringBuilder(" select ganglian_id,lz_DomesticMarketProduct_Id,price_date,ProductName," +
                "modelName,area_Name,market_Name," +
                "manufacture_Name,low_end_price,high_end_price,Average,memo,Change_Rate,units," +
                "ROW_NUMBER() over(order by price_date asc) as row_number from Vlz_DMPrice ");

        querySql = appendDateRegion(user, appendWhere(iDePrice, user, querySql));

        int startRow = (iArgDto.getCurrentPage() - 1) * iArgDto.getPageSize();
        int endRow = iArgDto.getCurrentPage() * iArgDto.getPageSize();
        return " select * from (" + querySql.toString() + ") as pp where row_number >"
                + startRow + " and row_number <= " + endRow + " order by price_date asc ";

    }

    private StringBuilder appendDateRegion(User user, StringBuilder querySql) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(user.getStartDate()!=null){
            querySql.append(" and price_date >= '" + sdf.format(user.getStartDate()) + "'");
        }

        if(user.getEndDate()!=null){
            querySql.append(" and price_date < '" + sdf.format(user.getEndDate()) + "' ");
        }
        return querySql;
    }

    private StringBuilder appendWhere(IDePrice iDePrice, User user, StringBuilder querySql) {
        querySql.append("  where to_ganglian=1 ");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (iDePrice.getInStartDate() != null) {
            querySql.append(" and price_date >= '" + sdf.format(iDePrice.getInStartDate()) + "' ");
        }
        if (iDePrice.getInEndDate() != null) {
            querySql.append(" and price_date < '" + sdf.format(iDePrice.getInEndDate()) + "' ");
        }
        if (iDePrice.getGid() != null) {
            querySql.append(" and ganglian_id=" + iDePrice.getGid());
        }
        if(iDePrice.getLid()!=null){
            querySql.append(" and lz_DomesticMarketProduct_Id="+iDePrice.getLid());
        }
        if (iDePrice.getProductName() != null) {
            logger.info("productName:" + iDePrice.getProductName());
            querySql.append(" and ProductName='" + iDePrice.getProductName() + "' ");
        }
        if (iDePrice.getModelName() != null) {
            querySql.append(" and modelName='" + iDePrice.getModelName() + "' ");
        }
        if (iDePrice.getAreaName() != null) {
            querySql.append(" and area_Name='" + iDePrice.getAreaName() + "' ");
        }
        if (iDePrice.getMarketName() != null) {
            querySql.append(" and market_Name='" + iDePrice.getMarketName() + "' ");
        }
        if (iDePrice.getManufactureName() != null) {
            querySql.append(" and manufacture_Name='" + iDePrice.getManufactureName() + "' ");
        }
        return querySql;
    }

}
