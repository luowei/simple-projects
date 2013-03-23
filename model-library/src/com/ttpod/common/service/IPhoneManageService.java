package com.ttpod.common.service;

import java.util.List;

import com.ttpod.common.model.Phone;
import com.ttpod.common.tool.page.Page;
import com.ttpod.common.tool.query.PropertyFilter;

public interface IPhoneManageService extends ServiceInterface<Phone>
{
	/**
	 * 查询所有的机型
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public Page findAllPhones(Page page,List<PropertyFilter> pfList);
	
	/**
	 * 检测机型名是否存在
	 * @param phoneName
	 * @return
	 */
	public boolean checkPhoneName(String phoneName);
	
	/**
	 * 检测品牌名是否存在
	 * @param phoneName
	 * @return 存在返回真 不存在返回假
	 */
	public boolean checkPhoneName(String fullPhoneName,String brandName);
	
	/**
	 * 按照机型名查找
	 * @param phoneName
	 * @return
	 */
	public Phone findByPhoneName(String phoneName);
	
	/**
	 * 机型图片的存放地址
	 * @Title: phoneImgLocation
	 * @return String    返回机型图片的存放地址
	 * @throws
	 */
	public String phoneImgLocation(String filePath);
}
