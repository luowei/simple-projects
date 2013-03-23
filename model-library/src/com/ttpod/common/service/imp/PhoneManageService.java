package com.ttpod.common.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ttpod.common.dao.PhoneDAO;
import com.ttpod.common.model.Phone;
import com.ttpod.common.model.Phonebrand;
import com.ttpod.common.model.TPhone;
import com.ttpod.common.model.UserInfo;
import com.ttpod.common.service.BaseService;
import com.ttpod.common.service.IPhoneManageService;
import com.ttpod.common.tool.common.URLFileUtil;
import com.ttpod.common.tool.page.Page;
import com.ttpod.common.tool.query.PropertyFilter;
import com.ttpod.common.tool.query.QueryUtil;
/**
 * @className:PhonebrandManageService.java
 * @classDescription:手机机型Service
 * @author:luowei
 * @createTime:2012-1-10
 */
@Service
public class PhoneManageService extends BaseService<Phone> implements IPhoneManageService{

	@Resource(name="phoneDAO")
	private PhoneDAO phoneDAO;
	public Page findAllPhones(Page page, List<PropertyFilter> pfList) {
		//初始化hql
		StringBuffer hql=new StringBuffer("from Phone");
		//设置查询条件
		String condition= QueryUtil.toSqlString(pfList, true);
		hql.append(condition);
		//查找所有用户的总条数
		int totalCount=this.phoneDAO.findCountBySql(hql.toString());
		page.setTotalCount(totalCount);
		
		
		//是否存在排序
		if(page.isOrderBySetted()){
			hql.append(page.getOrder());
		}
		List<Phone> list=this.phoneDAO.findList(hql.toString(), page.getPageNo(), page.getPageSize());
		//查出结果集
		page.setResult(list);
		return page;
	}

	public Phone findByPhoneName(String phoneName) {
		Map map=new HashMap();
		map.put("phoneName", phoneName);			
		Phone ph=phoneDAO.findOfMap("from Phone where phoneName=:phoneName",map);
		return ph;
	}

	/**
	 * 检测品牌名是否存在
	 * @param phoneName
	 * @return 存在返回真 不存在返回假
	 */
	public boolean checkPhoneName(String fullPhoneName,String brandName) {
		String inBrandName = new String(brandName);
		String inPhoneName = new String(fullPhoneName);
		String simplePhoneName = "";
		//转换为机型简称
		if (inBrandName.contains("（") && inBrandName.contains("）")) {
			String newBrandName = inBrandName.substring(inBrandName
					.indexOf("（") + 1, inBrandName.indexOf("）"));
			simplePhoneName = inPhoneName.replace(newBrandName, "");
		}
		Map map=new HashMap();
		map.put("phoneName",simplePhoneName);			
		Phone ph=this.findOfMap("from Phone where phoneName=:phoneName",map);
		if(ph==null)
		{
			return false;		
		}
		else
		{
			return true;
		}
		
	}
	public PhoneDAO getPhoneDAO() {
		return phoneDAO;
	}
	public void setPhoneDAO(PhoneDAO phoneDAO) {
		this.phoneDAO = phoneDAO;
	}

	/**
	 * 从txt文件中读取机型图片的存放位置
	 * @param filePath
	 * @return 返回机型图片的存放位置
	 */
	public String phoneImgLocation(String filePath) {
		List<String> keyValueList=URLFileUtil.readTxtLines(filePath);
		for(String keyValue:keyValueList){
			if(keyValue.contains("phoneImgPath")){
				return StringUtils.substringAfter(keyValue, "=").trim();
			}
		}
		return null;
	}

	/**
	 * 检测机型名是否存在
	 * @param phoneName
	 * @return 存在返回真 不存在返回假
	 */
	public boolean checkPhoneName(String phoneName) {
		Map map=new HashMap();
		map.put("phoneName", phoneName);			
		Phone ph=phoneDAO.findOfMap("from Phone where phoneName=:phoneName",map);
		if(ph==null)
		{
			return false;		
		}
		else
		{
			return true;
		}
	}
}
