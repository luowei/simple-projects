package com.rootls.view.controller;

import com.rootls.bean.Config;
import com.rootls.bean.Page;
import com.rootls.bean.Page;
import com.rootls.bean.SearchBean;
import com.rootls.model.Bill;
import com.rootls.service.BillService;
import com.rootls.view.ExcelView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.rootls.bean.UserCache.UidName;
import static com.rootls.bean.UserCache.getUserMap;
import static com.rootls.view.ExcelView.ExlBean;

/**
 * 发票Controller
 * User: luowei
 * Date: 13-3-22
 * Time: 上午10:54
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    Logger logger = Logger.getLogger(BillController.class);

    @Autowired
    BillService billService;

    @RequestMapping("/list")
     public String list(Model model,Page page,SearchBean searchBean){
        Page<Bill> pg = billService.list(page, searchBean);
        model.addAttribute("page", pg)
                .addAttribute("searchBean",searchBean);
        addPageInfo(model,pg,"/bill/list.lz");

        return "bill/list";
    }

    @ResponseBody @RequestMapping("/getuser")
    public List<String> getuser(){

        List<String> userList = new ArrayList<String>();
        for(Map.Entry<Integer,UidName> entry: getUserMap().entrySet()){
            UidName user = entry.getValue();
             userList.add(user.getAdminId()+"-"+user.getUserName()+"-"+user.getRealName());
        }
        return userList;
    }

    @ResponseBody @RequestMapping("/listjson")
    public Page<Bill> listjson(Model model,Page page,SearchBean searchBean){

        Page<Bill> pg = billService.list(page, searchBean);
        return pg;
    }

    @ResponseBody @RequestMapping("/updatejson")
    public String updatejson(Model model,Bill bill){
        Config.importLog.clear();
        try {
            billService.updateBill(bill);
        } catch (Exception e) {
            Config.importLog.add(e.getMessage());
            logger.error(e.getMessage(),e);
            return "{\"code\":0}";
        }
        return "{\"code\":1}";
    }

    @RequestMapping("/expExl")
    public ModelAndView expExl(Model model,SearchBean searchBean,Page page){

        String sheetName = "发票_"+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String excelName = sheetName + ".xls";
        List<ExlBean> exlBeanList = getBillExlBeanList();

        List<Bill> billList = billService.getList(searchBean, page);

        return new ModelAndView(new ExcelView<Bill>(excelName, sheetName, billList, exlBeanList));
    }


    @ResponseBody @RequestMapping("/impExl")
    public synchronized String impExl(MultipartFile file,RedirectAttributes redirectAttrs){
        //清空日志
        Config.importLog.clear();
        //上传导入
        try {
            String uploadPath = billService.upload(file);
            billService.importExl(uploadPath);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return "{\"code\":0}";
        }
        return "{\"code\":1}";
    }

    @ResponseBody @RequestMapping("/showlog")
    public List<String> showlog(){
        return Config.importLog;
    }


    private List<ExlBean> getBillExlBeanList() {
        List<ExlBean> exlBeanList = new ArrayList<ExlBean>();

        exlBeanList.add(new ExlBean(Config.EXL_NAME, "name"));
        exlBeanList.add(new ExlBean(Config.EXL_HTBIANHAO, "htbianhao"));
        exlBeanList.add(new ExlBean(Config.EXL_RECEIPTDATE, "receiptdate"));
        exlBeanList.add(new ExlBean(Config.EXL_SHOURUXIANMU, "shouruxianmu"));
        exlBeanList.add(new ExlBean(Config.EXL_RECEIPTSUM, "receiptsum"));
        exlBeanList.add(new ExlBean(Config.EXL_RECEIPT, "receipt"));
        exlBeanList.add(new ExlBean(Config.EXL_RECEIPTDZDATE, "receiptdzdate"));
        exlBeanList.add(new ExlBean(Config.EXL_KUANXIANGLX, "kuanxianglxStr"));
        exlBeanList.add(new ExlBean(Config.EXL_RECEIPTTYPE, "receipttypeStr"));
        exlBeanList.add(new ExlBean(Config.EXL_SENDADDR, "sendAddr"));
        exlBeanList.add(new ExlBean(Config.EXL_SENDLXR, "sendLxr"));
        exlBeanList.add(new ExlBean(Config.EXL_SENDPHONE, "sendPhone"));
        exlBeanList.add(new ExlBean(Config.EXL_SENDMOBILE, "sendMobile"));
        exlBeanList.add(new ExlBean(Config.EXL_SENDTIME, "sendTime"));
        exlBeanList.add(new ExlBean(Config.EXL_SENDCONTENT, "sendContent"));
        return exlBeanList;
    }

    private <T> Model addPageInfo(Model model, Page<T> page, String contextUrl) {

        int current = page.getPageNumber();
        int begin = Math.max(1, current - 4);
        int end = Math.min(begin + 4, page.getTotalPage());

        return model == null ? model : model.addAttribute("currentIndex", current)
                .addAttribute("beginIndex", begin)
                .addAttribute("endIndex", end)
                .addAttribute("contextUrl", contextUrl);
    }


}
