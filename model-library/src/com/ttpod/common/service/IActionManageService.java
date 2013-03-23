package com.ttpod.common.service;

import java.util.List;

import com.ttpod.common.model.Action;
import com.ttpod.common.tool.page.Page;
import com.ttpod.common.tool.query.PropertyFilter;



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
