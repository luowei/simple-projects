package com.rootls.common.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rootls.common.dao.RoleDAO;
import com.rootls.common.model.Role;
import com.rootls.common.service.BaseService;
import com.rootls.common.service.IRoleManageService;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.query.PropertyFilter;
import com.rootls.common.tool.query.QueryUtil;



/**
 * @className:RoleManageService.java
 * @classDescription:角色管理类
 * @author:xiayingjie
 * @createTime:2010-7-8
 */
@Service
public class RoleManageService extends BaseService<Role> implements IRoleManageService{
	@Resource(name="roleDAO")
	private  RoleDAO roleDAO;
	
	//====方法定义区====//
	/**
	 * 查询所有的角色
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public Page findAllRoles(Page page,List<PropertyFilter> pfList){
		//初始化hql
		StringBuffer hql=new StringBuffer("from Role");
		//设置查询条件
		String condition= QueryUtil.toSqlString(pfList, true);
		hql.append(condition);
		//查找所有用户的总条数
		int totalCount=this.roleDAO.findCountBySql(hql.toString());
		page.setTotalCount(totalCount);
		
		
		//是否存在排序
		if(page.isOrderBySetted()){
			hql.append(page.getOrder());
		}
		List<Role> list=this.roleDAO.findList(hql.toString(), page.getPageNo(), page.getPageSize());
		//查出结果集
		page.setResult(list);
	
		return page;
	}
	/**
	 * 查询所有的角色
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public List<Role> findAllRoles(){
		String hql="from Role";
		return this.roleDAO.findList(hql);
	}
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	
	//==========//


}
