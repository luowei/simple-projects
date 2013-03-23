package net.rootls.view.controller;

import net.rootls.bean.FrequentCheck;
import net.rootls.bean.IArgDto;
import net.rootls.bean.NeedLogin;
import net.rootls.bean.RetBean;
import net.rootls.dao.mapper.IBOPriceExtractor;
import net.rootls.dao.mapper.IBOPriceMapper;
import net.rootls.model.IBOPrice;
import net.rootls.model.User;
import net.rootls.service.OilPriceService;
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

import static net.rootls.bean.Config.*;

/**
 * 国内成品油价.
 * User: luowei
 * Date: 13-2-20
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
@Controller  @NeedLogin  @FrequentCheck
@SessionAttributes("user")
@RequestMapping("/oilprice")
public class OilPriceController extends BaseController{

    @Autowired
    OilPriceService oilPriceService;

    @ResponseBody
    @RequestMapping("/getOne")
    public RetBean<IBOPrice> getOne(Model model, IBOPrice oilPrice, User user){
        if (user.getStartDate() == null) {
            return new RetBean<IBOPrice>(FAILD_RET_CODE, NODATA_RET_MSG);
        }

        StringBuilder oneSql = getOneQuerySql(oilPrice, user, "oneSql");
        ResultSetExtractor<IBOPrice> resultSetExtractor=new IBOPriceExtractor();
        IBOPrice price = oilPriceService.getOne(oilPrice, user, oneSql.toString(), resultSetExtractor);
        return new RetBean<IBOPrice>(price);
    }

    @ResponseBody
    @RequestMapping("/getList")
    public RetBean<List<IBOPrice>> getList(Model model, IBOPrice oilPrice, User user,IArgDto iArgDto){
        if (user.getStartDate() == null) {
            return new RetBean<List<IBOPrice>>(FAILD_RET_CODE, NODATA_RET_MSG);
        }

        Integer totalElements = oilPriceService.getTotalElements(getCountQuerySql(oilPrice, user));
        Integer pageSize = iArgDto.getPageSize()==null? DEFAULT_PAGESIZE:iArgDto.getPageSize();
        Integer pageCurrent = iArgDto.getCurrentPage()==null? DEFAULT_CURRENT:iArgDto.getCurrentPage();
        iArgDto.setCurrentPage(pageCurrent).setPageSize(pageSize);

        String listSql = getPageQuerySql(oilPrice, user, iArgDto);
        RowMapper<IBOPrice> rowMapper=new IBOPriceMapper();
        List<IBOPrice> priceList = oilPriceService.getList(oilPrice,user, listSql, rowMapper);
        return new RetBean<List<IBOPrice>>(priceList)
                .setTotalPage(getTotalPage(pageSize,totalElements))
                .setCurrentPage(pageCurrent)
                .setPageSize(pageSize);
    }

//    @ResponseBody
//    @RequestMapping("/getAll")
//    public RetBean<List<IBOPrice>> getAll(Model model, IBOPrice oilPrice, User user){
//        if (user.getStartDate() == null || user.getEndDate()==null) {
//            return new RetBean<List<IBOPrice>>(FAILD_RET_CODE, NODATA_RET_MSG);
//        }
//        StringBuilder allSql = getQuerySql(oilPrice, user,null);
//        RowMapper<IBOPrice> rowMapper=new IBOPriceMapper();
//        List<IBOPrice> priceList = oilPriceService.getAll(oilPrice,user, allSql.toString(), rowMapper);
//        return new RetBean<List<IBOPrice>>(priceList);
//    }

    private String getCountQuerySql(IBOPrice iBOPrice, User user) {
        return appendDateRegion(iBOPrice,
                appendWhere(iBOPrice, user, new StringBuilder(" select count(ganglian_id) from Vlz_DMOPrice "))).toString();
    }

    private String getPageQuerySql(IBOPrice iBOPrice, User user, IArgDto iArgDto) {
        StringBuilder querySql = new StringBuilder(" select ganglian_id,lz_DomesticMarketOilProduct_ID,priceDate,ProductName," +
                "ModelName,AreaName,MarketName," +
                "Price_CNPC,Price_Sinopec,Price_Market,Price_zzj,Price_lsj,Change_Rate,unit,memo," +
                "ROW_NUMBER() over(order by priceDate asc) as row_number from Vlz_DMOPrice ");

        querySql = appendDateRegion(iBOPrice, appendWhere(iBOPrice, user, querySql));

        int startRow = (iArgDto.getCurrentPage() - 1) * iArgDto.getPageSize();
        int endRow = iArgDto.getCurrentPage() * iArgDto.getPageSize();
        return " select * from ("+querySql.toString()+") as pp where row_number >"
                + startRow + " and row_number <= " + endRow + " order by priceDate asc ";
    }

    private StringBuilder appendDateRegion(IBOPrice iBOPrice, StringBuilder querySql) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(iBOPrice.getInStartDate()!=null){
            querySql.append(" and PriceDate >= '" + sdf.format(iBOPrice.getInStartDate()) + "' ");
        }
        if (iBOPrice.getInEndDate()!=null){
            querySql.append(" and PriceDate < '" + sdf.format(iBOPrice.getInEndDate()) + "' ");
        }
        return querySql;
    }

    private StringBuilder getOneQuerySql(IBOPrice iBOPrice, User user, String type) {
        StringBuilder querySql=new StringBuilder(" select ");
        if("oneSql".equals(type)){
            querySql.append(" top 1 ");
        }
        querySql.append(" ganglian_id,lz_DomesticMarketOilProduct_ID,priceDate,ProductName,ModelName,AreaName,MarketName," +
                "Price_CNPC,Price_Sinopec,Price_Market,Price_zzj,Price_lsj,Change_Rate,unit,memo from Vlz_DMOPrice ");

        querySql = appendWhere(iBOPrice, user, querySql);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(iBOPrice.getPriceDate()!=null){
            querySql.append(" and PriceDate = '" + sdf.format(iBOPrice.getPriceDate()) + "' ");
        }

        return querySql.append(" order by priceDate desc ");
    }

    private StringBuilder appendWhere(IBOPrice iBOPrice, User user, StringBuilder querySql) {
        querySql.append("  where to_ganglian=1 ");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(user.getStartDate()!=null){
            querySql.append(" and PriceDate >= '" + sdf.format(user.getStartDate()) + "'");
        }

        if(user.getEndDate()!=null){
            querySql.append(" and PriceDate < '" + sdf.format(user.getEndDate()) + "' ");
        }

        if(iBOPrice.getGid()!=null){
            querySql.append(" and ganglian_id=" + iBOPrice.getGid());
        }
        if(iBOPrice.getLid()!=null){
            querySql.append(" and lz_DomesticMarketOilProduct_ID="+iBOPrice.getLid());
        }
        if(iBOPrice.getProductName()!=null){
            querySql.append(" and ProductName='" + iBOPrice.getProductName() + "' ");
        }
        if (iBOPrice.getModelName()!=null){
            querySql.append(" and ModelName='" + iBOPrice.getModelName() + "' ");
        }
        if(iBOPrice.getAreaName()!=null){
            querySql.append(" and AreaName='" + iBOPrice.getAreaName() + "' ");
        }
        if(iBOPrice.getAreaName()!=null){
            querySql.append(" and MarketName='" + iBOPrice.getMarketName() + "' ");
        }
        return querySql;
    }

}
