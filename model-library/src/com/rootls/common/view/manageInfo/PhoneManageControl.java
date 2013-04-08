package com.rootls.common.view.manageInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.rootls.common.model.Phone;
import com.rootls.common.model.Phonebrand;
import com.rootls.common.model.Platform;
import com.rootls.common.service.IPhoneManageService;
import com.rootls.common.service.IPhonebrandManageService;
import com.rootls.common.service.IPlatformManageService;
import com.rootls.common.tool.Controler.PhoneUtils;
import com.rootls.common.tool.common.DateUtil;
import com.rootls.common.tool.common.EncodeUtil;
import com.rootls.common.tool.page.Condition;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.page.PageUtil;
import com.rootls.common.tool.query.PropertyFilter;
import com.rootls.common.view.BaseController;
import com.rootls.common.view.dto.QueryDTO;

/**
 * @className:PhonebrandManageControl.java
 * @classDescription:手机机型控制类
 * @author:luowei
 * @createTime:2012-1-10
 */
@Controller
@RequestMapping({"/manage/manageInfo/phone"})
public class PhoneManageControl extends BaseController
{
	Page<Phone> page=new Page<Phone>(10);
	@Autowired
	private IPlatformManageService platformManageService;	
	@Autowired
	private IPhonebrandManageService phonebrandManageService;
	@Autowired
	private IPhoneManageService phoneManageService;
	
