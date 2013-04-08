package com.rootls.view.controller;

import com.rootls.bean.*;
import com.rootls.dao.mapper.TradeDetailMapper;
import com.rootls.model.TradeDetail;
import com.rootls.model.User;
import com.rootls.service.TradeDetailService;
import com.rootls.bean.FrequentCheck;
import com.rootls.bean.IArgDto;
import com.rootls.bean.NeedLogin;
import com.rootls.bean.RetBean;
import com.rootls.dao.mapper.TradeDetailMapper;
import com.rootls.model.TradeDetail;
import com.rootls.model.User;
import com.rootls.model.UserData;
import com.rootls.service.TradeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-11
 * Time: 下午5:30
 * To change this template use File | Settings | File Templates.
 */
@Controller @NeedLogin
@SessionAttributes("user")
@RequestMapping("/trade")
public class TradeDetailController extends BaseController {

    @Autowired
    TradeDetailService tradeDetailService;

    @RequestMapping("/listpcode")
    public String productCode(Model model, User user) {
        List<UserData> list = user.getId() == null ? null : tradeDetailService.listPCode(user);
        model.addAttribute("list", list);
        return "trade";
    }

    @RequestMapping("/updatepcode")
    public String updatePCode(UserData userData,RedirectAttributes redirect) {
        if (userData == null || isBlank(userData.getProductCode()) ||
                userData.getId() == null || tradeDetailService.exsitPCode(userData.getProductCode())) {
            redirect.addFlashAttribute("message","已存此产品代码,更新失败");
            return "redirect:/trade/listpcode.do";
        }

        userData.setProductName(tradeDetailService.getProductNameByCode(userData.getProductCode()));
        tradeDetailService.updatePCode(userData);
        redirect.addFlashAttribute("message","更新成功");
        return "redirect:/trade/listpcode.do";
    }

    @RequestMapping("/addpcode")
    public String addPCode(Model model,UserData userData, User user,RedirectAttributes redirect) {
        if (userData == null || user == null || tradeDetailService.exsitPCode(userData.getProductCode()) ||
                isBlank(userData.getProductCode())) {
            redirect.addFlashAttribute("message", "已存此产品代码 或 用户数据错误");
            return "redirect:/trade/listpcode.do";
        }

        userData.setUid(Long.parseLong(String.valueOf(user.getId())));
        userData.setProductName(tradeDetailService.getProductNameByCode(userData.getProductCode()));
        tradeDetailService.addPCode(userData);
        redirect.addFlashAttribute("message","添加成功");
        return "redirect:/trade/listpcode.do";
    }

    @RequestMapping("/delpcode")
    public String delpcode(Integer id,RedirectAttributes redirect) {
        if (id == null) {
            redirect.addFlashAttribute("message","删除失败");
            return "redirect:/trade/listpcode.do";
        }

        tradeDetailService.delPCode(id);
        redirect.addFlashAttribute("message","删除成功");
        return "redirect:/trade/listpcode.do";
    }


    @ResponseBody
    @RequestMapping("list")  @FrequentCheck
    public RetBean<List<TradeDetail>> list(TradeDetail tradeDetail, User user, IArgDto iArgDto) {

        if (isBlank(user.getStartYearMonth())) {
            return new RetBean<List<TradeDetail>>(Config.FAILD_RET_CODE, Config.NODATA_RET_MSG);
        }

        Integer totalElements = tradeDetailService.getTotalElements(getCountQuerySql(tradeDetail, user));
        Integer pageSize = iArgDto.getPageSize() == null ? Config.DEFAULT_PAGESIZE : iArgDto.getPageSize();
        Integer pageCurrent = iArgDto.getCurrentPage() == null ? Config.DEFAULT_CURRENT : iArgDto.getCurrentPage();
        iArgDto.setCurrentPage(pageCurrent).setPageSize(pageSize);

        String listSql = getPageQuerySql(tradeDetail, user, iArgDto);
        RowMapper<TradeDetail> rowMapper = new TradeDetailMapper();

        String type = "";
        if(tradeDetail.getType().equals(0)){
             type = "进口";
        }else {
            type = "出口";
        }

        List<TradeDetail> priceList = tradeDetailService.list(listSql, rowMapper);
        return new RetBean<List<TradeDetail>>(priceList)
                .setTotalPage(getTotalPage(pageSize, totalElements))
                .setCurrentPage(pageCurrent)
                .setPageSize(pageSize)
                .setType(type);

    }


