package com.rootls.common.service;

import java.util.List;

import com.rootls.common.model.Phonebrand;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.query.PropertyFilter;
/**
 * @className:IPhonebrandManageService.java
 * @classDescription:手机品牌Service
 * @author:luowei
 * @createTime:2012-1-10
 */
public interface IPhonebrandManageService extends ServiceInterface<Phonebrand>
{
	/**
	 * 查询所有的品牌
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public Page findAllPhonebrands(Page page,List<PropertyFilter> pfList);

	/**
	 * 检测品牌名是否存在
	 * @param userName
	 * @return
	 */
	public boolean checkPhonebrandName(String brandName);
	
	/**
	 * 根据品牌名查询品牌
	 * @author luowei
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public Phonebrand findByName(String platformName);
	
	/**
	 * 查找手机品牌列表
	 * @return 返回所有手机品牌
	 */
	public List<Phonebrand> findList();
}
