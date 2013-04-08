package com.rootls.price.controller;

import com.rootls.price.bean.CommonDto;
import com.rootls.price.bean.Page;
import com.rootls.price.model.PriceJudgeDetail;
import com.rootls.price.service.PriceJudgeDetailService;
import com.rootls.price.service.PriceJudgeService;
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
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes("staff")
@RequestMapping("/spring/pJudge")
public class PriceJudgeController extends BaseController {

    @Autowired
    PriceJudgeService priceJudgeService;

    @Autowired
    PriceJudgeDetailService priceJudgeDetailService;

    @RequestMapping("/listPage")
    public String listPage(Model model, Page page, CommonDto commonDto) {

        Page<PriceJudgeDetail> pp = priceJudgeDetailService.listPage(page, commonDto);

        model.addAttribute("list", pp.getContent())
                .addAttribute("areaName", commonDto.getAreaName())
                .addAttribute("modelName", commonDto.getModelName())
                .addAttribute("productName", commonDto.getProductName())
                .addAttribute("startDate", commonDto.getStartDate())
                .addAttribute("endDate", commonDto.getEndDate())
                .addAttribute("pageNumber", pp.getPageNumber())
                .addAttribute("pageSize", pp.getPageSize());
        addPageInfo(model, pp, "/spring/pJudge/listPage.sp");

        return "listJudgePrice";
    }

    @RequestMapping("/list")
    public String list(Model model, CommonDto commonDto) {

        if (commonDto.validDateProductName()) {
            List<PriceJudgeDetail> priceJudgeDetailList = priceJudgeDetailService.list(commonDto);
            model.addAttribute("list", priceJudgeDetailList)
                    .addAttribute("productName", commonDto.getProductName())
                    .addAttribute("priceDate", commonDto.getPriceDate())
                    .addAttribute("areaName", commonDto.getAreaName())
                    .addAttribute("modelName", commonDto.getModelName());
        }
        return "judgePrice";
    }

    @RequestMapping("/update")
    public String update(Model model, String codes, Map<String, Map> staff, CommonDto commonDto, RedirectAttributes redirec) {
        if (codes == null || "".equals(codes)) {
            redirec.addFlashAttribute(commonDto);
            return "redirect:/spring/pJudge/list.sp";
        }
        String[] priceArr = codes.split(",");
        Date priceDate = new Date();
        for (String priceStr : priceArr) {
            String[] strArr = priceStr.split("@");
            int id = isNotBlank(strArr[0]) ? parseInt(strArr[0]) : 0;
            int priceJudgeId = isNotBlank(strArr[1]) ? parseInt(strArr[1]) : 0;
            BigDecimal lastprice = null;
            if (strArr[2] != null && !"".equals(strArr[2])) {
                lastprice = BigDecimal.valueOf(Double.valueOf(strArr[2]));
            }

            try {
                priceDate = new SimpleDateFormat("yyyy-MM-dd").parse(strArr[3]);
            } catch (ParseException e) {
                LogFactory.getLog(this.getClass()).error(e);
            }

            BigDecimal judgePrice = BigDecimal.valueOf(Double.valueOf(strArr[4]));
            BigDecimal changeRate = BigDecimal.valueOf(0);
            if (judgePrice != null && changeRate != null && lastprice != null && judgePrice.compareTo(lastprice) != 0) {
                changeRate = judgePrice.subtract(lastprice).divide(lastprice, 3, BigDecimal.ROUND_HALF_UP);
            }
            PriceJudgeDetail priceJudgeDetail = new PriceJudgeDetail(id, priceJudgeId, judgePrice, changeRate, priceDate);

            int adminId = staff.get("staff") != null && staff.get("staff").get("staffId") != null ?
                    Integer.parseInt(String.valueOf(staff.get("staff").get("staffId"))) : 0;
            priceJudgeDetail.setAdminId(adminId);


            priceJudgeDetailService.update(priceJudgeDetail);

        }
        commonDto.setPriceDate(priceDate);
        redirec.addFlashAttribute(commonDto);
        return "redirect:/spring/pJudge/list.sp";
    }

}
