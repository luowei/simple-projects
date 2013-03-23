package com.ttpod.common.view.verifyInfo;

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

import com.ttpod.common.model.Phone;
import com.ttpod.common.model.Phonebrand;
import com.ttpod.common.model.Platform;
import com.ttpod.common.model.TPhone;
import com.ttpod.common.model.TPhonebrand;
import com.ttpod.common.service.ITPhonebrandManageService;
import com.ttpod.common.service.imp.PhonebrandManageService;
import com.ttpod.common.tool.GetId;
import com.ttpod.common.tool.common.DateUtil;
import com.ttpod.common.tool.common.EncodeUtil;
import com.ttpod.common.tool.common.URLFileUtil;
import com.ttpod.common.tool.page.Condition;
import com.ttpod.common.tool.page.Page;
import com.ttpod.common.tool.page.PageUtil;
import com.ttpod.common.tool.query.PropertyFilter;
import com.ttpod.common.view.BaseController;
import com.ttpod.common.view.dto.QueryDTO;

/**
 * @className:VerifyPhonebrandController.java
 * @classDescription:审核信息
 * @author:tangxiping
 * @createTime:2012-1-11
 */
@Controller
@RequestMapping("/manage/verifyPhonebrand")
public class VerifyPhonebrandController extends BaseController {
	Page<Phonebrand> page = new Page<Phonebrand>(10);
	@Autowired
	private ITPhonebrandManageService tPhonebrandManageService;
	@Autowired
	private PhonebrandManageService phonebrandManageService;

	/**
	 * 显示所有的品牌
	 * 
	 * @author xiping.tang
	 * @param phonebrandDTO
	 *            查询条件
	 * @param brandName
	 *            查询的品牌名称
	 * @param model
	 *            属性设置器
	 * @param request
	 *            请求对象
	 * @return result 返回页面
	 */
	@RequestMapping(value = { "/list", "" })
	public String list(QueryDTO phonebrandDTO, String brandName, Model model,
			HttpServletRequest request) {
		String cNbrandName = null;
		String uri = "manage/verifyInfo/phonebrand/verifyPhonebrand";
		String brandStatus = phonebrandDTO.getKey();
		int pageNo = phonebrandDTO.getPageNo();
		String order = phonebrandDTO.getOrder();
		String startDate = phonebrandDTO.getStartDate();
		String endDate = phonebrandDTO.getEndDate();

		// 查询条件
		PropertyFilter startPf = new PropertyFilter("addDate:GT_D", startDate);
		PropertyFilter endPf = new PropertyFilter("addDate:LT_D", endDate);
		List<PropertyFilter> pfList = new ArrayList();
		pfList.add(startPf);
		pfList.add(endPf);
		if (brandName != null) {
			cNbrandName = EncodeUtil.urlDecode(brandName);
			cNbrandName = EncodeUtil.urlDecode(cNbrandName);
			PropertyFilter pf = new PropertyFilter("phonebrandName:LIKE_S",
					cNbrandName);
			pfList.add(pf);
		}
		// 当phonebrandType未设置时，默认将未审核的数据设为过滤条件
		if (brandStatus == null) {
			brandStatus = 0 + "";
		}
		// 当phonebrandType设置3
		if (brandStatus.equals("3")) {
			// 查询条件brandName
			PropertyFilter pf1 = new PropertyFilter("brandStatus:GE_I", 1 + "");
			PropertyFilter pf2 = new PropertyFilter("brandStatus:LE_I", 2 + "");
			pfList.add(pf1);
			pfList.add(pf2);
			uri = "manage/verifyInfo/phonebrand/verifiedPhonebrand";
		} else {
			if (brandStatus.equals("1")) {
				uri = "manage/verifyInfo/phonebrand/passedPhonebrand";
			} else if (brandStatus.equals("2")) {
				uri = "manage/verifyInfo/phonebrand/unpassPhonebrand";
			}
			// 查询条件
			PropertyFilter pf = new PropertyFilter("brandStatus:EQ_I",
					brandStatus);
			pfList.add(pf);

		}

		// 设置跳转页面
		StringBuffer forwordName = new StringBuffer(this.getRoot(request)
				+ "/manage/verifyPhonebrand/list.do");

		// 获取分页跳转页面
		List<Condition> fragmentList = new ArrayList();
		String temp = EncodeUtil.urlEncode(cNbrandName);
		temp = EncodeUtil.urlEncode(temp);
		fragmentList.add(new Condition("key",
				EncodeUtil.urlEncode(brandStatus), "匹配'" + brandStatus + "'"));
		fragmentList.add(new Condition("brandName", temp, "匹配'" + cNbrandName
				+ "'"));
		fragmentList
				.add(new Condition("startDate", startDate, "大于" + startDate));
		fragmentList.add(new Condition("endDate", endDate, "小于" + endDate));
		fragmentList.add(new Condition("order", order, "排序", false));
		String forwarCondition = PageUtil.getForwardCondition(fragmentList);
		forwordName.append(forwarCondition);

		// 获取过滤查询集合
		List<Condition> filterList = PageUtil.getFilterConditions(fragmentList);
		model.addAttribute("filterList", filterList);

		// 获取排序跳转页面
		String orderCondition = PageUtil.getOrderCondition(fragmentList);
		model.addAttribute("order", orderCondition);

		// 初始化page属性值--按时间排序
		if (null == order) {
			page.setOrder("brandStatus:desc");
		} else {
			page.setOrder(order);
		}

		page.setPageNo(pageNo);

		// 查询所有品牌，并放入会话
		page = this.tPhonebrandManageService.findAllPhonebrands(page, pfList);
		model.addAttribute("phonebrandList", page.getResult());

		// 生成分页标签
		page.setForwordName(forwordName.toString());
		String tag = PageUtil.getTag(page);
		model.addAttribute("tag", tag);
		// 索引号
		model.addAttribute("index", page.getFirst());
		model.addAttribute("brandStatus", brandStatus);

		// 设置页面搜索初始值
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		String currentPage = request.getRequestURI().toString()
				+ forwarCondition + page.getPageNo();
		request.getSession().setAttribute("currentPage0", currentPage);
		return uri;
	}

