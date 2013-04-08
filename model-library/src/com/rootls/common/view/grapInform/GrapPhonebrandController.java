package com.rootls.common.view.grapInform;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rootls.common.model.TPhonebrand;
import com.rootls.common.service.IPhonebrandManageService;
import com.rootls.common.service.ITPhonebrandManageService;
import com.rootls.common.tool.common.DateUtil;

/**
 * @ClassName: GrapPhonebrandController
 * @Description: 采集品牌信息
 * @author wei.luo
 * @date 2012-2-7 下午05:01:58
 *
 */
@Controller
@RequestMapping("/manage/grapInfor")
public class GrapPhonebrandController 
{
	@Autowired
	protected ITPhonebrandManageService tPhonebrandService;
	@Autowired
	protected IPhonebrandManageService phonebrandService;
	
	/**
	 * 抓取品牌信息，并保存到request，和session当中
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param model
	 * @param request
	 * @return String 跳转的路径
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/grapPhonebrand")
	public String grapPhonebrands(Model model,HttpServletRequest request) throws ServletException, IOException
	{
		
		List<TPhonebrand>  tPhonebrand=tPhonebrandService.getPhonebrandFromZOL();
		HttpSession session=request.getSession();
		if(tPhonebrand!=null && session!=null){
			request.setAttribute("phonebrands", tPhonebrand);
			session.setAttribute("phonebrands", tPhonebrand);
			return "/manage/grabInfo/phonebrand";
		}
		else{
			System.out.println("抓取品牌失败。。。。。");
		}
		return null;
	}
	
	/**
	 * 加载手机品牌信息，保存到request当中
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param model
	 * @param request
	 * @param response
	 * @return String 跳转的路径
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadPhonebrand")
	public String loadPhonebrands(Model model,HttpServletRequest request) throws ServletException, IOException
	{
		List<TPhonebrand>  tPhonebrand=tPhonebrandService.getPhonebrandFromZOL();
		if(tPhonebrand!=null){
			request.setAttribute("num", tPhonebrand.size());
			request.setAttribute("phonebrands", tPhonebrand);
			return "manage/grabInfo/phonebrandsimple";
		}
		else{
			System.out.println("加载手机品牌失败。。。。。");
		}
		return null;
	}
	
	/**
	 * 保存手机品牌信息，保存到request当中
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/savePhonebrands")
	public String savePhonebrands(Model model,HttpServletRequest request) throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		List<TPhonebrand> list=(List<TPhonebrand>) session.getAttribute("phonebrands");
		for(TPhonebrand tPhonebrand:list)
		{
			String phonebrandName=tPhonebrand.getPhonebrandName().trim();
			//去重
			if(tPhonebrandService.isNewTPhonebrand(phonebrandName)){
				if(!phonebrandService.checkPhonebrandName(phonebrandName)){
					tPhonebrand.setPhonebrandName(phonebrandName);
					tPhonebrand.setBrandAddDate(DateUtil.datetimeToDate());
					tPhonebrandService.save(tPhonebrand);
				}
			}
		}
		return "manage/grabInfo/sucessful";
	}
	
	/**
	 * phonebrandService的get方法
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @return
	 */
	public ITPhonebrandManageService gettPhonebrandService() {
		return tPhonebrandService;
	}

	/**
	 * phonebrandService的set方法
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param tPhonebrandService
	 */
	public void settPhonebrandService(ITPhonebrandManageService tPhonebrandService) {
		this.tPhonebrandService = tPhonebrandService;
	}
}
