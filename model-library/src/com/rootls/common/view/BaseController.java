package com.rootls.common.view;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @className:BaseController.java
 * @classDescription:
 * @author:xiayingjie
 * @param <IUserManageService>
 * @createTime:2011-5-3
 */

public class BaseController {	

	/**
	 * 验证表单
	 * @param result
	 * @param model
	 * @return
	 */
	public boolean validate(BindingResult result, Model model) {
		boolean flag=true;
		if (result.hasErrors()) {
			flag= false;
			for (FieldError fe : result.getFieldErrors()) {
				model.addAttribute(fe.getField(), fe.getDefaultMessage());
			}
		}
		return flag;
	}

	
	/**
	 * 添加日期绑定参数
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//初始化日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		//初始化基本类型
		binder.registerCustomEditor(Short.class, new CustomNumberEditor(Short.class, true));
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
        binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
        binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));
		
	}
	/**
	 *  获取根目录地址
	 * @param request
	 * @return
	 */
	public String getRoot(HttpServletRequest request){
		return request.getSession().getServletContext().getContextPath();
		
	}
	/**
	 * 获取会话中的数据
	 * @param request
	 * @param key
	 * @param object
	 */
	public Object getSessionAttribute(HttpServletRequest request,String key){
		return request.getSession().getAttribute(key);
	}
	/**
	 * 发送地址 
	 * @param response
	 * @param forwardName
	 */
	public void sendRedirect(HttpServletResponse response,String forwardName){
		try {
			response.setCharacterEncoding("utf-8");
			response.sendRedirect(forwardName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
	
}
