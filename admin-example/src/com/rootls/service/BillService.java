package com.rootls.service;

import com.rootls.bean.Page;
import com.rootls.bean.SearchBean;
import com.rootls.model.Bill;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-22
 * Time: 下午3:03
 * To change this template use File | Settings | File Templates.
 */
public interface BillService {

    List<Bill> getList(SearchBean searchBean, Page page);

    String upload(MultipartFile file);

    void importExl(String uploadPath);

    Page<Bill> list(Page page, SearchBean searchBean);

    void updateBill(Bill bill);
}
