package com.rootls.service.impl;

import com.rootls.model.IdName;
import com.rootls.model.WebClass;
import com.rootls.repository.WebClassRepository;
import com.rootls.service.WebClassService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

import static com.rootls.bean.UserCache.UidName;
import static com.rootls.bean.UserCache.getUserMap;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-5-14
 * Time: 下午3:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class WebClassServiceImpl implements WebClassService {
    Logger logger = Logger.getLogger(WebClassServiceImpl.class);

    @Resource
    WebClassRepository webClassRepository;

    static Map<Integer, IdName> lanMuMap = null;
    static Map<Integer, IdName> productMap = null;
    static Long lanMUInitTime = null;
    static Long productInitTime = null;


    @Override
    public WebClass findById(Integer id) {
        return webClassRepository.findById(id);
    }

    @Override
    public IdName getLanMuById(String i) {
        Long mapTime = lanMUInitTime != null ? System.currentTimeMillis() - lanMUInitTime : 0l;
        //一个小时更新一次缓存
        if (lanMuMap == null || lanMUInitTime == null || mapTime > 3600000) {
            lanMuMap = webClassRepository.findLanMuMap();
            lanMUInitTime = System.currentTimeMillis();
        }
        return lanMuMap.get(Integer.valueOf(i));
    }

    @Override
    public IdName getProductById(String i) {
        Long mapTime = productInitTime != null ? System.currentTimeMillis() - productInitTime : 0l;
        //一个小时更新一次缓存
        if (productMap == null || productInitTime == null || mapTime > 3600000) {
            productMap = webClassRepository.findProductMap();
            productInitTime = System.currentTimeMillis();
        }
        return productMap.get(Integer.valueOf(i));
    }

    @Override
    public void updateById(Integer id, String lanMuIds, String productIds, String webCalssIds, String adminIds) {
        webClassRepository.updateById(id, lanMuIds, productIds, webCalssIds, adminIds);
    }

    @Override
    public IdName getWebClassById(String i) {
        return webClassRepository.getWebClassById(i);
    }

    @Override
    public IdName getAdminById(String i) {

        UidName uidName = getUserMap().get(Integer.valueOf(i));
        IdName idName = new IdName(uidName.getAdminId(),uidName.getRealName());
        return idName;
    }
}
