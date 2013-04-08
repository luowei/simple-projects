package com.rootls.common.view.verifyInfo;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.rootls.common.model.Phone;
import com.rootls.common.model.Phonebrand;
import com.rootls.common.model.Platform;
import com.rootls.common.model.TPhone;
import com.rootls.common.service.IPhoneManageService;
import com.rootls.common.service.IPhonebrandManageService;
import com.rootls.common.service.IPlatformManageService;
import com.rootls.common.service.ITPhoneManageService;
import com.rootls.common.service.ITPhonebrandManageService;
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
 * 申核机型信息
 * @ClassName: VerifyPhoneController
 * @Description: 
 * @author wei.luo
 * @date 2012-1-16 下午05:09:42
 *
 */
@Controller
@RequestMapping("/manage/verifyPhone")
public class VerifyPhoneController extends BaseController {
//public class VerifyPhoneController<T> extends BaseController {	//注：T，E等未知类型，是在定义类的同时定义的，默认继承于Object类
	@Autowired
	private ITPhoneManageService tphoneService;
	@Autowired
	private ITPhonebrandManageService tphonebrandService;
	@Autowired
	private IPhoneManageService phoneService;
	@Autowired
	private IPhonebrandManageService phonebrandService;
	@Autowired
	private IPlatformManageService platformManageService;
	
	/**
	 * 机型审核不通过处理,将状态设置为2
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param tphone
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/unpassPhones{id}", ""})
	public String modifyUnpassPhones(TPhone tphone,Model model,@PathVariable("id") int id,
			HttpServletRequest request,HttpServletResponse response)
	{
		TPhone tPhone=tphoneService.findById(id);
		tPhone.setPhoneStatus(2);		//状态设置为不通过
		tphoneService.alter(tPhone);	//更新临时表
		String currentPage = (String) this.getSessionAttribute(request,"currentPage");
		this.sendRedirect(response, currentPage);
		return null;
	}
	
	/**
	 * 机型审核通过处理，将状态设为1，并且保存到真实表中
	 * @author luowei
	 * @param id
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = {"/passPhones{id}", ""})
	public String passPhone(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response)
	{
		TPhone tPhone=tphoneService.findById(id);
		Phone realphone=new Phone();
		
		
		String configFilePath=request.getRealPath("WEB-INF/classes/com/rootls/config/config.txt");
		String pathRelative=phoneService.phoneImgLocation(configFilePath);
		String path=request.getRealPath(pathRelative);
		//下载机型图片
		String fileName=URLFileUtil.downImg(tPhone.getPhonePic(),path);
		String phoneImg=request.getContextPath()+pathRelative+fileName;
		
		//更新到真实表机型
		boolean isSuccess=updatePhoneAndTPhone(tPhone, realphone, phoneImg,request);
		if(isSuccess){
			tPhone.setPhoneStatus(1); 		//状态设置为通过
			tphoneService.alter(tPhone);	//更新临时表
			this.phoneService.save(realphone);	//保存到真实表
		}
		else{
			tPhone.setPhoneStatus(2);	//状态自动改为2，不通过
			tphoneService.alter(tPhone);	//更新临时表
		}
		
		//跳转
		String currentPage = (String) this.getSessionAttribute(request,"currentPage");
		this.sendRedirect(response, currentPage);
		return null;
	}

	/**
	 * 设置机型的属性，包括真实表与临时表
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param tPhone
	 * @param realphone
	 * @param phoneImg
	 * @param request
	 * @return boolean 更新成功，返回真，更新失败返回假
	 */
	private boolean updatePhoneAndTPhone(TPhone tPhone, Phone realphone,
			String phoneImg,HttpServletRequest request) {
		boolean isSuccess=false;
		
		//设置机型名称与简称
		String phoneName=tphoneService.fullName2Name(tPhone);
		if(phoneName!=null && !StringUtils.isBlank(phoneImg)){
			realphone.setPhoneName(phoneName);
			realphone.setPhoneHeadName(phoneName.substring(0,1));
			//Runtime.getRuntime().gc();
			
			Platform platform=null;
			String tphoneOS=tPhone.getPhoneOs();
			//平台简称文件的位置
			//String contextPath=request.getContextPath();
			String phonePlatFile=request.getRealPath("WEB-INF/classes/com/rootls/config/platformShortName.txt");
			String platformShortName=platformManageService.name2ShortName(tphoneOS,phonePlatFile);
			
			//设置平台、品牌、分辩率以及图片
			platform=platformManageService.findByName(tPhone.getPhoneOs());
			Phonebrand phonebrand=phonebrandService.findByName(tPhone.getBrandName());
			String phoneResolution=URLFileUtil.handleZOLResolution(tPhone.getPhoneSize());
			if(phonebrand!=null && !StringUtils.isBlank(phoneResolution)){
				if(platform!=null){	//如果平台存在
					realphone.setPlatform(platform);
					realphone.setPhonebrand(phonebrand);
					realphone.setPhoneResolution(phoneResolution);
					realphone.setPhoneImage(phoneImg);
					//设置机型添加日期
					realphone.setPhoneAddDate(DateUtil.datetimeToDate());
					isSuccess = true;
				}
				if(platform==null && platformShortName!=null){	//如果平台不存在
					platform=new Platform();		//插入平台
					platform.setPlatformName(tphoneOS);
					platform.setPlatformShortName(platformShortName);
					platform.setPlatformAddDate(DateUtil.datetimeToDate());
					platformManageService.save(platform);
					
					realphone.setPlatform(platform);
					realphone.setPhonebrand(phonebrand);
					realphone.setPhoneResolution(phoneResolution);
					realphone.setPhoneImage(phoneImg);
					//设置机型添加日期
					realphone.setPhoneAddDate(DateUtil.datetimeToDate());
					isSuccess=true;
				}
			}
		}
		return isSuccess;
	}
	
