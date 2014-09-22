package com.other.common;

import com.other.bean.DzGroup;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 14-4-3
 * Time: 下午6:30
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    Logger logger = Logger.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping("/reloadCache")
    public String reloadCache() {
        InerCache.clearCache();
        logger.info("init groupMap :" + InerCache.getGroupMap().size());
        logger.info("init smsUserMap :" + InerCache.getSmsUserMap().size());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/getGroups")
    public List<DzGroup> getGroups(Integer id) {
        id = id == null ? 0 : id;

        List<DzGroup> list = new ArrayList<DzGroup>();
        Map<Integer, DzGroup> groupMap = InerCache.getGroupMap();
        for (Map.Entry<Integer, DzGroup> entry : groupMap.entrySet()) {
            DzGroup g = entry.getValue();
            if (g != null && g.getParentId().equals(id)) {
                list.add(g);
            }
        }
        return list;
    }

}
