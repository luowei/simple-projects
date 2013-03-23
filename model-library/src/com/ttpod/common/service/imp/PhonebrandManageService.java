package com.ttpod.common.service.imp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ttpod.common.dao.PhonebrandDAO;
import com.ttpod.common.dao.PlatformDAO;
import com.ttpod.common.model.Action;
import com.ttpod.common.model.Phone;
import com.ttpod.common.model.Phonebrand;
import com.ttpod.common.model.Platform;
import com.ttpod.common.service.BaseService;
import com.ttpod.common.service.IPhonebrandManageService;
import com.ttpod.common.tool.page.Page;
import com.ttpod.common.tool.query.PropertyFilter;
import com.ttpod.common.tool.query.QueryUtil;
/**
 * @className:PhonebrandManageService.java
 * @classDescription:手机品牌Service
 * @author:tangxiping
 * @createTime:2012-1-10
 */
@Service
public class PhonebrandManageService extends BaseService<Phonebrand> implements IPhonebrandManageService 
{
	@Resource(name="phonebrandDAO")
	private PhonebrandDAO phonebrandDAO;

	public boolean checkPlatformName(String platformName) {
		// TODO Auto-generated method stub
		return false;
	}

	public Page findAllPhonebrands(Page page, List<PropertyFilter> pfList) {
		//初始化hql
		StringBuffer hql=new StringBuffer("from Phonebrand");
		//设置查询条件
		String condition= QueryUtil.toSqlString(pfList, true);
		hql.append(condition);
		//查找所有用户的总条数
		int totalCount=this.phonebrandDAO.findCountBySql(hql.toString());
		page.setTotalCount(totalCount);
		
		
		//是否存在排序
		if(page.isOrderBySetted()){
			hql.append(page.getOrder());
		}
		List<Phonebrand> list=this.phonebrandDAO.findList(hql.toString(), page.getPageNo(), page.getPageSize());
		//查出结果集
		page.setResult(list);
	
		return page;
	}

	/**
	 * 根据品牌名查询品牌
	 * @author luowei
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public Phonebrand findByName(String brandName) {
		String hql="from Phonebrand where brandName=:brandName";
		Map map=new HashMap();
		map.put("brandName", brandName);
		
		Phonebrand phonebrand= this.phonebrandDAO.findOfMap(hql, map);
		return phonebrand;
	}
	public PhonebrandDAO getPhonebrandDAO() {
		return phonebrandDAO;
	}

	public void setPhonebrandDAO(PhonebrandDAO phonebrandDAO) {
		this.phonebrandDAO = phonebrandDAO;
	}

	/**
	 * 检测品牌名是否存在
	 * @param userName
	 * @return
	 */
	public boolean checkPhonebrandName(String brandName) {
		boolean notNull=false;
		if(!StringUtils.isBlank(brandName)){
			Map map=new HashMap();
			map.put("brandName", brandName);			
			Phonebrand ph=phonebrandDAO.findOfMap("from Phonebrand where brandName=:brandName",map);
			if(ph!=null)
			{
				notNull=true;		
			}
		}
		return notNull;
	}

	/**
	 * 查找手机品牌列表
	 * @author luowei
	 * @param page
	 * @param platformName 品牌名
	 * @return 返回所有手机品牌;没有，则返回空
	 */
	public List<Phonebrand> findList(){
		String hql="from Phonebrand";
		return phonebrandDAO.findList(hql);
	}
}