    private String getCountQuerySql(TradeDetail tradeDetail, User user) {

        String sql = " select count(id) from %s where product_code in( " +
                "select product_code from t_user_detail where uid=%s ) ";
        if (tradeDetail.getType().equals(0)) {
            sql = String.format(sql, "t_import_detail", user.getId());
        }
        if (tradeDetail.getType().equals(1)) {
            sql = String.format(sql, "t_export_detail", user.getId());
        }

        return appendDateRegion(tradeDetail,
                appendWhere(tradeDetail, user, new StringBuilder(sql))).toString();
    }

    private String getPageQuerySql(TradeDetail tradeDetail, User user, IArgDto iArgDto) {

        String sql = " select ROW_NUMBER()  OVER  ( ORDER   BY  id)  AS  row_number," +
                " * from %s where product_code in( " +
                " select product_code from t_user_detail where uid=%s ) ";
        if (tradeDetail.getType().equals(0)) {
            sql = String.format(sql, "t_import_detail", user.getId());
        }
        if (tradeDetail.getType().equals(1)) {
            sql = String.format(sql, "t_export_detail", user.getId());
        }

        StringBuilder querySql = new StringBuilder(sql);
        querySql = appendDateRegion(tradeDetail, appendWhere(tradeDetail, user, querySql));

        int startRow = (iArgDto.getCurrentPage() - 1) * iArgDto.getPageSize();
//        int endRow = iArgDto.getCurrentPage() * iArgDto.getPageSize();

        return " select top " + iArgDto.getPageSize() + " * from (" + querySql.toString() + ") as pp where row_number >"
                + startRow + " order by year_month asc ";
    }

    private StringBuilder appendDateRegion(TradeDetail tradeDetai, StringBuilder querySql) {

        if (tradeDetai.getInStartDate() != null) {
            querySql.append(" and year_month >= '" + tradeDetai.getInStartDate() + "' ");
        }
        if (tradeDetai.getInEndDate() != null) {
            querySql.append(" and year_month < '" + tradeDetai.getInEndDate() + "' ");
        }
        return querySql;
    }

    private StringBuilder appendWhere(TradeDetail tradeDetai, User user, StringBuilder querySql) {

        if (isNotBlank(user.getStartYearMonth())) {
            querySql.append(" and year_month >= '" + user.getStartYearMonth() + "'");
        }
        if (isNotBlank(user.getEndYearMonth())) {
            querySql.append(" and year_month < '" + user.getEndYearMonth() + "' ");
        }
        if (isNotBlank(tradeDetai.getProductCode())) {
            querySql.append(" and product_code ='" + tradeDetai.getProductCode() + "' ");
        }
        if (isNotBlank(tradeDetai.getProductName())) {
            querySql.append(" and product_name='" + tradeDetai.getProductName() + "' ");
        }
        if (isNotBlank(tradeDetai.getCompanyType())) {
            querySql.append(" and company_type='" + tradeDetai.getCompanyType() + "' ");
        }
        if (isNotBlank(tradeDetai.getTradeType())) {
            querySql.append(" and trade_type='" + tradeDetai.getTradeType() + "' ");
        }
        if (isNotBlank(tradeDetai.getTransportation())) {
            querySql.append(" and transportation='" + tradeDetai.getTransportation() + "' ");
        }
        if (isNotBlank(tradeDetai.getCountry())) {
            querySql.append(" and country='" + tradeDetai.getCountry() + "' ");
        }
        if (isNotBlank(tradeDetai.getCity())) {
            querySql.append(" and city='" + tradeDetai.getCity() + "' ");
        }
        if (isNotBlank(tradeDetai.getCustoms())) {
            querySql.append(" and customs='" + tradeDetai.getCustoms() + "' ");
        }

        return querySql;
    }


}
