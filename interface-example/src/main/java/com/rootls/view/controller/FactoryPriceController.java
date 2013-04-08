package com.rootls.view.controller;

import com.rootls.bean.Config;
import com.rootls.bean.RetBean;
import com.rootls.bean.FrequentCheck;
import com.rootls.bean.IArgDto;
import com.rootls.bean.NeedLogin;
import com.rootls.bean.RetBean;
import com.rootls.dao.mapper.IFacPriceExtractor;
import com.rootls.dao.mapper.IFacPriceMapper;
import com.rootls.model.IFacPrice;
import com.rootls.model.User;
import com.rootls.service.FactoryPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 国内出厂价.
 * User: luowei
 * Date: 13-2-20
 * Time: 上午11:18
 * To change this template use File | Settings | File Templates.
 */
@Controller  @NeedLogin @FrequentCheck
@SessionAttributes("user")
@RequestMapping("/facprice")
public class FactoryPriceController extends BaseController{

    @Autowired
    FactoryPriceService factoryPriceService;


    @ResponseBody
    @RequestMapping("/getOne")
    public RetBean<IFacPrice> getOne(Model model,IFacPrice iFacPrice,User user){
        if (user.getStartDate() == null) {
            return new RetBean<IFacPrice>(Config.FAILD_RET_CODE, Config.NODATA_RET_MSG);
        }
        StringBuilder oneSql = getOneQuerySql(iFacPrice, user, "oneSql");
        ResultSetExtractor<IFacPrice> resultSetExtractor=new IFacPriceExtractor();
        IFacPrice price=factoryPriceService.getOne(iFacPrice,user, oneSql.toString(), resultSetExtractor);
        return new RetBean<IFacPrice>(price);
    }

    @ResponseBody
    @RequestMapping("/getList")
    public RetBean<List<IFacPrice>> getList(Model model,IFacPrice iFacPrice,User user,IArgDto iArgDto){
        if (user.getStartDate() == null) {
            return new RetBean<List<IFacPrice>>(Config.FAILD_RET_CODE, Config.NODATA_RET_MSG);
        }

        Integer totalElements = factoryPriceService.getTotalElements(getCountQuerySql(iFacPrice, user));
        Integer pageSize = iArgDto.getPageSize()==null? Config.DEFAULT_PAGESIZE:iArgDto.getPageSize();
        Integer pageCurrent = iArgDto.getCurrentPage()==null? Config.DEFAULT_CURRENT:iArgDto.getCurrentPage();
        iArgDto.setCurrentPage(pageCurrent).setPageSize(pageSize);

        String listSql = getPageQuerySql(iFacPrice, user, iArgDto);
        RowMapper<IFacPrice> rowMapper=new IFacPriceMapper();
        List<IFacPrice> priceList = factoryPriceService.getList(iFacPrice,user, listSql, rowMapper);
        return new RetBean<List<IFacPrice>>(priceList)
                .setTotalPage(getTotalPage(pageSize,totalElements))
                .setCurrentPage(pageCurrent)
                .setPageSize(pageSize);
    }

//    @ResponseBody
//    @RequestMapping("/getAll")
//    public RetBean<List<IFacPrice>> getAll(Model model,IFacPrice iFacPrice,User user){
//        if (user.getStartDate() == null || user.getEndDate()==null) {
//            return new RetBean<List<IFacPrice>>(FAILD_RET_CODE, NODATA_RET_MSG);
//        }
//        StringBuilder allSql = getQuerySql(iFacPrice, user, null);
//        RowMapper<IFacPrice> rowMapper=new IFacPriceMapper();
//        List<IFacPrice> priceList = factoryPriceService.getAll(iFacPrice,user, allSql.toString(), rowMapper);
//        return new RetBean<List<IFacPrice>>(priceList);
//    }

    private StringBuilder getOneQuerySql(IFacPrice iFacPrice, User user, String type) {
        StringBuilder querySql=new StringBuilder(" select ");
        if("oneSql".equals(type)){
            querySql.append(" top 1 ");
        }
        querySql.append("ganglian_id,lz_Domestic_exfactory_product_id,price_date,ProductName," +
                "modelName,manufacture_Name,exfactory_price," +
                "memo,Change_Rate,area_Name,sale_company_name,units from Vlz_DEPrice");

        querySql = appendWhere(iFacPrice, user, querySql);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(iFacPrice.getPriceDate()!=null){
            querySql.append(" and price_date = '" + sdf.format(iFacPrice.getPriceDate()) + "' ");
        }

        return querySql.append(" order by price_date desc ");
    }

    private String getCountQuerySql(IFacPrice iFacPrice, User user) {
        return appendDateRegion(user,appendWhere(iFacPrice, user,
                new StringBuilder(" select count(ganglian_id) from Vlz_DEPrice "))
        ).toString();
    }

    private String getPageQuerySql(IFacPrice iFacPrice, User user, IArgDto iArgDto) {
        StringBuilder querySql = new StringBuilder(" select ganglian_id,lz_Domestic_exfactory_product_id,price_date," +
                "ProductName,modelName,manufacture_Name,exfactory_price," +
                "memo,Change_Rate,area_Name,sale_company_name,units," +
                "ROW_NUMBER() over(order by price_date asc) as row_number from Vlz_DEPrice ");

        querySql = appendDateRegion(user, appendWhere(iFacPrice, user, querySql));

        int startRow = (iArgDto.getCurrentPage() - 1) * iArgDto.getPageSize();
        int endRow = iArgDto.getCurrentPage() * iArgDto.getPageSize();
        return " select * from ("+querySql.toString()+") as pp where row_number >"
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

    private StringBuilder appendWhere(IFacPrice iFacPrice, User user, StringBuilder querySql) {
        querySql.append("  where to_ganglian=1 ");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if(iFacPrice.getInStartDate()!=null){
            querySql.append(" and price_date >= '" + sdf.format(iFacPrice.getInStartDate()) + "' ");
        }
        if (iFacPrice.getInEndDate()!=null){
            querySql.append(" and price_date < '" + sdf.format(iFacPrice.getInEndDate()) + "' ");
        }
        if(iFacPrice.getGid()!=null){
            querySql.append(" and ganglian_id=" + iFacPrice.getGid());
        }
        if(iFacPrice.getLid()!=null){
            querySql.append(" and lz_Domestic_exfactory_product_id="+iFacPrice.getLid());
        }
        if(iFacPrice.getProductName()!=null){
            querySql.append(" and ProductName='" + iFacPrice.getProductName() + "' ");
        }
        if (iFacPrice.getModelName()!=null){
            querySql.append(" and modelName='" + iFacPrice.getModelName() + "' ");
        }
        if(iFacPrice.getAreaName()!=null){
            querySql.append(" and area_Name='" + iFacPrice.getAreaName() + "' ");
        }
        if(iFacPrice.getManufactureName()!=null){
            querySql.append(" and manufacture_Name='" + iFacPrice.getManufactureName() + "' ");
        }
        if(iFacPrice.getSaleCompanyName()!=null){
            querySql.append(" and sale_company_name='" + iFacPrice.getManufactureName() + "' ");
        }
        return querySql;
    }



}