	/**
	 * 批量审核通过的品牌
	 * 
	 * @author xiping.tang
	 * @param request
	 *            请求对象
	 * @param response
	 *            回应对象
	 * @return result 返回页面
	 */
	@RequestMapping(value = { "/mutiCheckPassed", "" })
	public void mutiCheckPassed(HttpServletRequest request,
			HttpServletResponse response) {
		String id[] = request.getParameterValues("brandId");
		System.out.println(id.length);
		for (int i = 0; i < id.length; i++) {
			TPhonebrand tpb = this.tPhonebrandManageService.findById(Integer
					.parseInt(id[i]));
			tpb.setBrandStatus(1);
			// 下载图片并保存
			String img = tpb.getPhonebrandImage();
			String targetRelativePath=
			URLFileUtil.downloadImg(img, "images/brand", request);
			if (img != null && !img.equals("")) {
				String temp[] = img.split("\\.");
				String Filename = GetId.getNewId() + "."
						+ temp[temp.length - 1];
				String path = request.getRealPath("images/brand/");
				URLFileUtil.saveFile(img, path, Filename);
				tpb.setPhonebrandImage("/modelLibrary/images/brand/" + Filename);
			}
			// 将信息保存到真实表中
			Phonebrand pb = new Phonebrand();
			String phonebrandImage = tpb.getPhonebrandImage();
			if (phonebrandImage != null) {
				pb.setBrandImage(phonebrandImage);
				pb.setBrandName(tpb.getPhonebrandName());
				pb.setBrandStatus(0);
				pb.setBrandAddDate(DateUtil.datetimeToDate());
				pb.setBrandDesc(0);
				this.phonebrandManageService.save(pb);
				// 修改临时表
				this.tPhonebrandManageService.alter(tpb);
			} else {
				tpb.setBrandStatus(2); // 设置为不通过
				this.tPhonebrandManageService.alter(tpb);
			}

		}
		String currentPage = (String) this.getSessionAttribute(request,
				"currentPage0");
		this.sendRedirect(response, currentPage);

	}

	/**
	 * 批量审核不通过的品牌
	 * 
	 * @author xiping.tang
	 * @param request
	 *            请求对象
	 * @param response
	 *            回应对象
	 * @return result 返回页面
	 */
	@RequestMapping(value = { "/mutiCheckUnpass", "" })
	public void mutiCheckUnpass(HttpServletRequest request,
			HttpServletResponse response) {
		String id[] = request.getParameterValues("brandId");
		for (int i = 0; i < id.length; i++) {
			TPhonebrand tpb = this.tPhonebrandManageService.findById(Integer
					.parseInt(id[i]));
			tpb.setBrandStatus(2);
			this.tPhonebrandManageService.alter(tpb);
		}
		String currentPage = (String) this.getSessionAttribute(request,
				"currentPage0");
		this.sendRedirect(response, currentPage);

	}

	/**
	 * 审核通过的品牌
	 * 
	 * @author xiping.tang
	 * @param id
	 *            通过的主键
	 * @param request
	 *            请求对象
	 * @param response
	 *            回应对象
	 * @return result 返回页面
	 */
	@RequestMapping(value = { "/checkPassed/{id}", "" })
	public void checkPassed(@PathVariable("id") int id,
			HttpServletRequest request, HttpServletResponse response) {
		TPhonebrand tpb = this.tPhonebrandManageService.findById(id);
		tpb.setBrandStatus(1);
		String img = tpb.getPhonebrandImage();
		// 下载图片并保存
		if (img != null) {
			String temp[] = img.split("\\.");
			String Filename = GetId.getNewId() + "." + temp[temp.length - 1];
			String path = request.getRealPath("images/brand/");
			URLFileUtil.saveFile(img, path, Filename);
			tpb.setPhonebrandImage("/modelLibrary/images/brand/" + Filename);
		}
		// 将信息保存到真实表中
		Phonebrand pb = new Phonebrand();
		pb.setBrandImage(tpb.getPhonebrandImage());
		pb.setBrandName(tpb.getPhonebrandName());
		pb.setBrandStatus(0);
		pb.setBrandAddDate(DateUtil.datetimeToDate());
		pb.setBrandDesc(0);
		this.phonebrandManageService.save(pb);
		// 修改临时表
		this.tPhonebrandManageService.alter(tpb);
		String currentPage = (String) this.getSessionAttribute(request,
				"currentPage0");
		this.sendRedirect(response, currentPage);

	}

