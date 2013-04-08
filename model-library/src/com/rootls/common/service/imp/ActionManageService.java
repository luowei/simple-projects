package com.rootls.common.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rootls.common.dao.ActionDAO;
import com.rootls.common.model.Action;
import com.rootls.common.service.BaseService;
import com.rootls.common.service.IActionManageService;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.query.PropertyFilter;
import com.rootls.common.tool.query.QueryUtil;

/**
 * @className:ActionManageService.java
 * @classDescription:动作权限管理类
 * @author:xiayingjie
 * @createTime:2010-7-8
 */
@Service
public class ActionManageService extends BaseService<Action> implements IActionManageService{
	@Resource(name="actionDAO")
	private  ActionDAO actionDAO;
	
	
	//====方法定义区====//
	/**
	 * 查询所有的权限
	 * @param page
	 * @param pfList 查询条件
	 * @return
	 */
	public Page findAllActions(Page page,List<PropertyFilter> pfList){
		//初始化hql
		StringBuffer hql=new StringBuffer("from Action");
		//设置查询条件
		String condition= QueryUtil.toSqlString(pfList, true);
		hql.append(condition);
		//查找所有用户的总条数
		int totalCount=this.actionDAO.findCountBySql(hql.toString());
		page.setTotalCount(totalCount);
		
		
		//是否存在排序
		if(page.isOrderBySetted()){
			hql.append(page.getOrder());
		}
		List<Action> list=this.actionDAO.findList(hql.toString(), page.getPageNo(), page.getPageSize());
		//查出结果集
		page.setResult(list);
	
		return page;
	}

	public ActionDAO getActionDAO() {
		return actionDAO;
	}

	public void setActionDAO(ActionDAO actionDAO) {
		this.actionDAO = actionDAO;
	}


}
