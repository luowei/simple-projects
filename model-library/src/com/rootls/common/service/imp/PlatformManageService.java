package com.rootls.common.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rootls.common.dao.PlatformDAO;
import com.rootls.common.model.Platform;
import com.rootls.common.service.BaseService;
import com.rootls.common.service.IPlatformManageService;
import com.rootls.common.tool.common.URLFileUtil;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.query.PropertyFilter;
import com.rootls.common.tool.query.QueryUtil;



/**
 * @className:PlatformManageService.java
 * @classDescription:手机平台管理类
 * @author:tangxiping
 * @createTime:2012-1-7
 */
@Service
public class PlatformManageService extends BaseService<Platform> implements IPlatformManageService
{
	@Resource(name="platformDAO")
	private PlatformDAO platformDAO;

	public Page findAllPlatforms(Page page, List<PropertyFilter> pfList) {
		//初始化hql
		StringBuffer hql=new StringBuffer("from Platform");
		//设置查询条件
		String condition= QueryUtil.toSqlString(pfList, true);
		hql.append(condition);
		//查找所有用户的总条数
		int totalCount=this.platformDAO.findCountBySql(hql.toString());
		page.setTotalCount(totalCount);
		
		
		//是否存在排序
		if(page.isOrderBySetted()){
			hql.append(page.getOrder());
		}
		List<Platform> list=this.platformDAO.findList(hql.toString(), page.getPageNo(), page.getPageSize());
		//查出结果集
		page.setResult(list);
	
		return page;
	}

	public PlatformDAO getPlatformDAO() {
		return platformDAO;
	}

	public void setPlatformDAO(PlatformDAO platformDAO) {
		this.platformDAO = platformDAO;
	}

	public boolean checkPlatformName(String platformName) 
	{
		boolean flag=true;
		String hql="from Platform where platformName=:platformName";
		Map map=new HashMap();
		map.put("platformName", platformName);
		
		Platform platform= this.platformDAO.findOfMap(hql, map);
		if(null==platform){
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 查询平台列表
	 * @author luowei
	 * @param page
	 * @param platformName 平台名
	 * @return 平台，没有，则返回空
	 */
	public List<Platform> findList(){
		String hql="from Platform";
		return platformDAO.findList(hql);
	}

	/**
	 * 根据平台名查询平台
	 * @author luowei
	 * @param page
	 * @param platformName 平台名
	 * @return 平台
	 */
	public Platform findByName(String platformName)
	{
		String hql="from Platform where platformName=:platformName";
		Map map=new HashMap();
		map.put("platformName", platformName);
		
		Platform platform= this.platformDAO.findOfMap(hql, map);
		return platform;
	}

	/**
	 * 由平台全称(platformFullName)得到平台简称
	 * @param platformFullName
	 * @param fileName
	 * @return String    返回平台简称
	 * @throws
	 */
	public String name2ShortName(String platformFullName,String fileName){
		List<String> platformList=new ArrayList<String>();
		platformList=URLFileUtil.readTxtLines(fileName);
		for(String platformShortName : platformList){
			if(platformFullName!=null && platformFullName.toLowerCase().contains(platformShortName.toLowerCase())){
				return platformShortName;
			}
		}
		return null;
	}
}
