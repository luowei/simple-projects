package net.xxxx.view.controller;

import net.xxxx.bean.IArgDto;
import net.xxxx.bean.NeedLogin;
import net.xxxx.bean.RetBean;
import net.xxxx.dao.mapper.IBOPriceExtractor;
import net.xxxx.dao.mapper.IBOPriceMapper;
import net.xxxx.model.IBOPrice;
import net.xxxx.model.User;
import net.xxxx.service.OilPriceService;
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

import static net.xxxx.bean.Config.*;

/**
 * 国内成品油价.
 * User: luowei
 * Date: 13-2-20
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
@Controller  @NeedLogin
@SessionAttributes("user")
@RequestMapping("/oilprice")
public class OilPriceController extends BaseController{

    @Autowired
    OilPriceService oilPriceService;

    @ResponseBody
    @RequestMapping("/getOne")
    public RetBean<IBOPrice> getOne(Model model, IBOPrice oilPrice, User user){
        if (user.getStartDate() == null || user.getEndDate()==null) {
            return new RetBean<IBOPrice>(FAILD_RET_CODE, NODATA_RET_MSG);
        }
        StringBuilder oneSql = getQuerySql(oilPrice, user, "oneSql");
        ResultSetExtractor<IBOPrice> resultSetExtractor=new IBOPriceExtractor();
        IBOPrice price = oilPriceService.getOne(oilPrice, user, oneSql.toString(), resultSetExtractor);
        return new RetBean<IBOPrice>(price);
    }

    @ResponseBody
    @RequestMapping("/getList")
    public RetBean<List<IBOPrice>> getList(Model model, IBOPrice oilPrice, User user,IArgDto iArgDto){
        if (user.getStartDate() == null || user.getEndDate()==null) {
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

    @ResponseBody
    @RequestMapping("/getAll")
    public RetBean<List<IBOPrice>> getAll(Model model, IBOPrice oilPrice, User user){
        if (user.getStartDate() == null || user.getEndDate()==null) {
            return new RetBean<List<IBOPrice>>(FAILD_RET_CODE, NODATA_RET_MSG);
        }
        StringBuilder allSql = getQuerySql(oilPrice, user,null);
        RowMapper<IBOPrice> rowMapper=new IBOPriceMapper();
        List<IBOPrice> priceList = oilPriceService.getAll(oilPrice,user, allSql.toString(), rowMapper);
        return new RetBean<List<IBOPrice>>(priceList);
    }

    private String getCountQuerySql(IBOPrice iMPrice, User user) {
        return appendWhere(iMPrice, user, new StringBuilder(" select count(ganglian_id) from Vlz_DMOPrice ")).toString();
    }

    private String getPageQuerySql(IBOPrice iMPrice, User user, IArgDto iArgDto) {
        StringBuilder querySql = new StringBuilder(" select ganglian_id,priceDate,ProductName,ModelName,AreaName,MarketName," +
                "Price_CNPC,Price_Sinopec,Price_Market,Price_zzj,Price_lsj,Change_Rate,unit,memo," +
                "ROW_NUMBER() over(order by priceDate desc) as row_number from Vlz_DMOPrice ");

        querySql = appendWhere(iMPrice, user, querySql);
        int startRow = (iArgDto.getCurrentPage() - 1) * iArgDto.getPageSize();
        int endRow = iArgDto.getCurrentPage() * iArgDto.getPageSize();
        return " select * from ("+querySql.toString()+") as pp where row_number >"
                + startRow + " and row_number <= " + endRow + " order by priceDate desc ";
    }

    private StringBuilder getQuerySql(IBOPrice iFacPrice, User user, String type) {
        StringBuilder querySql=new StringBuilder(" select ");
        if("oneSql".equals(type)){
            querySql.append(" top 1 ");
        }
        querySql.append(" ganglian_id,priceDate,ProductName,ModelName,AreaName,MarketName," +
                "Price_CNPC,Price_Sinopec,Price_Market,Price_zzj,Price_lsj,Change_Rate,unit,memo from Vlz_DMOPrice ");
        return appendWhere(iFacPrice, user, querySql).append(" order by priceDate desc ");
    }

    private StringBuilder appendWhere(IBOPrice iBOPrice, User user, StringBuilder querySql) {
        querySql.append("  where to_ganglian=1 and ganglian_id > 0 ");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        querySql.append(" and CONVERT(CHAR(10), priceDate, 23) >= '" + sdf.format(user.getStartDate()) + "'");
        querySql.append(" and CONVERT(CHAR(10), priceDate, 23) < '" + sdf.format(user.getEndDate()) + "' ");

        if(iBOPrice.getInStartDate()!=null){
            querySql.append(" and CONVERT(CHAR(10), priceDate, 23) >= '" + sdf.format(iBOPrice.getInStartDate()) + "' ");
        }
        if (iBOPrice.getInEndDate()!=null){
            querySql.append(" and CONVERT(CHAR(10), priceDate, 23) < '" + sdf.format(iBOPrice.getInEndDate()) + "' ");
        }
        if(iBOPrice.getGid()!=null){
            querySql.append(" and ganglian_id=" + iBOPrice.getGid());
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
