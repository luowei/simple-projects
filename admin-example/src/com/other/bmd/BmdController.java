package com.other.bmd;

import com.other.common.ResponseData;
import com.rootls.bean.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Pattern;

import static com.rootls.bean.Config.PAGE_SIZE;

/**
 * Created by luowei on 2014/7/11.
 */
@Controller
@RequestMapping("/bmd")
public class BmdController {

    @Autowired
    BmdRepository bmdRepository;

    @RequestMapping("/list")
    public String list(Model model,Bmd bmd,String publicMenuId,Page page){

        Page<Bmd> pg = bmdRepository.getPageBmd(bmd, page);
        model.addAttribute("bmd", bmd)
            .addAttribute("publicMenuId",publicMenuId);
        addPageInfo(model, pg, "/bmd/list.lz");
        return "/bmd/list";
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResponseData<String> add(String mobiles){
        int num = 0;
        if(StringUtils.isBlank(mobiles)){
            return new ResponseData<String>("添加失败！");
        }
        String exsitMobile = "";
        try {
            for(String mobile:mobiles.split(",")) {
                if(isValidMobileNo(mobile) && !bmdRepository.exsit(mobile)) {
//                    Bmd.OtherMobile otherMobile= bmdRepository.getOtherMobile(mobile);
                    int delNum = bmdRepository.delOtherMobile(mobile);
                    num += bmdRepository.addBmd(mobile);
                }else {
                    if(exsitMobile==""){
                        exsitMobile = exsitMobile+mobile;
                    }else {
                        exsitMobile = exsitMobile + "," + mobile;
                    }
                }
            }
            if(num == 0 ){
                return new ResponseData<String>("添加的号码已存在或不合法！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData<String>("发生异常！");
        }
        if(StringUtils.isNotBlank(exsitMobile)){
            return new ResponseData<String>(1).setMsg("号码已存在:"+exsitMobile);
        }
        return new ResponseData<String>(1).setMsg("添加成功！");

    }

    public Boolean isValidMobileNo(String mobileNo)
    {
//        Pattern pattern = Pattern.compile("([0-9]{11})|^(([0-9]{7,8})|([0-9]{4}|[0-9]{3})-([0-9]{7,8})|([0-9]{4}|[0-9]{3})-([0-9]{7,8})-([0-9]{4}|[0-9]{3}|[0-9]{2}|[0-9]{1})|([0-9]{7,8})-([0-9]{4}|[0-9]{3}|[0-9]{2}|[0-9]{1}))$");
        Pattern pattern = Pattern.compile("((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");
        boolean is =  pattern.matcher(mobileNo).matches();
        return is;
    }

    @ResponseBody
    @RequestMapping("/del")
    public ResponseData<String> del(Bmd bmd){
        int num = 0;
        try {
            num = bmdRepository.delBmd(bmd);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData<String>("删除失败！");
        }
        return new ResponseData<String>(1).setMsg("删除成功！");
    }


    public <T> Model addPageInfo(Model model, Page<T> page, String contextUrl) {

        int current = page.getPageNumber();
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + PAGE_SIZE, page.getTotalPage());
        Integer totalPages = page.getTotalPage();
        Integer totalElements = page.getTotalCount();
        Integer pageSize = page.getPageSize();
        String sort = page.getOrder();

        return model == null ? model : model.addAttribute("currentIndex", current)
                .addAttribute("beginIndex", begin)
                .addAttribute("endIndex", end)
                .addAttribute("totalPages", totalPages)
                .addAttribute("totalElements", totalElements)
                .addAttribute("contextUrl", contextUrl)
                .addAttribute("pageSize", pageSize)
                .addAttribute("sort", sort)
                .addAttribute("content", page.getContent());
    }

}
