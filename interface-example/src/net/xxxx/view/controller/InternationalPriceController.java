package net.xxxx.view.controller;

import net.xxxx.bean.IArgDto;
import net.xxxx.bean.NeedLogin;
import net.xxxx.bean.RetBean;
import net.xxxx.dao.mapper.IMPriceExtractor;
import net.xxxx.dao.mapper.IMPriceMapper;
import net.xxxx.model.IMPrice;
import net.xxxx.model.User;
import net.xxxx.service.InternationalPriceService;
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
 * 国际市场价.
 * User: luowei
 * Date: 13-2-20
 * Time: 上午11:18
 * To change this template use File | Settings | File Templates.
 */
@Controller  @NeedLogin
@SessionAttributes("user")
@RequestMapping("/imprice")
public class InternationalPriceController extends BaseController {

    @Autowired
    InternationalPriceService internationalPriceService;


    @ResponseBody
    @RequestMapping("/getOne")
    public RetBean<IMPrice> getOne(Model model,IMPrice iMPrice,User user){
        if (user.getStartDate() == null || user.getEndDate()==null) {
            return new RetBean<IMPrice>(FAILD_RET_CODE, NODATA_RET_MSG);
        }
        StringBuilder oneSql = getQuerySql(iMPrice, user, "oneSql");
        ResultSetExtractor<IMPrice> resultSetExtractor=new IMPriceExtractor();
        IMPrice price = internationalPriceService.getOne(iMPrice, user, oneSql.toString(), resultSetExtractor);
        return new RetBean<IMPrice>(price);
    }

    @ResponseBody
    @RequestMapping("/getList")
    public RetBean<List<IMPrice>> getList(Model model,IMPrice iMPrice,User user,IArgDto iArgDto){
        if (user.getStartDate() == null || user.getEndDate()==null) {
            return  new RetBean<List<IMPrice>>(FAILD_RET_CODE, NODATA_RET_MSG);
        }

        Integer totalElements = internationalPriceService.getTotalElements(getCountQuerySql(iMPrice, user));
        Integer pageSize = iArgDto.getPageSize()==null? DEFAULT_PAGESIZE:iArgDto.getPageSize();
        Integer pageCurrent = iArgDto.getCurrentPage()==null? DEFAULT_CURRENT:iArgDto.getCurrentPage();
        iArgDto.setCurrentPage(pageCurrent).setPageSize(pageSize);

        String listSql = getPageQuerySql(iMPrice, user, iArgDto);
        RowMapper<IMPrice> rowMapper=new IMPriceMapper();
        List<IMPrice> priceList = internationalPriceService.getList(iMPrice,user, listSql, rowMapper);
        return new RetBean<List<IMPrice>>(priceList)
                .setTotalPage(getTotalPage(pageSize,totalElements))
                .setCurrentPage(pageCurrent)
                .setPageSize(pageSize);
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public RetBean<List<IMPrice>> getAll(Model model,IMPrice iMPrice,User user){
        if (user.getStartDate() == null || user.getEndDate()==null) {
            return  new RetBean<List<IMPrice>>(FAILD_RET_CODE, NODATA_RET_MSG);
        }
        StringBuilder allSql = getQuerySql(iMPrice, user, null);
        RowMapper<IMPrice> rowMapper=new IMPriceMapper();
        List<IMPrice> priceList = internationalPriceService.getAll(iMPrice,user, allSql.toString(), rowMapper);
        return new RetBean<List<IMPrice>>(priceList);
    }

    private StringBuilder getQuerySql(IMPrice iMPrice, User user, String type) {
        StringBuilder querySql=new StringBuilder(" select ");
        if("oneSql".equals(type)){
            querySql.append(" top 1 ");
        }
        querySql.append(" ganglian_id,price_date,ProductName,modelName,area_Name,price_type," +
                " low_end_price,high_end_price,mid_end_price,memo,RMB_price,Change_Rate,unit from Vlz_IMPrice ");
        return appendWhere(iMPrice, user, querySql).append(" order by price_date desc ");
    }

    private String getCountQuerySql(IMPrice iMPrice, User user) {
        return appendWhere(iMPrice, user, new StringBuilder(" select count(ganglian_id) from Vlz_IMPrice ")).toString();
    }

    private String getPageQuerySql(IMPrice iMPrice, User user, IArgDto iArgDto) {
        StringBuilder querySql = new StringBuilder(" select  ganglian_id,price_date,ProductName,modelName,area_Name,price_type," +
                " low_end_price,high_end_price,mid_end_price,memo,RMB_price,Change_Rate,unit," +
                "ROW_NUMBER() over(order by price_date desc) as row_number from Vlz_IMPrice ");

        querySql = appendWhere(iMPrice, user, querySql);
        int startRow = (iArgDto.getCurrentPage() - 1) * iArgDto.getPageSize();
        int endRow = iArgDto.getCurrentPage() * iArgDto.getPageSize();
        return " select * from ("+querySql.toString()+") as pp where row_number >"
                + startRow + " and row_number <= " + endRow + " order by price_date desc ";

    }

    private StringBuilder appendWhere(IMPrice iMPrice, User user, StringBuilder querySql) {
        querySql.append("  where to_ganglian=1 and ganglian_id > 0 ");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        querySql.append(" and CONVERT(CHAR(10), price_date, 23) >= '" + sdf.format(user.getStartDate()) + "'");
        querySql.append(" and CONVERT(CHAR(10), price_date, 23) < '" + sdf.format(user.getEndDate()) + "' ");

        if(iMPrice.getInStartDate()!=null){
            querySql.append(" and CONVERT(CHAR(10), price_date, 23) >= '" + sdf.format(iMPrice.getInStartDate()) + "' ");
        }
        if (iMPrice.getInEndDate()!=null){
            querySql.append(" and CONVERT(CHAR(10), price_date, 23) < '" + sdf.format(iMPrice.getInEndDate()) + "' ");
        }
        if(iMPrice.getGid()!=null){
            querySql.append(" and ganglian_id=" + iMPrice.getGid());
        }
        if(iMPrice.getProductName()!=null){
            querySql.append(" and ProductName='" + iMPrice.getProductName() + "' ");
        }
        if (iMPrice.getModelName()!=null){
            querySql.append(" and modelName='" + iMPrice.getModelName() + "' ");
        }
        if(iMPrice.getAreaName()!=null){
            querySql.append(" and area_Name='" + iMPrice.getAreaName() + "' ");
        }
        if(iMPrice.getPriceType()!=null){
            querySql.append(" and price_type='" + iMPrice.getPriceType() + "' ");
        }
        return querySql;
    }

}