	/**
	 * 初始化新增机型页面
	 * @author luowei
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/initPhonePage")
	public String initPhonePage(Model model,HttpServletRequest request,HttpServletResponse response)
	{
		List<Platform> platformList=platformManageService.findList("from Platform");
		List<Phonebrand>  phonebrandList=phonebrandManageService.findList("from Phonebrand");
		if(platformList!=null && phonebrandList!=null){
			request.setAttribute("platformList",platformList);
			request.setAttribute("phonebrandList",phonebrandList);
			return "/manage/manageInfo/phone/addphone";
		}
		String currentPage = (String) this.getSessionAttribute(request,"currentPage3");
		this.sendRedirect(response, currentPage);
		return null;
	}
	
	/**
	 * 初始化编辑机型
	 * @author luowei
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/edit/{id}")
	public String edit(Model model,@PathVariable("id") int id, 
			HttpServletRequest request,HttpServletResponse response){
		Phone phone=this.phoneManageService.findById(id);
		List<Platform> platformList=platformManageService.findList();
		List<Phonebrand>  phonebrandList=phonebrandManageService.findList();
		if(phone!=null && platformList!=null && phonebrandList!=null){
			model.addAttribute("phone",phone);
			model.addAttribute("platformList", platformList);
			model.addAttribute("phonebrandList", phonebrandList);
			return "manage/manageInfo/phone/alterphone";
		}
		String currentPage = (String) this.getSessionAttribute(request,"currentPage3");
		this.sendRedirect(response, currentPage);
		return null;
	}
	
	/**
	 * 更新机型
	 * @author luowei
	 * @param phone
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update")
	public void updatePhone(Phone phone,DefaultMultipartHttpServletRequest request,
			HttpServletResponse response) {
		Phone realPhone=phoneManageService.findById(phone.getId());
		String phonebrandId=(String)request.getParameter("brandName");
		String platformId=(String)request.getParameter("platformName");
		//判断从request中取到的值是否为空
		if(phonebrandId!=null && platformId!=null){
			String imgPath=PhoneUtils.upLoadPhomImg("phoneimage1", "images/phone", request);
			Phonebrand phonebrand=phonebrandManageService.findById(Integer.parseInt(phonebrandId));
			Platform platform=platformManageService.findById(Integer.parseInt(platformId));
			String resolution=phone.getPhoneResolution();
			//判断从以下参数的值是否为空
			if(phonebrandId!=null && platformId!=null && !StringUtils.isBlank(imgPath)){
				realPhone.setPhoneImage("/modelLibrary/images/phone/"+imgPath);
				realPhone.setPhonebrand(phonebrand);
		        realPhone.setPlatform(platform);
		        realPhone.setPhoneResolution(resolution);
		        realPhone.setPhoneAddDate(DateUtil.datetimeToDate());
		        realPhone.setPhoneHeadName(phone.getPhoneHeadName());
		        realPhone.setPhoneName(phone.getPhoneName());
		        realPhone.setPhoneStatus(phone.getPhoneStatus());
		        realPhone.setPhoneDesc(phone.getPhoneDesc());
				this.phoneManageService.alter(realPhone);
			}
			else{
				System.out.println("更新机型失败。。。。");
			}
		}
		else{
			System.out.println("更新机型失败。。。。");
		}
		String currentPage = (String) this.getSessionAttribute(request,"currentPage3");
		this.sendRedirect(response, currentPage);
	}
	
	/**
	 * 新增机型
	 * @author luowei
	 * @param phone
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/new")
	public void newPhone(Phone phone,DefaultMultipartHttpServletRequest request,
			HttpServletResponse response) {
		String phonebrandId=(String)request.getParameter("brandName");
		String platformId=(String)request.getParameter("platformName");
		//判断从request中取到的值是否为空
		if(phonebrandId!=null && platformId!=null){
			String imgPath=PhoneUtils.upLoadPhomImg("phoneimage1", "images/phone", request);
			Phonebrand phonebrand=phonebrandManageService.findById(Integer.parseInt(phonebrandId));
			Platform platform=platformManageService.findById(Integer.parseInt(platformId));
			//判断从request中取到的值是否为空
			if(phonebrandId!=null && platformId!=null && !StringUtils.isBlank(imgPath)){
		        phone.setPhoneImage("/modelLibrary/images/phone/"+imgPath);
				phone.setPhonebrand(phonebrand);
				phone.setPlatform(platform);
		     	phone.setPhoneAddDate(DateUtil.datetimeToDate());
				this.phoneManageService.save(phone);
			}
			else{
				System.out.println("新增机型失败。。。。");
			}
		}
		else{
			System.out.println("新增机型失败。。。。");
		}
		
		String currentPage = (String) this.getSessionAttribute(request,"currentPage3");
		this.sendRedirect(response, currentPage);
	}

	/**
	 * 删除机型
	 * @author luowei
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	public void delete(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) {
		Phone phone=this.phoneManageService.findById(id);
		String realPhoneImg=request.getRealPath(phone.getPhoneImage().replace("/rootls",""));
		if(realPhoneImg!=null && phone!=null){
			PhoneUtils.deletePhoneImg(realPhoneImg);
			if(!phoneManageService.delete(phone)){
				System.out.println("删除机型失败。。。。。");
			}
		}
		else{
			System.out.println("删除机型失败。。。。。。");
		}
		String currentPage = (String) this.getSessionAttribute(request,"currentPage3");
		this.sendRedirect(response, currentPage);
	}
	
	
	/**
	 * 显示所有的机型
	 * @author xiping.tang
	 * @param 	phoneDTO       查询条件
	 * @param   platformName   平台名称
	 * @param 	phonebrandName 品牌名称
	 * @param 	model          属性设置器
	 * @param 	request        请求对象
	 * @return
	 */
	@RequestMapping(value={"/list",""})
	public String list(QueryDTO phoneDTO,String DataType,String PageSize,String platformName,String phonebrandName,Model model,HttpServletRequest request)
	{
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
		String phoneName=phoneDTO.getKey();
		int pageNo=phoneDTO.getPageNo();
		String order=phoneDTO.getOrder();
		String startDate=phoneDTO.getStartDate();
		String endDate=phoneDTO.getEndDate();
		
		String cNphonebrandName=null;
		if(phonebrandName!=null)
		{
			cNphonebrandName=EncodeUtil.urlDecode(phonebrandName);
			cNphonebrandName=EncodeUtil.urlDecode(cNphonebrandName);	
		}
		
		String cNplatformName=null;
		if(platformName!=null)
		{
			cNplatformName=EncodeUtil.urlDecode(platformName);
			cNplatformName=EncodeUtil.urlDecode(cNplatformName);	
		}
		String cNphoneName=null;
		if(phoneName!=null)
		{
			cNphoneName=EncodeUtil.urlDecode(phoneName);
			cNphoneName=EncodeUtil.urlDecode(cNphoneName);	
		}
		
		StringBuffer condition=new StringBuffer();
		//查询条件
		PropertyFilter pf=new PropertyFilter("phoneName:LIKE_S",cNphoneName);
		PropertyFilter pf1=new PropertyFilter("platform.platformName:LIKE_S",cNplatformName);
		PropertyFilter pf2=new PropertyFilter("phonebrand.brandName:LIKE_S",cNphonebrandName);
		PropertyFilter startPf=new PropertyFilter("phoneAddDate:GT_D",startDate);
		PropertyFilter endPf=new PropertyFilter("phoneAddDate:LT_D",endDate);
		List<PropertyFilter>pfList=new ArrayList<PropertyFilter>();
		pfList.add(pf);
		pfList.add(pf1);
		pfList.add(pf2);
		pfList.add(startPf);
		pfList.add(endPf);
		
		//设置跳转页面
		StringBuffer forwordName=new StringBuffer(this.getRoot(request)+"/manage/manageInfo/phone/list.do");
		
		//获取分页跳转页面
		List<Condition> fragmentList=new ArrayList<Condition>();
		fragmentList.add(new Condition("key",EncodeUtil.urlEncode(EncodeUtil.urlEncode(cNphoneName)),"匹配'"+cNphoneName+"'"));
		fragmentList.add(new Condition("platformName",EncodeUtil.urlEncode(EncodeUtil.urlEncode(cNplatformName)),"匹配'"+cNplatformName+"'"));
		fragmentList.add(new Condition("phonebrandName",EncodeUtil.urlEncode(EncodeUtil.urlEncode(cNphonebrandName)),"匹配'"+cNphonebrandName+"'"));
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
			page.setOrder("phoneAddDate:asc");
		}else
		{
			page.setOrder(order);
		}
		page.setPageNo(pageNo);
		//查询所有机型，并放入会话
		page=this.phoneManageService.findAllPhones(page, pfList);
		model.addAttribute("phoneList", page.getResult());
		
		//生成分页标签
		page.setForwordName(forwordName.toString());
		String tag=PageUtil.getTag(page);
		model.addAttribute("tag", tag);
		//索引号
		model.addAttribute("index", page.getFirst());
		
		//索引号
		model.addAttribute("index", page.getFirst());
		
		//设置页面搜索初始值
		model.addAttribute("phonebrandName", cNphonebrandName);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		String currentPage=request.getRequestURI().toString()+forwarCondition+page.getPageNo();
		request.getSession().setAttribute("currentPage3",currentPage );
		if("JSON".equals(DataType))
		{
			model.addAttribute("pageSize", page.getPageSize());
			model.addAttribute("currentPageNum", page.getPageNo());
			model.addAttribute("pageCount", page.getTotalPages());
			model.addAttribute("totalCount", page.getTotalCount());
			return "manage/manageInfo/phone/json";
		}
		return "manage/manageInfo/phone/phoneManage";
		
		
	}
}
