package com.rootls.price.controller;

import com.rootls.price.bean.Page;
import org.springframework.ui.Model;

import static com.rootls.price.bean.Config.PAGEBAR_SIZE;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-15
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
public class BaseController /*extends SimpleFormController */{

    <T> Model addPageInfo(Model model, Page<T> page, String contextUrl) {

        int current = page.getPageNumber();
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + PAGEBAR_SIZE, page.getTotalPage());
        Integer totalPages = page.getTotalPage();
        Integer totalElements = page.getTotalCount();
        Integer pageSize = page.getPageSize();
        String order = page.getOrder();

        return model == null ? model : model.addAttribute("currentIndex", current)
                .addAttribute("beginIndex", begin)
                .addAttribute("endIndex", end)
                .addAttribute("totalPages", totalPages)
                .addAttribute("totalElements", totalElements)
                .addAttribute("contextUrl", contextUrl)
                .addAttribute("pageSize", pageSize)
                .addAttribute("order", order);
    }

//    @InitBinder
//    protected void initBinder(HttpServletRequest request,
//                              ServletRequestDataBinder binder) throws Exception {
//        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
//        binder.registerCustomEditor(Date.class, dateEditor);
//        super.initBinder(request, binder);
//    }

}