	/**
	 * 审核不通过的品牌
	 * 
	 * @author xiping.tang
	 * @param id
	 *            通过的主键
	 * @param request
	 *            请求对象
	 * @param response
	 *            回应对象
	 * @return result 返回页面
	 */
	@RequestMapping(value = { "/checkUnPass/{id}", "" })
	public void checkUnPass(@PathVariable("id") int id,
			HttpServletRequest request, HttpServletResponse response) {
		TPhonebrand tpb = this.tPhonebrandManageService.findById(id);
		tpb.setBrandStatus(2);
		this.tPhonebrandManageService.alter(tpb);
		String currentPage = (String) this.getSessionAttribute(request,
				"currentPage0");
		this.sendRedirect(response, currentPage);

	}

	/**
	 * 审核不通过的品牌
	 * 
	 * @author xiping.tang
	 * @param id
	 *            id为状态 0未审核 1审核通过 2审核不通过 3已审核
	 * @param request
	 *            请求对象
	 * @param response
	 *            回应对象
	 * @return result 返回页面
	 */
	@RequestMapping(value = { "/clearAll/{id}", "" })
	public void clear(@PathVariable("id") int id, HttpServletRequest request,
			HttpServletResponse response) {
		if (id <= 2) {
			List<TPhonebrand> tpbs = this.tPhonebrandManageService
					.findList("from TPhonebrand where brandStatus=" + id);
			for (TPhonebrand tpb : tpbs) {
				this.tPhonebrandManageService.delete(tpb);
			}

		} else if (id == 3) {
			List<TPhonebrand> tpbs = this.tPhonebrandManageService
					.findList("from TPhonebrand where brandStatus=" + 1);
			for (TPhonebrand tpb : tpbs) {
				this.tPhonebrandManageService.delete(tpb);
			}
			tpbs = this.tPhonebrandManageService
					.findList("from TPhonebrand where brandStatus=" + 2);
			for (TPhonebrand tpb : tpbs) {
				this.tPhonebrandManageService.delete(tpb);
			}

		}

		String currentPage = (String) this.getSessionAttribute(request,
				"currentPage0");
		this.sendRedirect(response, currentPage);

	}

	/**
	 * 编辑未能通过的品牌
	 * 
	 * @author xiping.tang
	 * @param model
	 *            属性设置器
	 * @param id
	 *            需要修改的ID
	 * @return result 返回页面
	 */
	@RequestMapping(value = "/alterUnpassPhonebrand{id}")
	public String alterUnpassPhonebrand(Model model, @PathVariable("id") int id) {
		TPhonebrand tPhonebrand = tPhonebrandManageService.findById(id);
		model.addAttribute("tPhonebrand", tPhonebrand);
		return "manage/verifyInfo/phonebrand/alterUnpassPhonebrand";
	}

	/**
	 * 更新未能通过的品牌，并保存到真实表
	 * 
	 * @author xiping.tang
	 * @param tPhonebrand
	 * @param request
	 *            请求对象
	 * @param response
	 *            回应对象
	 * @return 返回跳转路径
	 */
	@RequestMapping(value = "/updateUnpassPhonebrand")
	public String updatePhonebrand(TPhonebrand tPhonebrand,
			DefaultMultipartHttpServletRequest request,
			HttpServletResponse response) {
		// 上传修改的机型图片
		String fileName = URLFileUtil.upLoadPhomImg("phonebrandimage1",
				"images/brand", request);
		String phoneImg = null;
		if (!StringUtils.isBlank(fileName)) {
			tPhonebrand.setPhonebrandImage("/modelLibrary/images/brand/" + fileName);
			tPhonebrand.setBrandStatus(1);
		}
		// 将信息保存到真实表中
		Phonebrand pb = new Phonebrand();
		pb.setBrandImage(tPhonebrand.getPhonebrandImage());
		pb.setBrandName(tPhonebrand.getPhonebrandName());
		pb.setBrandStatus(0);
		pb.setBrandAddDate(DateUtil.datetimeToDate());
		pb.setBrandDesc(0);
		this.phonebrandManageService.save(pb);
		// 修改临时表
		this.tPhonebrandManageService.alter(tPhonebrand);
		// 跳转
		String currentPage = (String) this.getSessionAttribute(request,
				"currentPage0");
		this.sendRedirect(response, currentPage);
		return null;
	}

}
