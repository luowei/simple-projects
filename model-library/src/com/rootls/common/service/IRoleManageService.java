package com.rootls.common.service;



import java.util.List;

import com.rootls.common.model.Role;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.query.PropertyFilter;

/**
 * @className:IRoleManageService.java
 * @classDescription:权限管理接口
 * @author:xiayingjie
 * @createTime:2010-7-8
 */
public interface IRoleManageService extends ServiceInterface<Role>{
	/**
	 * 查询所有的角色
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public Page findAllRoles(Page page,List<PropertyFilter> pfList);
	/**
	 * 查询所有的角色
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public List<Role> findAllRoles();
}
