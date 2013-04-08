package com.rootls.common.service;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.rootls.common.model.*;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.query.PropertyFilter;
public interface ITPhonebrandManageService extends ServiceInterface<TPhonebrand>
{
	/**
	 * 获取中关村手机品牌信息
	 * @author luowei
	 * @return
	 */
	public  List<TPhonebrand>  getPhonebrandFromZOL();
	
	/**
	 * 查询所有品牌信息
	 * @author luowei
	 * @return
	 */
	public Page findAllPhonebrands(Page page, List<PropertyFilter> pfList);
	
	
	/**
	 * 查找机型列表
	 * @return
	 */
	public List<TPhonebrand> findList();
	
	/**
	 * 判断是否为新平台
	 * 
	 * @author luowei
	 * @createTime 2012-01-19
	 * @param tphone
	 * @return 是否为真
	 */
	public boolean isNewTPhonebrand(String tphonebrandName);
	
	/**
	 * 根据品牌名查找品牌
	 * @param brandName
	 * @return
	 */
	public TPhonebrand findByName(String brandName);
}
