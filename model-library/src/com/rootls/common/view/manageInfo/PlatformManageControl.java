package com.rootls.common.view.manageInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rootls.common.model.Platform;
import com.rootls.common.service.IPlatformManageService;
import com.rootls.common.tool.common.DateUtil;
import com.rootls.common.tool.common.EncodeUtil;
import com.rootls.common.tool.page.Condition;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.page.PageUtil;
import com.rootls.common.tool.query.PropertyFilter;
import com.rootls.common.view.BaseController;
import com.rootls.common.view.dto.QueryDTO;
/**
 * @className:PlatformManageControl.java
 * @classDescription:手机平台控制类
 * @author:tangxiping
 * @createTime:2012-1-7
 */
@Controller
@RequestMapping("/manage/manageInfo/platform")
public class PlatformManageControl extends BaseController
{
	Page<Platform> page = new Page<Platform>(10);
	@Autowired
	private IPlatformManageService platformManageService;
	/**
	 * 显示所有的平台
	 * @author:tangxiping
	 * @param platformDTO 查询条件
	 * @param model       属性设置器
	 * @param request     request请求
	 * @return result     跳转的jsp
	 */
	@RequestMapping(value={"/list",""})
	public String list(QueryDTO platformDTO,String DataType,String PageSize,Model model,HttpServletRequest request)
	{
		//fileds
		if(PageSize!=null)
		{
			int size=10;
			try
			{
				size=Integer.parseInt(PageSize);
				
			}
			catch(Exception e)
			{
				
			}
			
			page.setPageSize(size);
		}
		String platformName=platformDTO.getKey();
		String cNplatformName=null;
		if(platformName!=null)
		{
			cNplatformName=EncodeUtil.urlDecode(platformName);
			cNplatformName=EncodeUtil.urlDecode(cNplatformName);	
		}
		int pageNo=platformDTO.getPageNo();
		String order=platformDTO.getOrder();
		String startDate=platformDTO.getStartDate();
		String endDate=platformDTO.getEndDate();
		//查询条件
		PropertyFilter pf=new PropertyFilter("platformName:LIKE_S",cNplatformName);
		PropertyFilter startPf=new PropertyFilter("platformAddDate:GT_D",startDate);
		PropertyFilter endPf=new PropertyFilter("platformAddDate:LT_D",endDate);
		List<PropertyFilter>pfList=new ArrayList<PropertyFilter>();
		pfList.add(pf);
		pfList.add(startPf);
		pfList.add(endPf);
		
		//设置跳转页面
		StringBuffer forwordName=new StringBuffer(this.getRoot(request)+"/manage/manageInfo/platform/list.do");
		
		//获取分页跳转页面
		List<Condition> fragmentList=new ArrayList<Condition>();
		fragmentList.add(new Condition("key",EncodeUtil.urlEncode(EncodeUtil.urlEncode(cNplatformName)),"匹配'"+cNplatformName+"'"));
		fragmentList.add(new Condition("order",order,"排序",false));
		fragmentList.add(new Condition("startDate",startDate,"大于"+startDate));
		fragmentList.add(new Condition("endDate",endDate,"小于"+endDate));
		String forwarCondition=PageUtil.getForwardCondition(fragmentList);
		forwordName.append(forwarCondition);
		
		//获取排序跳转页面
		String orderCondition=PageUtil.getOrderCondition(fragmentList);
		model.addAttribute("order", orderCondition);
		
		//获取过滤查询集合
		List<Condition> filterList=PageUtil.getFilterConditions(fragmentList);
		model.addAttribute("filterList", filterList);

		//初始化page属性值--按时间排序
		if(null==order)
		{
			page.setOrder("platformAddDate:asc");
		}else
		{
			page.setOrder(order);
		}
	    page.setPageNo(pageNo);
	
				
		//查询所有平台，并放入会话
		page=this.platformManageService.findAllPlatforms(page, pfList);
		model.addAttribute("platformList", page.getResult());
		//生成分页标签
		page.setForwordName(forwordName.toString());
		String tag=PageUtil.getTag(page);
		model.addAttribute("tag", tag);
		//索引号
		model.addAttribute("index", page.getFirst());	
		//设置页面搜索初始值
		model.addAttribute("platformName", platformName);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		String currentPage=request.getRequestURI().toString()+forwarCondition+page.getPageNo();
		request.getSession().setAttribute("currentPage0",currentPage );
		if("JSON".equals(DataType))
		{
			model.addAttribute("pageSize", page.getPageSize());
			model.addAttribute("currentPageNum", page.getPageNo());
			model.addAttribute("pageCount", page.getTotalPages());
			model.addAttribute("totalCount", page.getTotalCount());
			return "manage/manageInfo/platform/json";
		}
		return "manage/manageInfo/platform/platformManage";
	}
	/**
	 * 新增平台
	 * author:tangxiping
	 * @param platform  将要被添加的平台对象
	 * @param request   请求对象
	 * @param response  回应对象
	 * @return result   返回结果
	 */
	@RequestMapping(value = "/new")
	public String _new(Platform platform, HttpServletRequest request,
			HttpServletResponse response) {
		platform.setPlatformAddDate(DateUtil.datetimeToDate());
		this.platformManageService.save(platform);
		String currentPage = (String) this.getSessionAttribute(request,"currentPage0");
		this.sendRedirect(response, currentPage);
		return null;
	}
	
	/**
	 * 检查平台名是否存在
	 * author:tangxiping
	 * @param platformName  平台名称
	 * @param response      回应对象
	 * @return result       返回结果
	 */
	@RequestMapping("/checkPlatformName")
	public String checkPlatformName(String platformName, HttpServletResponse respnose) {

		boolean flag = this.platformManageService.checkPlatformName(platformName);
		try {
			if (flag) 
			{
				respnose.getWriter().write("{\"isNewPlatformName\":true}");
			} else 
			{
				respnose.getWriter().write("{\"isNewPlatformName\":false}");
			}
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 编辑平台
	 * author:tangxiping
	 * @param platformName  平台名称
	 * @param response      回应对象
	 * @return result       返回结果-编辑页面
	 */
	@RequestMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) 
	{
		Platform platform = this.platformManageService.findById(id); //查找平台对象
		model.addAttribute("platform", platform);
		return "manage/manageInfo/platform/alterPlatform";
	}
	/**
	 * 修改平台
	 * author:tangxiping
	 * @param model
	 * @param userInfo
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(Platform platformInfo, HttpServletRequest request,
			HttpServletResponse response) {
		this.platformManageService.alter(platformInfo);
		String currentPage = (String) this.getSessionAttribute(request,"currentPage0");
		this.sendRedirect(response, currentPage);
		return null;
	}
	/**
	 * 删除平台
	 * author:tangxiping
	 * @param model
	 * @param userInfo
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) {
		this.platformManageService.deleteById(id);
		String currentPage = (String) this.getSessionAttribute(request,
				"currentPage0");
		this.sendRedirect(response, currentPage);
		return null;
	}
	

}
