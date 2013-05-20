package com.rootls.view.controller;

import com.rootls.model.IdName;
import com.rootls.model.WebClass;
import com.rootls.service.WebClassService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-5-14
 * Time: 下午3:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/webClass")
public class WebClassController {


    Logger logger = Logger.getLogger(WebClassController.class);

    @Autowired
    WebClassService webClassService;

    @RequestMapping("/edit")
    public String edit(Model model, Integer id) {

        WebClass webClass = webClassService.findById(id);
        if (webClass != null) {
            model.addAttribute("id", webClass.getId()).addAttribute("sitName", webClass.getName());
        }
        return "navigate/edit";
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(Integer id, String lanmuIds, String productIds, String webClassIds,String adminIds) {

        if (id == null) {
            return "{\"code\":0}";
        }
        if (isBlank(lanmuIds)) {
            lanmuIds = "";
        }
        if (isBlank(productIds)) {
            productIds = "";
        }
        if (isBlank(webClassIds)) {
            webClassIds = "";
        }
        if (isBlank(adminIds)) {
            adminIds = "";
        }

        webClassService.updateById(id, lanmuIds, productIds, webClassIds, adminIds);
        return "{\"code\":1}";
    }

    @ResponseBody
    @RequestMapping("/loadCheckBox")
    public String loadCheckBox(Integer id) {

        WebClass webClass = webClassService.findById(id);
        String lanMuIds = webClass.getLanMuIds();
        String productIds = webClass.getProductIds();
        String webClassIds = webClass.getChildWebClassIds();
        String adminIds = webClass.getAdminIds();

        String[] lanMuIdArr = isBlank(lanMuIds) ? null : lanMuIds.substring(1, lanMuIds.length() - 1).split(",");
        String[] productIdArr = isBlank(productIds) ? null : productIds.substring(1, productIds.length() - 1).split(",");
        String[] webClassIdArr = isBlank(webClassIds) ? null : webClassIds.substring(1, webClassIds.length() - 1).split(",");
        String[] adminIdArr = isBlank(adminIds) ? null : adminIds.substring(1, adminIds.length() - 1).split(",");

        List<IdName> lanMuList = new ArrayList<IdName>();
        List<IdName> productList = new ArrayList<IdName>();
        List<IdName> webClassList = new ArrayList<IdName>();
        List<IdName> adminList = new ArrayList<IdName>();

        if (lanMuIdArr != null) {
            for (String i : lanMuIdArr) {
                IdName idName = webClassService.getLanMuById(i);
                if (idName != null) {
                    lanMuList.add(idName);
                }
            }
        }
        if (productIdArr != null) {
            for (String i : productIdArr) {
                IdName idName = webClassService.getProductById(i);
                if (idName != null) {
                    productList.add(idName);
                }
            }
        }
        if (webClassIdArr != null) {
            for (String i : webClassIdArr) {
                IdName idName = webClassService.getWebClassById(i);
                if (idName != null) {
                    webClassList.add(idName);
                }
            }
        }
        if(adminIdArr!=null){
            for(String i:adminIdArr){
                IdName idName = webClassService.getAdminById(i);
                if(idName != null){
                    adminList.add(idName);
                }
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            String lanMuJson = mapper.writeValueAsString(lanMuList);
            String productJson = mapper.writeValueAsString(productList);
            String webClassJson = mapper.writeValueAsString(webClassList);
            String adminJson = mapper.writeValueAsString(adminList);
            return "{\"lanMus\":" + lanMuJson + ",\"products\":" + productJson +
                    ",\"webClasses\":" + webClassJson + ",\"admins\":" + adminJson +"}";

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return "{\"lanMus\":[],\"products\":[],\"webClasses\":[],\"adminJson\":[]}";
        }

    }

}