	/**
	 * 初始化修改未能通过的机型的页面
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param model
	 * @param id
	 * @return String 要跳转的路径
	 */
	@RequestMapping(value = "/modifyUnpassPhones{id}")
	public String initUpdatePhone(Model model,@PathVariable("id") int id) {
		TPhone tPhone=tphoneService.findById(id);
		List<Platform> platformList=platformManageService.findList();
		List<Phonebrand>  phonebrandList=phonebrandService.findList();
		model.addAttribute("tPhone",tPhone);
		model.addAttribute("platformList", platformList);
		model.addAttribute("phonebrandList", phonebrandList);
		return "manage/verifyInfo/phone/alterUnpassPhone";
	}
	
	/**
	 * 修改未能通过的机型，并保存到真实表
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param tPhone
	 * @param primevalPic
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateUnpassPhones") 
	public String updatePhone(TPhone tPhone,String primevalPic,DefaultMultipartHttpServletRequest request,
			HttpServletResponse response) {
		Phone realPhone=new Phone();
		
		String configFilePath=request.getRealPath("WEB-INF/classes/com/rootls/config/config.txt");
		String pathRelative=phoneService.phoneImgLocation(configFilePath);
		
		MultipartFile file=request.getFile("phoneimage1");
		String fileName=null;
		
		if(StringUtils.isNotBlank(file.getOriginalFilename())){
			//上传修改的机型图片
			String upPath = request.getRealPath(pathRelative);
			fileName = URLFileUtil.upLoadImg(file,upPath);
		}
		else{
			//下载机型图片
			String donwPath=request.getRealPath(pathRelative);
			fileName=URLFileUtil.downImg(primevalPic,donwPath);
		}
		String phoneImg=request.getContextPath()+pathRelative+fileName;
		
		//更新到真实表机型
		boolean isSuccess=updatePhoneAndTPhone(tPhone, realPhone, phoneImg,request);
		if(isSuccess){
			tPhone.setPhonePic(phoneImg);	//因为是上传的，把的tPhone的图片地址也要修改
			tPhone.setPhoneStatus(1); 		//状态设置为通过
			tphoneService.alter(tPhone);	//更新临时表
			this.phoneService.save(realPhone);	//保存到真实表
		}
		
		//跳转
		String currentPage = (String) this.getSessionAttribute(request,"currentPage");
		this.sendRedirect(response, currentPage);
		return "manage/verifyInfo/phone/verifiedPhone";
	}

	/**
	 * 批量审核通过
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = {"/mutiCheckPassed", ""})
	public void mutiCheckPassed(HttpServletRequest request,HttpServletResponse response)
	{
		String ids[]=request.getParameterValues("phoneId");
		if(ids!=null){
			//从配置文件中读取图片下载路径
			String configFilePath=request.getRealPath("WEB-INF/classes/com/rootls/config/config.txt");
			String pathRelative=phoneService.phoneImgLocation(configFilePath);
			String path=request.getRealPath(pathRelative);
			for(int i=0;i<ids.length;i++)
			{
				TPhone tPhone=tphoneService.findById(Integer.parseInt(ids[i]));
				Phone realphone=new Phone();
				
				//下载机型图片
				String fileName=URLFileUtil.downImg(tPhone.getPhonePic(),path);
				String phoneImg=request.getContextPath()+pathRelative+fileName;
				
				
				//更新到真实表机型
				boolean isSuccess=updatePhoneAndTPhone(tPhone, realphone, phoneImg,request);
				if(isSuccess){	//通过
					tPhone.setPhoneStatus(1); 		//状态设置为通过
					tphoneService.alter(tPhone);	//更新临时表
					this.phoneService.save(realphone);	//保存到真实表
				}
				else{	//不通过
					tPhone.setPhoneStatus(2);	//状态自动改为2，不通过
					tphoneService.alter(tPhone);	//更新临时表
				}
			}
		}
		String currentPage = (String) this.getSessionAttribute(request,"currentPage");
        this.sendRedirect(response, currentPage);
	}

	/**
	 * 批量审核不通过
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = {"/mutiCheckUnpass", ""})
	public void mutiCheckUnpass(HttpServletRequest request,HttpServletResponse response)
	{
		String ids[]=request.getParameterValues("phoneId");
		if(ids!=null){
			for(int i=0;i<ids.length;i++)
			{
				TPhone tPhone=this.tphoneService.findById(Integer.parseInt(ids[i]));
				tPhone.setPhoneStatus(2);	//状态自动改为2，不通过
				tphoneService.alter(tPhone);	//更新临时表
				
			}
		}
		String currentPage = (String) this.getSessionAttribute(request,"currentPage");
        this.sendRedirect(response, currentPage);
	}
	
	/**
	 * 删除未能通过的机型
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteUnpassPhones{id}")
	public void deleteTempPhone(@PathVariable("id") int id,HttpServletRequest request,
			HttpServletResponse response) {
		TPhone tPhone=tphoneService.findById(id);
		tPhone.setPhoneStatus(2);
		tphoneService.deleteById(id);	//更新临时表
		String currentPage = (String) this.getSessionAttribute(request,"currentPage");
		this.sendRedirect(response, currentPage);
	}
	
	/**
	 * 根据条件清空临时表中的机型
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param brandName
	 * @param phoneOs
	 * @param phoneStatus
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/clearAll")
	public void deleteAll(String brandName,String phoneOs,String phoneStatus,
			HttpServletRequest request,HttpServletResponse response){
		StringBuffer bsql=new StringBuffer("delete from tphone ");
		if(StringUtils.isNotBlank(brandName)){
			bsql.append("where brandName like '%"+brandName+"%' ");
			if(StringUtils.isNotBlank(phoneOs)){
				bsql.append(" and phoneOs like '%"+phoneOs+"%' ");
			}
			if(StringUtils.isNotBlank(phoneStatus)){
				bsql.append(" and phoneStatus = '"+phoneStatus+"'");
			}
			tphoneService.executeSql(bsql.toString());
			String currentPage = (String) this.getSessionAttribute(request,"currentPage");
			this.sendRedirect(response, currentPage);
			return ;
		}
		else if(StringUtils.isNotBlank(phoneOs)){
			bsql.append(" where phoneOs like '%"+phoneOs+"%' ");
			if(StringUtils.isNotBlank(phoneStatus)){
				bsql.append(" and phoneStatus = '"+phoneStatus+"'");
			}
			tphoneService.executeSql(bsql.toString());
			String currentPage = (String) this.getSessionAttribute(request,"currentPage");
			this.sendRedirect(response, currentPage);
			return ;
		}
		else if(StringUtils.isNotBlank(phoneStatus)){
			bsql.append(" where phoneStatus = '"+phoneStatus+"'");
			tphoneService.executeSql(bsql.toString());
			String currentPage = (String) this.getSessionAttribute(request,"currentPage");
			this.sendRedirect(response, currentPage);
			return ;
		}
	}
	
	/**
	 * 清空临时表机型
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/clearTempPhones")
	public void deleteTempPhone(HttpServletRequest request,HttpServletResponse response) {
		tphoneService.clearAll();
		String currentPage = (String) this.getSessionAttribute(request,"currentPage");
		this.sendRedirect(response, currentPage);
	}
	
	/**
	 * 一键审核指定品牌或平台的机型
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param brandName
	 * @param phonePlat
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/passAllPhones")
	public void passAllPhones(String brandName,String phonePlat,
			HttpServletRequest request,HttpServletResponse response){
		if(brandName==null){
			brandName="";
		}
		if(phonePlat==null){
			phonePlat="";
		}
		String hql="from TPhone where phoneStatus='0' and brandName like '%"+brandName+"%' and phoneOs like '%"+phonePlat+"'";
		List<TPhone> tPhoneList=tphoneService.findList(hql);
		//从配置文件中读取图片下载路径
		String configFilePath=request.getRealPath("WEB-INF/classes/com/rootls/config/config.txt");
		String pathRelative=phoneService.phoneImgLocation(configFilePath);
		String path=request.getRealPath(pathRelative);
		
		for(TPhone tPhone:tPhoneList){
			Phone realphone=new Phone();
			//下载机型图片
			String fileName=URLFileUtil.downImg(tPhone.getPhonePic(),path);
			String phoneImg=request.getContextPath()+pathRelative+fileName;
			
			//更新到真实表机型
			boolean isSuccess=updatePhoneAndTPhone(tPhone, realphone, phoneImg,request);
			if(isSuccess){
				tPhone.setPhoneStatus(1); 		//状态设置为通过
				tphoneService.alter(tPhone);	//更新临时表
				this.phoneService.save(realphone);	//保存到真实表
			}
			else{
				tPhone.setPhoneStatus(2);	//状态自动改为2，不通过
				tphoneService.alter(tPhone);	//更新临时表
			}
		}
		
		String currentPage = (String) this.getSessionAttribute(request,"currentPage");
		this.sendRedirect(response, currentPage);
	}
	
	/**
	 * 根据条件显示机型列表
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param phoneDTO
	 * @param phoneStatus
	 * @param brandName
	 * @param phoneOs
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/list", ""})
	public String list(QueryDTO phoneDTO,String phoneStatus,String brandName,String phoneOs,
			Model model,HttpServletRequest request,HttpServletResponse response)
	{
		Page<TPhone> page = new Page<TPhone>(10);
		String phoneName=phoneDTO.getKey();
		int pageNo=phoneDTO.getPageNo();
		String order=phoneDTO.getOrder();
		String startDate=phoneDTO.getStartDate();
		String endDate=phoneDTO.getEndDate();
		
		Phone realPhone=new Phone();
		
		//查询条件List
		List<PropertyFilter> pfList=new ArrayList();
		
		//uri默认为未审核页面
		String uri="manage/verifyInfo/phone/verifyPhone";
		
		//得到查询条件List
		Object[] arr=checkPhoneStatus(pfList,uri,phoneStatus);
		pfList=(List<PropertyFilter>)arr[0];
		uri=(String)arr[1];
		phoneStatus=(String)arr[2];
		
		String cNbrandName=null;
		if(!StringUtils.isBlank(brandName)){
			cNbrandName=EncodeUtil.urlDecode(brandName);
			cNbrandName=EncodeUtil.urlDecode(cNbrandName);
		}
		
		String cNphoneOs=null;
		if(!StringUtils.isBlank(phoneOs)){
			cNphoneOs=EncodeUtil.urlDecode(phoneOs);
			cNphoneOs=EncodeUtil.urlDecode(cNphoneOs);
		}
		
//		TPhonebrand brand=tphonebrandService.findByName(cNbrandName);
//		String phonebrandName=null;
//		if(brand!=null){
//			phonebrandName=brand.getPhonebrandName();
//		}
//		Platform platform=platformManageService.findByName(cNphoneOs);
//		String platformName=null;
//		if(platform!=null){
//			platformName=platform.getPlatformName();
//		}
		
		PropertyFilter pf_phoneStatus=new PropertyFilter("phoneName:LIKE_S",phoneName);
		PropertyFilter pf_phonebrand_id=new PropertyFilter("brandName:LIKE_S",brandName);
		PropertyFilter pf_platform_id=new PropertyFilter("phoneOs:LIKE_S",phoneOs);
		pfList.add(pf_phoneStatus);
		pfList.add(pf_phonebrand_id);
		pfList.add(pf_platform_id);
		
		//设置跳转页面
		StringBuffer forwordName=new StringBuffer(this.getRoot(request)+"/manage/verifyPhone/list.do");
		
		//获取分页跳转页面，(显示页面条件)
		List<Condition> fragmentList=new ArrayList<Condition>();
		fragmentList.add(new Condition("key",phoneName,"匹配'"+phoneName+"'"));
		fragmentList.add(new Condition("phoneStatus",phoneStatus,"等于'"+phoneStatus+"'"));
		fragmentList.add(new Condition("brandName",brandName,"匹配'"+cNbrandName+"'"));
		fragmentList.add(new Condition("phoneOs",phoneOs,"匹配'"+cNphoneOs+"'",false));
		fragmentList.add(new Condition("order",order,"排序",false));
		fragmentList.add(new Condition("startDate",startDate,"大于"+startDate));
		fragmentList.add(new Condition("endDate",endDate,"小于"+endDate));
		String forwarCondition=PageUtil.getForwardCondition(fragmentList);
		forwordName.append(forwarCondition);
		
		request.setAttribute("phoneName", phoneName);
		request.setAttribute("brandName", cNbrandName);
		request.setAttribute("phoneOs", cNphoneOs);
		
		//获取排序跳转页面
		String orderCondition=PageUtil.getOrderCondition(fragmentList);
		model.addAttribute("order", orderCondition);
		
		//获取过滤查询集合
		List<Condition> filterList=PageUtil.getFilterConditions(fragmentList);
		model.addAttribute("filterList", filterList);

		//初始化page属性值--按时间排序
		if(null==order)
		{
			page.setOrder("addDate:asc");
		}else
		{
			page.setOrder(order);
		}
		page.setPageNo(pageNo);
		
		page=this.tphoneService.findAllPhones(page, pfList);
		
		model.addAttribute("phoneList", page.getResult());
		//生成分页标签
		page.setForwordName(forwordName.toString());
		String tag=PageUtil.getTag(page);
		model.addAttribute("tag", tag);
		//索引号
		model.addAttribute("index", page.getFirst());
		//设置页面搜索初始值
		model.addAttribute("phoneStatus", phoneStatus);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		String currentPage=request.getRequestURI().toString()+forwarCondition+page.getPageNo();
		request.getSession().setAttribute("currentPage",currentPage );
		return uri;
	}

	/**
	 * 检查机型状态
	 * @author wei.luo
	 * @createTime 2012-2-7
	 * @param pfList
	 * @param uri
	 * @param phoneStatus
	 * @return
	 */
	private Object[] checkPhoneStatus(List<PropertyFilter> pfList,String uri,String phoneStatus){
		Object[] arr=new Object[3];
		
		//当phoneType未设置时，默认将未审核的数据设为过滤条件
		if(phoneStatus==null)
		{
			PropertyFilter pf=new PropertyFilter("phoneStatus:GE_I",0+"");
			pfList.add(pf);
			phoneStatus=0+"";
		}
		
		//当phoneStatus为3时，查询已处理的（包括已通过的和未通过的）
		if(phoneStatus.equals("3"))
		{
			//查询条件
			PropertyFilter pf1=new PropertyFilter("phoneStatus:GE_I",1+"");
			PropertyFilter pf2=new PropertyFilter("phoneStatus:LE_I",2+"");	
			pfList.add(pf1);
			pfList.add(pf2);
			uri="manage/verifyInfo/phone/verifiedPhone";
		}
		else
		{
			if(phoneStatus.equals("1"))
			{
				PropertyFilter pf=new PropertyFilter("phoneStatus:GE_I",1+"");
				pfList.add(pf);
				uri="manage/verifyInfo/phone/passPhone";
			}
			else if(phoneStatus.equals("2"))
			{
				PropertyFilter pf=new PropertyFilter("phoneStatus:GE_I",2+"");
				pfList.add(pf);
				uri="manage/verifyInfo/phone/unpassPhone";
			}
			//查询条件
			PropertyFilter pf3=new PropertyFilter("phoneStatus:EQ_I",phoneStatus);
			pfList.add(pf3);
		}
		arr[0]=pfList;
		arr[1]=uri;
		arr[2]=phoneStatus;
		return arr;
	}
}
