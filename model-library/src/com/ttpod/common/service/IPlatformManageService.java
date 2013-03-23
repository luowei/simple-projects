package com.ttpod.common.service;

import java.util.List;

import com.ttpod.common.model.Platform;
import com.ttpod.common.tool.page.Page;
import com.ttpod.common.tool.query.PropertyFilter;
/**
 * @className:IPlatformManageServices.java
 * @classDescription:手机平台管理接口
 * @author:tangxiping
 * @createTime:2012-1-7
 */
public interface IPlatformManageService extends ServiceInterface<Platform>
{
	/**
	 * 查询所有的平台
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public Page findAllPlatforms(Page page,List<PropertyFilter> pfList);
	/**
	 * 检测平台名是否存在
	 * @param platformName
	 * @return
	 */
	public boolean checkPlatformName(String platformName);

	/**
	 * 根据平台名查询平台
	 * @author luowei
	 * @param page
	 * @param platformName 平台名
	 * @return 平台
	 */
	public Platform findByName(String platformName);
	
	/**
	 * 查找手机平台列表
	 * @author luowei
	 * @param page
	 * @param platformName 平台名
	 * @return 返回所有手机平台;没有，则返回空
	 */
	public List<Platform> findList();
	
	/**
	 * 由平台全称(platformFullName)得到平台简称
	 * @param platformFullName
	 * @param fileName
	 * @return String    返回平台简称
	 * @throws
	 */
	public String name2ShortName(String platformFullName,String fileName);
}
