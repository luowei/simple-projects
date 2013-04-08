package com.rootls.price.controller;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.model.PriceTraderDetail;
import com.rootls.price.service.PriceTraderDetailService;
import com.rootls.price.service.PriceTraderService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes("staff")
@RequestMapping("/spring/pTrade")
public class PriceTraderController extends BaseController {

    @Autowired
    PriceTraderService priceTraderService;

    @Autowired
    PriceTraderDetailService priceTraderDetailService;


    @RequestMapping("/listPage")
    public String listPage(Model model, Page page, CommonDto commonDto) {

        Page<PriceTraderDetail> pp = priceTraderDetailService.listPage(page, commonDto);

        model.addAttribute("list", pp.getContent())
                .addAttribute("areaName", commonDto.getAreaName())
                .addAttribute("modelName", commonDto.getModelName())
                .addAttribute("productName", commonDto.getProductName())
                .addAttribute("marketName", commonDto.getMarketName())
                .addAttribute("traderName", commonDto.getTraderName())
                .addAttribute("startDate", commonDto.getStartDate())
                .addAttribute("endDate", commonDto.getEndDate())
                .addAttribute("pageNumber", pp.getPageNumber())
                .addAttribute("pageSize", pp.getPageSize());
        addPageInfo(model, pp, "/spring/pTrade/listPage.sp");

        return "listTradePrice";
    }

    @RequestMapping("/list")
    public String list(Model model, CommonDto commonDto) {
        if (commonDto.validDateProductName()) {
            List<PriceTraderDetail> priceJudgeDetailList = priceTraderDetailService.list(commonDto);
            model.addAttribute("list", priceJudgeDetailList)
                    .addAttribute("productName", commonDto.getProductName())
                    .addAttribute("priceDate", commonDto.getPriceDate())
                    .addAttribute("areaName", commonDto.getAreaName())
                    .addAttribute("marketName",commonDto.getMarketName())
                    .addAttribute("traderName",commonDto.getTraderName())
                    .addAttribute("modelName", commonDto.getModelName());
        }
        return "tradePrice";
    }

    @RequestMapping("/update")
    public String update(Model model, String codes,Map<String,Map> staff, CommonDto commonDto, RedirectAttributes redirec) {
        if (codes == null || "".equals(codes)) {
            redirec.addFlashAttribute(commonDto);
            return "redirect:/spring/pTrade/list.sp";
        }
        String[] priceArr = codes.split(",");
        Date priceDate = new Date();
        for (String priceStr : priceArr) {
            String[] strArr = priceStr.split("@");
            int id = parseInt(strArr[0]);
            int priceTradeId = parseInt(strArr[1]);
            try {
                priceDate = new SimpleDateFormat("yyyy-MM-dd").parse(strArr[2]);
            } catch (ParseException e) {
                LogFactory.getLog(this.getClass()).error(e);
            }
            BigDecimal tradePrice = isNotBlank(strArr[3]) ? BigDecimal.valueOf(Double.valueOf(strArr[3])) : BigDecimal.ZERO;
            BigDecimal trade_num = isNotBlank(strArr[4]) ? BigDecimal.valueOf(Double.valueOf(strArr[4])) : BigDecimal.ZERO;

            PriceTraderDetail priceTraderDetail = new PriceTraderDetail(id, priceTradeId, tradePrice, trade_num, priceDate);

            int adminId = staff.get("staff") != null && staff.get("staff").get("staffId") != null ?
                    Integer.parseInt(String.valueOf(staff.get("staff").get("staffId"))) : 0;
            priceTraderDetail.setAdminId(adminId);

            priceTraderDetailService.update(priceTraderDetail);

        }
        commonDto.setPriceDate(priceDate);
        redirec.addFlashAttribute(commonDto);
        return "redirect:/spring/pTrade/list.sp";
    }

}
