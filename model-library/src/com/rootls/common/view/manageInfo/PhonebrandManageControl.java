package com.rootls.common.view.manageInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.rootls.common.model.Phonebrand;
import com.rootls.common.service.IPhonebrandManageService;
import com.rootls.common.tool.common.DateUtil;
import com.rootls.common.tool.common.EncodeUtil;
import com.rootls.common.tool.common.URLFileUtil;
import com.rootls.common.tool.page.Condition;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.page.PageUtil;
import com.rootls.common.tool.query.PropertyFilter;
import com.rootls.common.view.BaseController;
import com.rootls.common.view.dto.QueryDTO;
/**
 * @className:PhonebrandManageControl.java
 * @classDescription:手机平牌控制类
 * @author:tangxiping
 * @createTime:2012-1-10
 */
@Controller
@RequestMapping({"/manage/manageInfo/phonebrand"})
public class PhonebrandManageControl extends BaseController
{
	Page<Phonebrand> page = new Page<Phonebrand>(10);
	@Autowired
	private IPhonebrandManageService phonebrandManageService;	
	/**
	 * 新增品牌
	 * @param   phonebrand	  手机品牌对象
	 * @param	request      请求对象
	 * @param   response     新增品牌       
	 */
	@RequestMapping(value="/new")
	public void newPhone(Phonebrand phonebrand,DefaultMultipartHttpServletRequest request,
			HttpServletResponse response) {
		String imgPath=URLFileUtil.upLoadPhomImg("phonebrandimage1", "images/brand", request);
        if(phonebrand!=null && !StringUtils.isBlank(imgPath)){
      	  phonebrand.setBrandImage("/rootls/images/brand/"+imgPath);
      	  phonebrand.setBrandName(phonebrand.getBrandName());
      	  phonebrand.setBrandStatus(phonebrand.getBrandStatus());
      	  phonebrand.setBrandDesc(phonebrand.getBrandDesc());  
      	  phonebrand.setBrandAddDate(DateUtil.datetimeToDate());
      	  phonebrand.setBrandAddDate(DateUtil.datetimeToDate());
	      this.phonebrandManageService.save(phonebrand);
        }
		String currentPage1 = (String) this.getSessionAttribute(request,"currentPage1");
		this.sendRedirect(response, currentPage1);
	
	}
	
