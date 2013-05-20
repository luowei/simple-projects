package com.rootls.service;

import com.rootls.model.IdName;
import com.rootls.model.WebClass;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-5-14
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
public interface WebClassService {
    WebClass findById(Integer id);

    IdName getLanMuById(String i);

    IdName getProductById(String i);

    void updateById(Integer id, String lanMuIds, String productIds, String webCalssIds, String adminIds);

    IdName getWebClassById(String i);

    IdName getAdminById(String i);
}
