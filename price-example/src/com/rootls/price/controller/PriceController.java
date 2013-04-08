package com.rootls.price.controller;

import com.rootls.price.bean.ReadExcel;
import com.rootls.price.service.ProductPriceService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-4
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/spring/price")
public class PriceController {

    @Autowired
    ProductPriceService productPriceService;

    @RequestMapping("/test")
    public void test() {
        System.out.println("print it");
    }

    @RequestMapping("/upload")
    public String upload(String type,MultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/upload");
        request.setAttribute("name", file.getOriginalFilename());
        FileCopyUtils.copy(file.getBytes(), new File(path + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename()));
        Workbook book = null;
        try {
            book = new XSSFWorkbook(file.getInputStream());
        } catch (Exception ex) {
            book = new HSSFWorkbook(file.getInputStream());
        }
        if (type.equals("dmprice")) {
            productPriceService.updateDometicPriceListbyIds(ReadExcel.readDmPriceExcel(book));
        } else if (type.equals("dmoilprice")) {
            productPriceService.updateDometicOilListbyIds(ReadExcel.readDmOilPriceExcel(book));
        } else if (type.equals("interalprice")) {
            productPriceService.updateInteralPriceListbyIds(ReadExcel.readInteralPriceExcel(book));
        } else if (type.equals("extfactoryprice")) {
            productPriceService.updateDometicExtfactoryListbyIds(ReadExcel.readDEPriceExcel(book));
        }
        return "/upload/success";
    }

    @RequestMapping("/uploadinfo")
    public String uploadinfo() {
        return "/upload/depriceUpload";
    }
    
    

}
