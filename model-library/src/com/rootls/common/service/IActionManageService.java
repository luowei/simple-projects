package com.rootls.common.service;

import java.util.List;

import com.rootls.common.model.Action;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.query.PropertyFilter;



/**
 * @className:IActionManageService.java
 * @classDescription:动作权限管理接口
 * @author:xiayingjie
 * @createTime:2010-7-8
 */
public interface IActionManageService extends ServiceInterface<Action>{
	/**
	 * 查询所有的权限
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public Page findAllActions(Page page,List<PropertyFilter> pfList);
}