	/**
	 * 删除品牌
	 * author:xiping.tang
	 * @param id             删除对象的Id
	 * @param request        请求对象
	 * @param response       回应对象
	 * @return result        返回结果
	 */
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) {
		Phonebrand phonebrand=this.phonebrandManageService.findById(id);
    	//将原来的文件删除
    	String imgPath=phonebrand.getBrandImage();
    	if(!(imgPath==null||imgPath==""))
    	{
    		String imgrealPath=request.getRealPath(imgPath.replace("/rootls",""));
        	File f=new File(imgrealPath);
        	f.delete();	
    	} 	
		this.phonebrandManageService.delete(phonebrand);
		String currentPage1 = (String) this.getSessionAttribute(request,"currentPage1");
		this.sendRedirect(response, currentPage1);
		return null;
	}
	
	/**
	 * 编辑品牌
	 * author:tangxiping
	 * @param id             删除对象的Id
	 * @param request        请求对象
	 * @param  response      回应对象
	 * @return result        返回结果
	 */
	@RequestMapping(value="/edit/{id}")
	public String edit(Model model,@PathVariable("id") int id ){
		Phonebrand phonebrand=this.phonebrandManageService.findById(id);
		model.addAttribute("phonebrand",phonebrand);	
		return "manage/manageInfo/phonebrand/alterphonebrand";
	}

	/**
	 * 更新品牌
	 * author:tangxiping
	 * @param id             删除对象的Id
	 * @param request        请求对象
	 * @param response       回应对象
	 * @return result        返回结果
	 */
	@RequestMapping(value="/update")
	public String update(Phonebrand tphonebrand,DefaultMultipartHttpServletRequest request,HttpServletResponse response)
	{
		String imgPath=URLFileUtil.upLoadPhomImg("phonebrandimage1", "images/brand", request);
		//如果上传文件存在
        if(imgPath!=""){
          tphonebrand.setBrandImage("/rootls/images/brand/"+imgPath);
          tphonebrand.setBrandAddDate(DateUtil.datetimeToDate());
        }
        this.phonebrandManageService.alter(tphonebrand);
		String currentPage1 = (String) this.getSessionAttribute(request,"currentPage1");
		this.sendRedirect(response, currentPage1);
		return null;
	}

	/**
	 * 显示所有的品牌
	 * author: tangxiping
	 * @param  phonebrandDTO 查询条件
	 * @param  request       请求对象
	 * @param  response      回应对象
	 * @return result        返回结果
	 */
	@RequestMapping(value={"/list",""})
	public String list(QueryDTO phonebrandDTO, String DataType,String PageSize,Model model,HttpServletRequest request)
	{   //设置页大小
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
		//fileds
		String phonebrandName=phonebrandDTO.getKey();
		String cNphonebrandName=null;
		if(phonebrandName!=null)
		{
			cNphonebrandName=EncodeUtil.urlDecode(phonebrandName);
			cNphonebrandName=EncodeUtil.urlDecode(cNphonebrandName);	
		}
		int pageNo=phonebrandDTO.getPageNo();
		String order=phonebrandDTO.getOrder();
		String startDate=phonebrandDTO.getStartDate();
		String endDate=phonebrandDTO.getEndDate();	
		//查询条件
		PropertyFilter pf=new PropertyFilter("brandName:LIKE_S",cNphonebrandName);
		PropertyFilter startPf=new PropertyFilter("brandAddDate:GT_D",startDate);
		PropertyFilter endPf=new PropertyFilter("brandAddDate:LT_D",endDate);
		List<PropertyFilter>pfList=new ArrayList<PropertyFilter>();
		pfList.add(pf);
		pfList.add(startPf);
		pfList.add(endPf);
		
		//设置跳转页面
		StringBuffer forwordName=new StringBuffer(this.getRoot(request)+"/manage/manageInfo/phonebrand/list.do");
		
		//获取分页跳转页面
		List<Condition> fragmentList=new ArrayList<Condition>();
		fragmentList.add(new Condition("key",EncodeUtil.urlEncode(EncodeUtil.urlEncode(cNphonebrandName)),"匹配'"+cNphonebrandName+"'"));
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
			page.setOrder("brandAddDate:asc");
		}else
		{
			page.setOrder(order);
		}
	
			page.setPageNo(pageNo);
	
				
		//查询所有平台，并放入会话
		page=this.phonebrandManageService.findAllPhonebrands(page, pfList);
		model.addAttribute("phonebrandList", page.getResult());
		//生成分页标签
		page.setForwordName(forwordName.toString());
		String tag=PageUtil.getTag(page);
		model.addAttribute("tag", tag);
		//索引号
		model.addAttribute("index", page.getFirst());
		
		//设置页面搜索初始值
		model.addAttribute("phonebrandName", cNphonebrandName);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		String currentPage1=request.getRequestURI().toString()+forwarCondition+page.getPageNo();
		request.getSession().setAttribute("currentPage1",currentPage1 );
		//请求JSON数据跳至相应页面
		if("JSON".equals(DataType))
		{
			model.addAttribute("pageSize", page.getPageSize());
			model.addAttribute("currentPageNum", page.getPageNo());
			model.addAttribute("pageCount", page.getTotalPages());
			model.addAttribute("totalCount", page.getTotalCount());
			return "manage/manageInfo/phonebrand/json";
		}
		return "manage/manageInfo/phonebrand/phonebrandManage";
	}
	
}
