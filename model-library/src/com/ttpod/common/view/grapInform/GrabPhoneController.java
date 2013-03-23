package com.ttpod.common.view.grapInform;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ttpod.common.model.TPhone;
import com.ttpod.common.service.IPhoneManageService;
import com.ttpod.common.service.ITPhoneManageService;
import com.ttpod.common.service.imp.TPhoneManageService;
import com.ttpod.common.tool.common.DateUtil;

@Controller
@RequestMapping("/manage/grapInfor")
public class GrabPhoneController {
	@Autowired
	protected ITPhoneManageService tPhoneManageService;
	@Autowired
	private IPhoneManageService phoneManageService;

	/**
	 * 抓取手机信息
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            回应对象
	 * @param result
	 *            呈现结果页面
	 * @return
	 */
	@RequestMapping(value = "/grapPhone")
	public String getPhone(String url, String brandName,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cNbrandName = null;
		cNbrandName = java.net.URLDecoder.decode(brandName, "utf-8");
		cNbrandName = java.net.URLDecoder.decode(cNbrandName, "utf-8");
		request.getSession().setAttribute("brandName", cNbrandName);
		List<TPhone> tph = tPhoneManageService.getPhonesFromZOL(url);
		request.setAttribute("phones", tph);
		request.getSession().setAttribute("phones", tph);
		return "/manage/grabInfo/phone";
	}

	/**
	 * 获取手机页面数量
	 * @param request
	 *            请求对象
	 * @param response
	 *            回应对象
	 * @param url
	 *            url
	 * @return 页面数量
	 */
	@RequestMapping(value = "/getPhoneCount")
	public void getPhoneCount(String url, String brandName,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageCount = 1;
		tPhoneManageService.getPageCount(url);
		PrintWriter out = response.getWriter();
		out.print(pageCount);
		out.flush();
		out.close();

	}

	/**
	 * 抓取当页手机信息
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            回应对象
	 * @param result
	 *            呈现结果页面
	 * @return
	 */
	@RequestMapping(value = "/grapPhoneByPage")
	public String getPhoneByPage(String url, String brandName,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cNbrandName = null; //中文手机品牌名称
		cNbrandName = java.net.URLDecoder.decode(brandName, "utf-8"); //解码
		cNbrandName = java.net.URLDecoder.decode(cNbrandName, "utf-8"); //再次解码
		request.getSession().setAttribute("brandName", cNbrandName);      //将手机品牌保存至session中
		
		List<TPhone> tph = tPhoneManageService.getPhonesByPageFromZOL(url); 
		request.setAttribute("phones", tph);
		request.getSession().setAttribute("phones", tph);
		return "/manage/grabInfo/phonesInPage";
	}

	/**
	 * 从Session中保存抓取的手机信息
	 * @param request
	 * @param response
	 * @return 呈现结果页面
	 */
	@RequestMapping(value = "/savePhones")
	public String savePhones(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<TPhone> tphone=(List<TPhone>)request.getSession().getAttribute("phones");		
		int grapedPhoneNum = 0;
		int savedPhoneNum = 0;
		int tempTableExsitNum = 0;
		int realTableExsitNum = 0;

		if (tphone != null) {
			grapedPhoneNum = tphone.size();
			String brandName = (String) request.getSession().getAttribute("brandName");
			Iterator<TPhone> it = tphone.iterator();
			while (it.hasNext()) {
				TPhone th = (TPhone) it.next();
				th.setAddDate(DateUtil.datetimeToDate());
				th.setBrandName(brandName);
				// 检查是否为新的机型，以及真实机型表中是否存在
				boolean isNewPhoneinRealTable = !phoneManageService
						.checkPhoneName(th.getPhoneName(),th.getBrandName());
				boolean isNewPhoneinTempTable = tPhoneManageService
						.isNewPhone(th);
				if (isNewPhoneinTempTable == true) {
					if (isNewPhoneinRealTable == true) {
						boolean rs = this.tPhoneManageService.save(th);
						if (rs) {
							savedPhoneNum = savedPhoneNum + 1;
						}

					} else {
						realTableExsitNum = realTableExsitNum + 1;
					}

				} else {
					tempTableExsitNum = tempTableExsitNum + 1;
				}
			}
			request.getSession().removeAttribute("phones");			
			

		}
		try { // 以JSON格式返回保存结果{已抓取的数量：成功保存的数量:失败原因--临时表已存在几条，真实表已存在几条}
			PrintWriter out = response.getWriter();
			String str = "{\"grapedPhoneNum\":" + grapedPhoneNum + ","
					+ "\"savedPhoneNum\":" + savedPhoneNum + ","
					+ "\"tempTableExsitNum\":" + tempTableExsitNum + ","
					+ "\"realTableExsitNum\":" + realTableExsitNum + "}";

			out.println(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * session取出手机信息移除
	 * @param request
	 * @param response
	 * @param result
	 *            呈现结果页面
	 * @return
	 */
	@RequestMapping(value = "/removePhones")
	public String removePhones(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("phones") != null) {
			request.getSession().removeAttribute("phones");
		}
		return null;
	}

	public ITPhoneManageService gettPhoneService() {
		return tPhoneManageService;
	}

	public void TPhoneManageService(TPhoneManageService tPhoneManageService) {
		this.tPhoneManageService = tPhoneManageService;
	}

	public IPhoneManageService getPhoneManageService() {
		return phoneManageService;
	}

	public void setPhoneManageService(IPhoneManageService phoneManageService) {
		this.phoneManageService = phoneManageService;
	}

}
