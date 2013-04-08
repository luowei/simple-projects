package com.rootls.common.service;

import java.util.List;
import com.rootls.common.model.TPhone;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.query.PropertyFilter;
/**
 * @className:ITPhoneManageService.java
 * @classDescription:临时表管理接口
 * @author xiping.tang
 * @version v0.0.1
 * @createTime:2012-1-18
 */
public interface ITPhoneManageService extends ServiceInterface<TPhone>
{
	/**
	 * 从url获取页数
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param url
	 * @return int
	 */
	public int getPageCount(String url);
	/**
	 * 根据url查找手机对象全部集合
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param url
	 * @return List<TPhone>
	 * 		返回查询手机集合
	 */
	public List<TPhone> getPhonesFromZOL(String url);
	/**
	 * 根据url查找手机对象本页集合
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param url
	 * @return List<TPhone> 返回查询手机集合
	 */
	public List<TPhone> getPhonesByPageFromZOL(String url);
	/**
	 * 根据tphone检查真实表存在新的
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param tphone
	 * @return 是否为真
	 */
	public  boolean isNewPhone(TPhone tphone);
	/**
	 * 查询所有的机型
	 * @author xiping.tang
	 * @param  page   查询页面
	 * @param  pfList 查询条件
	 * @return Page
	 */
	public Page findAllPhones(Page page,List<PropertyFilter> pfList);
	
	/**
	 * 查询临时机型列表
	 * @author luowei
	 * @createTime 2012-01-18
	 * @return Page
	 */
	public List<TPhone> findList();
	
	/**
	 * 清空临时机型
	 * @author luowei
	 * @createTime 2012-01-20
	 * @return
	 */
	public boolean clearAll();
	
	/**
	 * 机型的完整名转换成正常机型名
	 * @author wei.luo
	 * @createTime 2012-2-9
	 * @param tphone
	 * @return
	 */
	public String fullName2Name(TPhone tPhone);
}
